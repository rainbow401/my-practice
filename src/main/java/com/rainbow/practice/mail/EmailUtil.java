package com.rainbow.practice.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @ClassName EmailUtil
 * @Description 邮件发送工具
 * @Author Sophia
 * @Date 2022/4/6 16:06
 */
@Slf4j
@Component
public class EmailUtil {

    @Value("${spring.mail.from}") // 从application.yml配置文件中获取
    private String from; // 发送发邮箱地址

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    /**
     * 发送纯文本邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     */
    public void sendMessage(String to, String subject, String content) {
        // 创建一个邮件对象
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from); // 设置发送发
        msg.setTo(to); // 设置接收方
        msg.setSubject(subject); // 设置邮件主题
        msg.setText(content); // 设置邮件内容
        // 发送邮件
        mailSender.send(msg);
    }

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param files 文件数组 // 可发送多个附件
     */
    public void sendMessageCarryFiles(String to, String subject, String content, File[] files) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from); // 设置发送发
            helper.setTo(to); // 设置接收方
            helper.setSubject(subject); // 设置邮件主题
            helper.setText(content); // 设置邮件内容
            if (files != null && files.length > 0) { // 添加附件（多个）
                for (File file : files) {
                    helper.addAttachment(file.getName(), file);
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }
/**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param file 单个文件
     */
    public void sendMessageCarryFile(String to, String subject, String content, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from); // 设置发送发
            helper.setTo(to); // 设置接收方
            helper.setSubject(subject); // 设置邮件主题
            helper.setText(content); // 设置邮件内容
            helper.addAttachment(file.getName(), file); // 单个附件
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件
     * @param toEmails 接收方
     * @param from 发送方
     * @param subject 主题
     * @param mailTemplatePath 邮件模板路径 eg: "/index",即 resources/templates/index.html
     * @param param 替换模板中的参数
     */
    public void sendEmail(String[] toEmails, String from, String subject, String mailTemplatePath, Map<String, Object> param) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(toEmails);
        helper.setFrom(from);

        // 替换html中的参数
        Context context = new Context();
        context.setVariables(param);
        String htmlText = templateEngine.process(mailTemplatePath, context);
        helper.setText(htmlText, true);

        // 发送邮件
        mailSender.send(message);
    }

    /**
     * 发送邮件
     * @param toEmails 接收方
     * @param from 发送方
     * @param subject 主题
     * @param mailTemplatePath 邮件模板路径 eg: "/index",即 resources/templates/index.html
     * @param param 替换模板中的参数
     */
    public void sendEmailWithAttachments(String[] toEmails, String from, String subject, String mailTemplatePath, Map<String, Object> param, Map<String, File> files) throws MessagingException, IOException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(toEmails);
        helper.setFrom(from);

        // 添加附件
        for (Map.Entry<String, File> entry : files.entrySet()) {
            helper.addAttachment(entry.getKey(), entry.getValue());
        }


        // 替换html中的参数
        Context context = new Context();
        context.setVariables(param);
        String htmlText = templateEngine.process(mailTemplatePath, context);
        helper.setText(htmlText, true);

        // 发送邮件
        mailSender.send(message);
    }

    /**
     * 替换模板文件中的参数
     * {0}，{1}，{2} 数字代表顺序
     * @param params 替换值
     * @return 替换后的html文件字符串
     */
    public String buildHtmlContent(Object ...params) throws IOException {
        ClassPathResource resource = new ClassPathResource("/templates/index.html");
        //替换html模板中的参数
        return MessageFormat.format(StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8), params);
    }
}

