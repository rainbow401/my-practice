package com.practice.tree.province;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.practice.test.JsonUtil;
import com.practice.tree.CommonNode;
import com.practice.tree.Node;
import com.practice.tree.TreeUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yanzhihao
 * @date 2023/1/9
 * @Description
 */
public class TestDemo {

    public static void main(String[] args) throws IOException {
        String path = "src/main/java/com/practice/tree/province/dataList.json";
        List<CityEntity> list = JsonUtil.getListByFile(path, CityEntity.class);
        TreeUtil treeUtil = new TreeUtil();
        List<Node<String>> treeByMap = treeUtil.getTreeByMap(list);
        System.out.println(list);
        System.out.println(treeByMap);
        path = "src/main/java/com/practice/tree/province/result.json";
        JsonUtil.appendJsonStringToFile(path, JSONObject.toJSONString(treeByMap, true), 1);
    }


//    public static void main(String[] args) throws IOException {
//        String s = JsonUtil.readFile("src/main/java/com/practice/tree/province/province.json");
//        List<CityEntity> data = new ArrayList<>();
//        JSONArray jsonArray = JSONArray.parseArray(s);
//        Iterator iterator = jsonArray.stream().iterator();
//
//        while (iterator.hasNext()) {
//            JSONObject next = (JSONObject)iterator.next();
//            CityEntity cityEntity = next.toJavaObject(CityEntity.class);
//            JSONArray cities = next.getJSONArray("cities");
//            Iterator iterator1 = cities.stream().iterator();
//            while (iterator1.hasNext()) {
//                JSONObject next1 = (JSONObject)iterator1.next();
//                CityEntity cityEntity1 = next1.toJavaObject(CityEntity.class);
//                cityEntity1.setParentCode(cityEntity.getCode());
//                data.add(cityEntity1);
//            }
//            data.add(cityEntity);
//        }
//
//        String s1 = JSONArray.toJSONString(data, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
//        System.out.println("data = " + data);
////        System.out.println("s1 = " + s1);
//    }
}
