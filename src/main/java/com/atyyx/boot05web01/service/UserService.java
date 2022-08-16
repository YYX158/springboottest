package com.atyyx.boot05web01.service;

import com.atyyx.boot05web01.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User>
{
    public User getUserById(Long id);

    public List<User> getAllUser();
}
