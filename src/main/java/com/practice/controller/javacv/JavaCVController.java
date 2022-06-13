package com.practice.controller.javacv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/javacv")
public class JavaCVController {

    @GetMapping("/convert")
    public void convertVideo(String videoStream, String outputPath) throws IOException {
        System.out.println(videoStream);
        System.out.println(outputPath);
        try {
            FrameRecord frame = new FrameRecord();
            frame.frameRecord(videoStream, outputPath, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("结束");
    }

    @GetMapping("/local/convert")
    public boolean convertLocalVideo(String videoStream, String outputPath) throws IOException {
        File file = new File(videoStream);
        System.out.println(file.getPath());
        if(file.exists()) {
            try {
                FrameRecord frame = new FrameRecord();
                frame.frameRecord(file.getPath(), outputPath, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        } else {
            return false;
        }

    }
}
