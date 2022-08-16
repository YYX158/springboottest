package com.atyyx.boot05web01;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 说明是springboot的测试类，可以使用springboot中的容器
@ExtendWith(SpringExtension.class) //指明是对接Springboot的，可以用这个接口对接其他功能模块
// Junit4以前是用@Runwith, Junit5以后取而代之的是@ExtendWith
@DisplayName("junit5功能测试类")
public class Junit5Test
{

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 断言：前面的断言失败，那么后面的代码都不会在执行了
     */
    @Test
    @DisplayName("测试简单断言")
    void testSimpleAssertions()
    {
        int cal = cal(3, 3);
        // 使用断言来判断，是都跟我预期想的是意义昂的
        // Assertions 是断言类，断言类中都是静态方法
        // 第一个参数是期望返回的值
        // 第二个参数的实际返回来的值
        // 第三个参数是错误提示
        assertEquals(5,cal,"业务逻辑计算失败");
        // 判断两个对象是否为同一个对象
        // 也就是判断两个对象是否指向一个内存（地址）空间
        Object obj1=new Object();
        Object obj2=new Object();
        assertSame(obj1,obj2,"两个对象不一样");
    }
    @Test
    @DisplayName("测试数组断言机制")
    void testArray()
    {
        assertArrayEquals(new int[]{1,2},new int[]{1,2},"两个数组不一样");

    }


    int cal(int i,int j)
    {
        return i+j;
    }

    @Test
    @DisplayName("组合断言测试")
    void all()
    {
        /**
         * 函数式断言极值
         * 第一个参数是组合断言的名称
         * 第二个可以通过函数式（lamda表达式）
         * 组合断言从第二个参数以后就都是判断的条件
         * 组合断言要想为真，就必须每一个子断言都是真的才可以
         */
        assertAll("test",
                ()-> assertTrue(true&&true,"结果不是true"),
                ()->assertEquals(1,2,"结果不是1"));
    }

    @Test
    @DisplayName("异常断言测试")
    void testException()
    {
        // 断定业务逻辑一定会出现异常
        assertThrows(ArithmeticException.class,
                ()->{int i=10/0;},"业务逻辑居然正常运行？");
    }

   @Test
   @DisplayName("快速失败测试")
    void testFail()
    {
        if(1==2)
          fail("测试失败");
    }
    /**
     * 需要注意的是，这个@Test一定要是 org.junit.jupiter.api.Test里面的
     */
    @DisplayName("测试displayname注解")// 给测试方法起一个名字
    @Test
    void testDisplayName()
    {

        System.out.println(1);
        System.out.println(jdbcTemplate);
    }

    @BeforeEach  // 每个单元测试运行之前，都会执行一次
    void testBeforeEach()
    {
        System.out.println("测试就要开始啦");
    }

    @AfterEach // 每个单元测试运行之后，都会执行一次
    void testAfterEach(){
        System.out.println("测试结束啦");
    }

    @BeforeAll //在所有测试执行之前先执行
    static void testBeforAll(){
        System.out.println("所有测试就要开始了");
    }

    @AfterAll
    static void testAfterAll()
    {
        System.out.println("所有的测试都已经结束了");
    }

    @Test
    @Disabled //禁用该测试方法
    @DisplayName("测试方法2")
    void test2()
    {
        System.out.println(2);
    }

    @Test
    // 如果超过500ms就认为是方法超时了
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)  // value的值表示数值    unit的值表示单位，这里是纳秒
    void testTimeout() throws InterruptedException {
        Thread.sleep(500);
    }

    @Test
    @RepeatedTest(5)// 指明这是一个重复测试，需要测试的次数是五次
    void test3()
    {
        System.out.println(3);
    }

    /**
     * 前置条件
     */
    @Test
    @DisplayName("测试假设")
    void testassumptions()
    {
        /**
         * 断言会把测试汇总出来
         * 
         */
        assertTrue(false,"结果不是true");
        System.out.println(1111);
    }
}
