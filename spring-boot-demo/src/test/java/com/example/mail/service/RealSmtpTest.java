package com.example.mail.service;

import com.example.mail.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 submail smtp 测试邮件的实际发送效果。
 * <p>
 * 与 {@link MailService} 不同的是，后者是使用 GreenMail 模拟 smtp 进行测试，并不产生实际的发送操作。
 * <p>
 * 此处使用 mail.properties 覆盖 application.yml 中的默认配置。
 *
 * @author wjx
 * @version 1.0.0
 */
//@Ignore
@TestPropertySource("classpath:mail.properties")
public class RealSmtpTest extends AbstractTest {

    @Autowired
    MailService mailService;

    @Test
    public void send() throws InterruptedException {
        Email email = new Email.PlainEmailBuilder()
            .to("someok@qq.com")
            .subject("BMS 测试邮件发送")
            .body("这是个测试邮件").createEmail();
        mailService.sendText(email);

        Thread.sleep(3000);
    }

    @Test
    public void sendHtml() throws InterruptedException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("message", "测试 html 邮件");
        variables.put("toUserName", "张三");
        Email email = new Email.HtmlEmailBuilder()
            .to("wjianxu@outlook.com,wjianxu@gmail.com,someok@qq.com")
            .subject("BMS 测试 html 邮件")
            .templatePath("mail/demo/demo")
            .templateParams(variables)
            .dangerMessage("<strong>这是个高亮信息行</strong>")
            .createEmail();

        mailService.sendHtml(email);

        Thread.sleep(10000);
    }
}
