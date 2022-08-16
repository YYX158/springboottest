package com.atyyx.boot05web01.mapper;

import com.atyyx.boot05web01.bean.Mbg;

public interface MbgMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(Mbg row);

    int insertSelective(Mbg row);

    Mbg selectByPrimaryKey(Integer eid);

    int updateByPrimaryKeySelective(Mbg row);

    int updateByPrimaryKey(Mbg row);
}