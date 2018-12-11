package com.example.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoLayoutService {
    @Autowired
    private MailContentService mailContentService;

    public String getDemoContent() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("message", "测试 html 邮件");
        variables.put("toUserName", "张三");
        variables.put("successMessage", "<strong>这是个 success 信息！</strong>");

        return mailContentService.htmlBody("mail/Demo1", variables);
    }
}
