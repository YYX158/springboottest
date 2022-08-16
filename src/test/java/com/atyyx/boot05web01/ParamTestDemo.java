package com.atyyx.boot05web01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

@DisplayName("参数化测试")
public class ParamTestDemo {

    @ParameterizedTest  // 参数化测试
    @DisplayName("参数化测试")
    @ValueSource(ints={1,2,3,4,5}) // 指定数据的来源
    void  testParameterized(int i)
    {
        System.out.println(i);
    }

    static Stream<String> stringProvider()
    {
        return Stream.of("apple","banana","atyyx");
    }

    @ParameterizedTest  // 参数化测试
    @DisplayName("参数化测试")
    @MethodSource("stringProvider")
    void  testParameterized2(String i)
    {
        System.out.println(i);
    }
}
