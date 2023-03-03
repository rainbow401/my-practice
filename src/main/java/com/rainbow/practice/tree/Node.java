package com.rainbow.practice.tree;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * 树节点信息
 * @author yanzhihao
 */
public interface Node<T> {

    /**
     * 获取节点的id
     * @return 节点的id
     */
    @JsonView(Node.View.class)
    T getId();

    /**
     * 获取父节点的id
     * @return 父节点id
     */
    @JsonView(Node.View.class)
    T getParentId();

    /**
     * 获取子节点数据
     * @return 子节点数据
     */
    @JsonView(Node.View.class)
    List<Node<T>> getChild();

    /**
     * 设置子节点数据
     * @param child 数据
     */
    void setChild(List<Node<T>> child);

    public interface View {}
}
