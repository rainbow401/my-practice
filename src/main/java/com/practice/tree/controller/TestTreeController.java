package com.practice.tree.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.practice.test.JsonUtil;
import com.practice.threadlocal.userinfo.annotation.InjectUser;
import com.practice.tree.CommonNode;
import com.practice.tree.JsonResult;
import com.practice.tree.Node;
import com.practice.tree.TreeUtil;
import com.practice.tree.annotation.TreeNodeView;
import com.practice.tree.province.CityEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author yanzhihao
 * @date 2023/1/11
 * @Description
 */
@RestController
@RequestMapping("/tree")
public class TestTreeController {

//    @JsonView(TreeNodeView.class)
    @GetMapping("/test")
    public JsonResult<List<Node<String>>> getList() throws IOException {
        String path = "src/main/java/com/practice/tree/province/dataList.json";
        List<CityEntity> list = JsonUtil.getListByFile(path, CityEntity.class);
        TreeUtil treeUtil = new TreeUtil();

        return JsonResult.success("",treeUtil.getTreeByMap(list));
    }
}
