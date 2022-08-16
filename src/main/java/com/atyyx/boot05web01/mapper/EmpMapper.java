package com.atyyx.boot05web01.mapper;

import com.atyyx.boot05web01.bean.Emp;
import com.atyyx.boot05web01.bean.Empper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpMapper  extends BaseMapper<Empper> {

    public Empper getEmpAndDeptbyEmpId(@Param("empId") Integer empId);

    public Empper getEmpAndDeptbyStepEmpId(@Param("empId") Integer empId);

    List<Empper> getDeptAndEmpsByStepTwo(@Param("deptId") Integer deptId);
}
