package com.practice.tree.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.practice.response.ResponseResult;
import com.practice.test.JsonUtil;
import com.practice.threadlocal.userinfo.context.UserInfoService;
import com.practice.tree.Node;
import com.practice.tree.TreeUtil;
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

    private UserInfoService userInfoService;

    @JsonView(Node.View.class)
    @GetMapping("/test")
    public ResponseResult<List<Node<String>>> getList() throws IOException {
        String path = "src/main/java/com/practice/tree/province/dataList.json";
        List<CityEntity> list = JsonUtil.getListByFile(path, CityEntity.class);
        TreeUtil treeUtil = new TreeUtil();

        return ResponseResult.success(treeUtil.getTreeByMap(list));
    }
}
