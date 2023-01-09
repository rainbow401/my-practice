package com.practice.tree.province;

import com.practice.tree.Node;

import java.util.List;

/**
 * @author yanzhihao
 * @date 2023/1/9
 * @Description 城市
 */
public class CityEntity implements Node<String> {

    private String province;

    private String abbreviate;

    private String name;

    private String code;

    private List<Node<String>> child;

    private String parentCode;



    @Override
    public String getId() {
        return code;
    }

    @Override
    public String getParentId() {
        return parentCode;
    }

    @Override
    public List<Node<String>> getChild() {
        return child;
    }

    @Override
    public void setChild(List<Node<String>> child) {
        this.child = child;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
