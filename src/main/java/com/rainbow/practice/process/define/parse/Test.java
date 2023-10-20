package com.rainbow.practice.process.define.parse;

import com.rainbow.practice.process.define.element.Base;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/9/20
 * @Description:
 */
public class Test {

    public static final String PATH = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\process\\testdata\\data.json";

    public static void main(String[] args) throws IOException {
        String s = FileUtils.readFileToString(new File(PATH), "UTF-8");

        ProcessDefineParser processDefineParser = new ProcessDefineParser();
        List<Base> data = processDefineParser.parseJson(s);
        processDefineParser.parseTree(data);
    }
}
