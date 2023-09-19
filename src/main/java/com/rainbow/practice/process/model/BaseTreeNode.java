package com.rainbow.practice.process.model;

import com.rainbow.practice.tree.Node;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/9/19
 * @Description:
 */
@Getter
@Setter
public class BaseTreeNode implements Node<String, BaseTreeNode> {

    private String id;
    private String parentId;
    private List<BaseTreeNode> child;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setChild(List<BaseTreeNode> child) {

    }


}
