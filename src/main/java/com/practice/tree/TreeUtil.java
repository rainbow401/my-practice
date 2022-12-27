package com.practice.tree;

import com.practice.test.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 树结构数据处理工具
 * @author yanzhihao
 */
public class TreeUtil {

    private <T> List<? extends Node<T>> sortNode(List<? extends Node<T>> nodes, Comparator<Node<T>> comparator) {
        nodes.sort(comparator);
        return nodes;
    }

    /**
     * 借助HashMap完成查询
     */
    public <T> List<Node<T>> getTreeByMap(List<? extends Node<T>> nodes) {
        List<Node<T>> resultList = new ArrayList<>();
        List<Node<T>> rootList = new ArrayList<>();
        HashMap<T, List<Node<T>>> childMap = new HashMap<>(48);

        for (Node<T> node : nodes) {
            T parentId = node.getParentId();
            if (parentId == null) {
                rootList.add(node);
            } else {
                List<Node<T>> value = childMap.get(parentId);
                if (value == null) {
                    value = new ArrayList<>();
                    value.add(node);

                    childMap.put(parentId, value);
                } else {
                    value.add(node);
                }
            }
        }

        for (Node<T> rootNode : rootList) {
            T id = rootNode.getId();
            List<Node<T>> childNode = getChildrenList(id, childMap);
            rootNode.setChild(childNode);
            resultList.add(rootNode);
        }

        return resultList;
    }

    private <T> List<Node<T>> getChildrenList(T id, HashMap<T, List<Node<T>>> childMap) {
        List<Node<T>> result = new ArrayList<>();
        List<Node<T>> childList = childMap.get(id);
        if (childList == null) {
            return null;
        }

        for (Node<T> node : childList) {
            T nodeId = node.getId();
            List<Node<T>> nodeChild = getChildrenList(nodeId, childMap);
            if (nodeChild != null) {
                node.setChild(nodeChild);
            }
            result.add(node);
        }

        return result;
    }
}
