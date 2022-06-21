package com.practice.domain.jsoup;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsoupController {

    /**
     * 从url下载文件
     */
    public void downloadPdf(Integer id1, Integer id2, String urlTemp,String head, Integer index, Integer count) throws IOException, InterruptedException {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println(id1);
            String url = String.format(urlTemp, id1, id2);
            System.out.println(url);
            Document document =  Jsoup.connect(url).get();
            String documentToString = document.getElementsByTag("script").html();
            int startIndex = documentToString.indexOf(head);
            int endIndex = startIndex + index;
            System.out.println(documentToString.substring(startIndex, endIndex));
            id1++;
            result.add(documentToString.substring(startIndex, endIndex));
            Thread.sleep(500);

            InputStream in = new URL(result.get(i)).openStream();
            // 将文件转换成字节数组
            byte[] bytes = IOUtils.toByteArray(in);
            // 导出路径和文件格式
            FileUtils.writeByteArrayToFile(new File("D:\\test.pdf"),bytes);
        }
        System.out.println(result);
        System.out.println(result.size());
    }
}
