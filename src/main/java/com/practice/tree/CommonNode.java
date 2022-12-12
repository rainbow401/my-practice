package com.practice.tree;

import lombok.ToString;

import java.util.List;

/**
 * 通用数据载体
 * @author yanzhihao
 */
@ToString
public class CommonNode implements Node<Integer> {

    private Integer id;

    private Integer parentId;

    private String data;

    private List<Node<Integer>> child;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public List<Node<Integer>> getChild() {
        return child;
    }

    @Override
    public void setChild(List<Node<Integer>> child) {
        this.child = child;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
