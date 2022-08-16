package com.atyyx.boot05web01.mapper;

import com.atyyx.boot05web01.bean.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    /**
     * 通过分布查询来查询员工以及所对应部分的第二步
     * @return
     */
    public Dept getEmpAndDeptByStepTwo(@Param("d_id") Integer d_id);

    /**
     * 查询部门以及部门中的员工信息
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByDeptId(@Param("deptId") Integer deptId);


    /**
     * 通过分布查询来查询部门以及部门中的员工信息的第一步
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByStepOne(@Param("deptId") Integer deptId);


}
