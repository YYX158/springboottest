package com.atyyx.boot05web01.service.impl;

import com.atyyx.boot05web01.bean.User;
import com.atyyx.boot05web01.mapper.UserMapper;
import com.atyyx.boot05web01.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService是一个顶级的接口
 * ServiceImpl<UserMapper,User>是一个顶级接口的实现类，第一个参数是要操作的mapper，第二个操作是操作的对象的数据类型
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService
{
    @Autowired
    UserMapper userMapper;

    public User getUserById(Long id)
    {
        User user = this.userMapper.selectById(1L);
        return user;
    }

    public List<User> getAllUser()
    {
        return null;
    }
}
