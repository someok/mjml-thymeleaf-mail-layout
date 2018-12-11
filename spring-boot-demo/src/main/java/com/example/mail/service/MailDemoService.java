package com.example.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试 service。
 *
 * @author wjx
 * @version 1.0.0
 */
@Service
public class MailDemoService {
    private final MailContentService mailContentService;

    @Autowired
    public MailDemoService(MailContentService mailContentService) {
        this.mailContentService = mailContentService;
    }

    String send() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("toUserName", "张三");
        variables.put("infoMessage", "<strong>这是个 info 信息！</strong>");
        variables.put("message", "<strong>这是个一般信息！</strong>");

        return mailContentService.htmlBody("mail/demo/demo", variables);
    }

    String sendNoMessage() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("toUserName", "张三");
        variables.put("message", "<strong>这是个一般信息！</strong>");

        return mailContentService.htmlBody("mail/demo/demo", variables);
    }

    String sendNoVariables() {
        return mailContentService.htmlBody("mail/demo/demo", null);
    }

    String sendNoTemplate() {
        return mailContentService.htmlBody("mail/demo/notexist", null);
    }

    String ifData() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("successMessage", "这是个 success 信息！");
        variables.put("infoMessage", null);

        return mailContentService.htmlBody("mail/demo/if", variables);
    }
}
