
package com.rainbow.practice.mysqlview.model;

import lombok.Data;

import javax.annotation.Generated;


@Data
public class Column {

    private String comment;
    private String extra;
    private String field;
    private String key;
    private String isNull;
    private String privileges;
    private String type;

}
