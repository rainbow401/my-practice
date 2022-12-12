package com.practice.tree;

import com.practice.test.JsonUtil;

import java.io.IOException;
import java.util.List;

/**
 * @author yanzhihao
 */
public class TestDemo {

    public static void main(String[] args) throws IOException {
        String path = "src/main/java/com/practice/tree/data.json";
        List<CommonNode> list = JsonUtil.getListByFile(path, CommonNode.class);
        TreeUtil<Integer> treeUtil = new TreeUtil<>();
        List<Node<Integer>> treeByMap = treeUtil.getTreeByMap(list);
        System.out.println(list);
        System.out.println(treeByMap);
    }
}
