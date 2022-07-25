package com.practice.domain.mail;

import com.practice.utils.EmailUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/mail/")
public class MailController {

    @Resource
    private EmailUtil emailUtil;

    @PostMapping("/send")
    public void sendEmail(String to, String subject, String content) throws IOException {
        emailUtil.sendEmailMessage("273697776@qq.com", "yanzhihao0", "yanzhihao1");
    }
}
