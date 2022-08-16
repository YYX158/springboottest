package com.atyyx.boot05web01.service;

import com.atyyx.boot05web01.bean.Empper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;


import java.io.IOException;

public interface EmpService extends IService<Empper> {
    public void  getEmpperandDept() throws IOException;

    public void getEmpAndDeptbyStepEmpId();


    public void getDeptAndEmpByDeptId();

    public void getDeptAndEmpByStepOne();
}
