package com.practice.domain.mail;

import com.practice.utils.EmailUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.util.URLUtil;
import sun.plugin2.message.helper.URLHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail/")
public class MailController {

    @Resource
    private EmailUtil emailUtil;

    @PostMapping("/send")
    public void sendEmail(String path) throws MessagingException {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "你好");
        param.put("code", "abcd");
        emailUtil.sendEmail(new String[]{"273697776@qq.com", "yanzhihao@zhongruigroup.com"}, "273697776@qq.com", "通知", path, param);
    }

    @PostMapping("/attachment/send")
    public void sendEmailWithAttachment(String path, String fileUrl) throws MessagingException, URISyntaxException, IOException {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "你好");
        param.put("code", "abcd");
        Map<String, File> fileMap = new HashMap<>();


        UrlResource resource = new UrlResource(fileUrl);
        fileMap.put("file1.png", new File("C:\\Users\\lenovo\\Desktop\\企业微信截图_16566577127765.png"));
        fileMap.put("file2.png", new File("C:\\Users\\lenovo\\Desktop\\企业微信截图_16566577127765.png"));



        emailUtil.sendEmailWithAttachments(
                new String[]{"273697776@qq.com", "yanzhihao@zhongruigroup.com"},
                "273697776@qq.com",
                "通知",
                path,
                param,
                fileMap
        );
    }


}
