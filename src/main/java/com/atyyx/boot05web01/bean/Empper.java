package com.atyyx.boot05web01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.sql.In;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Empper {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Dept dept;
}
