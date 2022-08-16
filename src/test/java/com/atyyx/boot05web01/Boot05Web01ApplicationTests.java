package com.atyyx.boot05web01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;


@Slf4j
@SpringBootTest
class Boot05Web01ApplicationTests {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    UserMapper userMapper;
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Test
//    void contextLoads() {
//
//        Long along = jdbcTemplate.queryForObject("select count(*) from stu",Long.TYPE);
//        log.info("记录的总数有{}条数据",along);
//
//        log.info("数据源的类型{}",dataSource.getClass());
//    }
//
//    @Test
//    void testUserMapper()
//    {
//        User user = this.userMapper.selectById(1L);//1L是Long对象
//        log.info("用户信息:{}",user);
//    }
//
//    @Test
//    void testRedis()
//    {
//        // 指明要对key,value都是String的对象进行操作
//        ValueOperations<String,String> operations = redisTemplate.opsForValue();
//
//        operations.set("hello","world");
//
//        String hello = operations.get("hello");
//
//        log.info("这里面的值是:{}",hello);
//
//    }


}
