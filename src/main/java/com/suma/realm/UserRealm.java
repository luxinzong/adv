package com.suma.realm;

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
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

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
        System.out.println(roleKeys);
        //权限加入AuthorizationInfo认证对象
        Set<String> perms = menuService.selectMenuPermsByUserId(userId);
        info.setStringPermissions(perms);
        System.out.println(perms);
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

        AdvUser advUser = null;
        LoginVO loginVO = new LoginVO();
        loginVO.setUsername(username);
        loginVO.setPassword(password);
        loginVO.setRememberMe(rememberMe);
        advUser = loginService.login(loginVO);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(advUser,password,getName());
        return info;
    }
}
