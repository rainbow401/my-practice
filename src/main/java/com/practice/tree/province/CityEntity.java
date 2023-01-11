package com.practice.tree.province;

import com.fasterxml.jackson.annotation.JsonView;
import com.practice.threadlocal.userinfo.annotation.InjectUser;
import com.practice.tree.Node;
import com.practice.tree.annotation.TreeNodeView;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author yanzhihao
 * @date 2023/1/9
 * @Description 城市
 */
@JsonView(CityEntity.View.class)
public class CityEntity implements Node<String> {
    private String province;

    private String abbreviate;

    private String name;

    private String code;

    private List<Node<String>> children;

    private String parentCode;



    @Override
    public String getId() {
        return code;
    }

    @Override
    public String getParentId() {
        return parentCode;
    }

    public List<Node<String>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<String>> children) {
        this.children = children;
    }

    @Override
    public List<Node<String>> getChild() {
        return children;
    }

    @Override
    public void setChild(List<Node<String>> child) {
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

    public interface View extends Node.View{

    }
}
