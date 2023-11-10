package com.rainbow.practice.tree;

import lombok.ToString;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * 通用数据载体
 * @author yanzhihao
 */
public class CommonNode implements Node<Integer, CommonNode> {

    private Integer id;

    private Integer parentId;

    private String data;

    private List<CommonNode> child;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public List<CommonNode> getChild() {
        return child;
    }

    @Override
    public void setChild(List<CommonNode> child) {
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

    public CommonNode(Integer id, Integer parentId, String data, List<CommonNode> child) {
        this.id = id;
        this.parentId = parentId;
        this.data = data;
        this.child = child;
    }

    public CommonNode() {
    }
}
