package com.practice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestData implements Serializable {

    private Integer id;

    private String name;

    private static final long serialVersionUID = 2L;
}
