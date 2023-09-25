package com.rainbow.practice.tree;

import java.util.*;

/**
 * 树结构数据处理工具
 * @author yanzhihao
 */
public class TreeUtils {

    private <T, R extends Node<T, R>> List<R> sortNode(List<R> nodes, Comparator<R> comparator) {
        nodes.sort(comparator);
        return nodes;
    }

    /**
     * 借助HashMap完成查询
     */
    public static <T, R extends Node<T, R>> List<R> getTreeByMap(Collection<R> nodes) {
        List<R> resultList = new ArrayList<>();
        List<R> rootList = new ArrayList<>();
        HashMap<T, List<R>> childMap = new HashMap<>(48);

        for (R node : nodes) {
            T parentId = node.getParentId();
            if (parentId == null) {
                rootList.add(node);
            } else {
                childMap.computeIfAbsent(parentId, (key) -> new ArrayList<>()).add(node);
            }
        }

        for (R rootNode : rootList) {
            T id = rootNode.getId();
            List<R> childNode = getChildrenList(id, childMap);
            rootNode.setChild(childNode);
            resultList.add(rootNode);
        }

        return resultList;
    }

    public static <T, R extends Node<T, R>> R getTreeRoot(Collection<R> nodes) {
        List<R> nodeList = getTreeByMap(nodes);

        if (nodeList.size() != 1) {
            throw new IllegalStateException("The number of root is " + nodeList.size());
        }

        return nodeList.get(0);
    }

    private static <T, R extends Node<T, R>> List<R> getChildrenList(T id, HashMap<T, List<R>> childMap) {
        List<R> result = new ArrayList<>();
        List<R> childList = childMap.get(id);
        if (childList == null) {
            return null;
        }

        for (R node : childList) {
            T nodeId = node.getId();
            List<R> nodeChild = getChildrenList(nodeId, childMap);
            if (nodeChild != null) {
                node.setChild(nodeChild);
            }
            result.add(node);
        }

        return result;
    }
}
