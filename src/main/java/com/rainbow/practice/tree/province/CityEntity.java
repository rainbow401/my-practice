package com.rainbow.practice.tree.province;

import com.fasterxml.jackson.annotation.JsonView;
import com.rainbow.practice.tree.Node;
import lombok.ToString;

import java.util.List;

/**
 * @author yanzhihao
 * @date 2023/1/9
 * @Description 城市
 */
@ToString
@JsonView(CityEntity.View.class)
public class CityEntity implements Node<String, CityEntity> {
    private String province;

    private String abbreviate;

    private String name;

    private String code;

    private List<CityEntity> children;

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
    public List<CityEntity> getChild() {
        return children;
    }

    @Override
    public void setChild(List<CityEntity> child) {
        this.children = child;
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

    public interface View extends Node{

    }
}
