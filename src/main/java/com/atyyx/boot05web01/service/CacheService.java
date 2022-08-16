package com.atyyx.boot05web01.service;

import com.atyyx.boot05web01.bean.Emp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface CacheService extends IService<Emp> {
    public Emp getEmpById(@Param("emp")Emp emp);

    public void setAemp(@Param("emp") Emp emp);

    public List<Emp> getEmpByXing(String xing,Integer pageCurrent,Integer pageSize) throws IOException;

    public int deleteMoreByArray(Integer [] eids);

    public Integer InsertEmps(List<Emp> emps);
}
