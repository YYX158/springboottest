package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EmpperController {
    @Autowired
    com.atyyx.boot05web01.service.EmpService EmpService;

    @GetMapping("/getEmpAndDept")
    public void getEmpAndDept() throws IOException {
        EmpService.getEmpperandDept();
        System.out.println("============下面使用分步查询================");
        EmpService.getEmpAndDeptbyStepEmpId();
    }

    @GetMapping("/getDeptAndEmpByDeptId")
    public void getDeptAndEmpByDeptId()
    {
        EmpService.getDeptAndEmpByDeptId();
    }

    @GetMapping("/getDeptAndEmpByStepOne")
    public void getDeptAndEmpByStepOne()
    {
        EmpService.getDeptAndEmpByStepOne();
    }
}
