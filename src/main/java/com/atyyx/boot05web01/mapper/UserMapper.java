package com.atyyx.boot05web01.mapper;

import com.atyyx.boot05web01.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper  extends BaseMapper<User>
{
    // 有了mybatis-plus以后，我们的mapper直接继承BaseMapper就可以拥有crud

}
