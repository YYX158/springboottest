package com.atyyx.boot05web01.service.impl;

import com.atyyx.boot05web01.bean.Dept;
import com.atyyx.boot05web01.bean.Emp;
import com.atyyx.boot05web01.bean.Empper;
import com.atyyx.boot05web01.mapper.DeptMapper;
import com.atyyx.boot05web01.mapper.EmpMapper;
import com.atyyx.boot05web01.service.EmpService;
import com.atyyx.boot05web01.utils.SqlSessionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper,Empper> implements EmpService
{
    //private SqlSession sqlSession;
    @Autowired
    EmpMapper empMapper;
    @Autowired
    DeptMapper deptMapper;
    @Override
    public void getEmpperandDept() throws IOException {
//        sqlSession= SqlSessionUtils.getSqlSession();
//        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Empper emp = empMapper.getEmpAndDeptbyEmpId(1);
        System.out.println(emp.toString());
        // 关闭连接
        //sqlSession.close();
    }

    @Override
    public void getEmpAndDeptbyStepEmpId() {
        Empper empper = empMapper.getEmpAndDeptbyStepEmpId(1);
        System.out.println(empper.toString());
    }

    @Override
    public void getDeptAndEmpByDeptId()
    {
        Dept dept = deptMapper.getDeptAndEmpByDeptId(1);
        System.out.println(dept);
    }

    @Override
    public void getDeptAndEmpByStepOne()
    {
        Dept dept = deptMapper.getDeptAndEmpByStepOne(1);
        System.out.println(dept);
    }
}
