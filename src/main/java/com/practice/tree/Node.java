package com.practice.tree;

import java.util.List;

/**
 * 树节点细心
 * @author yanzhihao
 */
public interface Node<T> {

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
     * 获取树节点数据
     * @return 树节点数据
     */

    /**
     * 获取子节点数据
     * @return 子节点数据
     */
    List<Node<T>> getChild();

    /**
     * 设置子节点数据
     */
    void setChild(List<Node<T>> child);
}
