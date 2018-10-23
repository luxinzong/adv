package com.suma.utils;

import com.suma.pojo.AdvUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;


/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description 权限工具类
 **/
public class ShiroUtils {

    //只产生一个SecureRandomNumberGenerator实例
    private static final SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();

    /**
     * 随机生成盐值方法，保证线程安全性
     *
     * @return
     */
    public static String generateRandomSalt(){
        synchronized (ShiroUtils.class){
            //一个Byte占两个字节，此处生成3字节，字符串长度为6
            String hex = secureRandom.nextBytes(3).toHex();
            return hex;
        }
    }

    public static Subject getSubject(){
        synchronized (ShiroUtils.class){
            return SecurityUtils.getSubject();
        }
    }

    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    public static AdvUser getUser(){
        synchronized (ShiroUtils.class){
            AdvUser advUser = null;
            Object object = getSubject().getPrincipal();
            if(object != null){
                advUser = new AdvUser();
                BeanUtils.copyProperties(object,advUser);
            }

            return advUser;
        }
    }

    public static String getLoginName(){
        return getUser().getUserName();
    }

    public static String getIp(){
        return getSubject().getSession().getHost();
    }

    public static String getSessionId(){
        return String.valueOf(getSubject().getSession().getId());
    }
}
