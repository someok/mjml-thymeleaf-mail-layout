package com.example.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * 使用 thymeleaf 渲染出 html 格式的邮件内容。
 *
 * @author wjx
 * @version 1.0.0
 */
@Service
public class MailContentService {
    private TemplateEngine templateEngine;

    @Autowired
    public MailContentService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * html 格式邮件内容。
     *
     * @param template  模板路径
     * @param variables 模板中需要的参数
     * @return html 邮件内容
     */
    public String htmlBody(String template, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(template, context);
    }
}
