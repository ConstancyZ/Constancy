package com.yuanchuang.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5721371597294840376L;

    private Integer id;

    private String username;

    private String password;

    private Double salary;


}