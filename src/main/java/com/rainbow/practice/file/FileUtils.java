package com.rainbow.practice.file;

import org.springframework.core.io.PathResource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class FileUtils {

    public void downloadNet(File file) throws MalformedURLException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        URL url;
        url = new URL("windine.blogdriver.com/logo.gif");

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(file);

            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file){
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendStringToFile(String path, String str) throws IOException {
        PathResource resource = new PathResource(path);
        FileWriter fw = null;
        PrintWriter write = null;
        File file = null;
        try {
            file = resource.getFile();
            fw = new FileWriter(file, true);
            write = new PrintWriter(fw);
            write.println(str);
            write.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert fw != null;
            fw.close();
            assert write != null;
            write.close();
        }
    }
}
