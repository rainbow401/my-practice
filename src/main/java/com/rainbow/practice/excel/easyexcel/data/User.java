package com.rainbow.practice.excel.easyexcel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 * @Author: yzh
 * @Date: 2023/12/18
 * @Description:
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Data
public class User {

    @ExcelProperty
    private String id;

    @ExcelProperty
    private String name;

//    public User(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
