package com.suma.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/29 0029
 * @Description 将通过树结构service统一用此类方法实现
 **/
@Service
@Slf4j
public class TreeService<T>{

    public void addRootToRootList(List<T> elementList,List<T> rootList, String ancestors,String status
                            ,Multimap<String,T> multimap) {
        elementList.forEach(element ->{
            Class clazz = element.getClass();
            Method ancestorsMethod;
            String invokeAncestors;
            try {
                 ancestorsMethod = clazz.getMethod("getAncestors");
                 invokeAncestors = (String)ancestorsMethod.invoke(element);
                if(Strings.isNullOrEmpty(status)){
                    multimap.put(invokeAncestors,element);
                    if(invokeAncestors.equals(ancestors)){
                        rootList.add(element);
                    }
                }else{
                    Method statusMethod = clazz.getMethod("getStatus");
                    String invokeStatus = (String) statusMethod.invoke(element);
                    if(invokeStatus.equals(status)){
                        multimap.put(invokeAncestors,element);
                    }
                    if(invokeAncestors.equals(ancestors) && invokeAncestors.equals(status)){
                        rootList.add(element);
                    }
                }
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });


    }

}
