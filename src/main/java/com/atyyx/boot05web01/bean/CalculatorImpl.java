package com.atyyx.boot05web01.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculatorImpl  implements Calculator{
    @Override
    public Integer add(Integer a, Integer b)
    {
        //log.info(a+"+"+b+"="+a+b);
        return a+b;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        //log.info(a+"-"+b+"="+(a-b));
        return a-b;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        //log.info(a+"*"+b+"="+a*b);
        return a*b;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        //log.info(a+"/"+b+"="+a/b);
        return a/b;
    }
}
