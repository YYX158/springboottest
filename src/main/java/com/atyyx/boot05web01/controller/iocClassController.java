package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.Emp;
import com.atyyx.boot05web01.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
public class iocClassController {

    public void test01(Map<String, User> map, List<User> list)
    {
        System.out.println("test01");
    }

    public Map<String,User> test02()
    {
        System.out.println("test02");
        return null;
    }

    @GetMapping("/testIoc")
    public void testIoc() throws  NoSuchMethodException {
        Method method1 = iocClassController.class.getMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = method1.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println(genericParameterType);
            if(genericParameterType instanceof ParameterizedType)
            {
                // 获取真实的参数类型
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }
    }
}
