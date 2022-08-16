package com.atyyx.boot05web01.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
public class Emp implements Serializable {
    @TableId(value = "eid") // 声明这是主键
    private Integer eid;
    @Column(value = "emp_name")
    private String empName;
    @Column(value = "age")
    private Integer age;
    @Column(value = "sex")
    private String sex;
    @Column(value = "email")
    private String email;
}
