package com.rainbow.practice.tree;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * 树节点信息
 * @author yanzhihao
 */
public interface Node<T, R extends Node<T, R>> {

    /**
     * 获取节点的id
     * @return 节点的id
     */
    T getId();

    /**
     * 获取父节点的id
     * @return 父节点id
     */
    T getParentId();

    /**
     * 获取子节点数据
     * @return 子节点数据
     */
    List<R> getChild();

    /**
     * 设置子节点数据
     * @param child 数据
     */
    void setChild(List<R> child);
}
