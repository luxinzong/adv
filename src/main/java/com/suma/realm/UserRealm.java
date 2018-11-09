package com.suma.realm;

import com.suma.constants.ExceptionConstants;
import com.suma.exception.LoginException;
import com.suma.pojo.AdvUser;
import com.suma.service.iAdvMenuService;
import com.suma.service.iAdvRoleService;
import com.suma.service.iLoginService;
import com.suma.utils.ShiroUtils;
import com.suma.vo.LoginVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private iLoginService loginService;
    @Autowired
    private iAdvRoleService roleService;
    @Autowired
    private iAdvMenuService menuService;
    @Autowired
    private SessionDAO sessionDAO;


    /**
     *
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        int userId = ShiroUtils.getUser().getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //角色加入AuthorizationInfo认证对象
        Set<String> roleKeys = roleService.selectRoleKeys(userId);
        info.setRoles(roleKeys);
        //权限加入AuthorizationInfo认证对象
        Set<String> perms = menuService.selectMenuPermsByUserId(userId);
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        String username = upToken.getUsername();
        String password = "";
        boolean rememberMe = upToken.isRememberMe();

        if(upToken.getPassword() != null){
            password = new String(upToken.getPassword());
        }

        AdvUser advUser;
        LoginVO loginVO = new LoginVO();
        loginVO.setUsername(username);
        loginVO.setPassword(password);
        loginVO.setRememberMe(rememberMe);
        advUser = loginService.login(loginVO);

        //获取当前在线session
        Collection<Session> sessionCollection = sessionDAO.getActiveSessions();
        if(!CollectionUtils.isEmpty(sessionCollection)){//判断session是否为空
            sessionCollection.forEach(session -> {
                if(session != null){
                    //获取session保存用户的信息
                    SimplePrincipalCollection sessionAttribute = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                    if(sessionAttribute != null){
                        AdvUser sessionAdvUser = (AdvUser) sessionAttribute.getPrimaryPrincipal();
                        if(sessionAdvUser != null && sessionAdvUser.getUserName().equals(username)){//如果当前已经存在相同用户名的session
                            throw new LoginException(ExceptionConstants.LOGIN_EXCEPTION_CAN_NOT_REPEAT_LOGIN);
                        }
                    }
                }
            });
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(advUser,password,getName());
        return info;
    }
}
