package com.atyyx.boot05web01.mapper;

import com.atyyx.boot05web01.bean.Stu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StuMapper extends BaseMapper<Stu>
{
    public Stu getStuById(Long id);
}

