package com.atyyx.boot05web01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer dept_id;
    private String dept_name;
    private List<Empper> emps;
}
