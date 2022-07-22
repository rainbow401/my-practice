package com.practice.domain.mail;

import com.practice.utils.EmailUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/mail/")
public class MailController {

    @Resource
    private EmailUtil emailUtil;

    @PostMapping("/send")
    public void sendEmail(String to, String subject, String content) throws IOException {
        emailUtil.sendEmailMessage("273697776@qq.com", "yanzhihao0", "yanzhihao1", "yanzhihao2");
    }
}
