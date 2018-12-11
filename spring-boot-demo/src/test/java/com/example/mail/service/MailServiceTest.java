package com.example.mail.service;

import com.example.mail.AbstractTest;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * 使用 GreenMail 模拟 smtp 进行测试
 *
 * @author wjx
 * @version 1.0.0
 */
public class MailServiceTest extends AbstractTest {

    @Rule
    public final GreenMailRule greenMail = new GreenMailRule(
            ServerSetupTest.SMTP);

    @Autowired
    MailService mailService;

    @Autowired
    MailContentService mailContentService;

    @Test
    public void sendText() throws MessagingException, IOException {
        Email email = new Email.PlainEmailBuilder()
                .to("someok@qq.com")
                .subject("BMS 测试邮件发送")
                .body("这是个测试邮件")
                .createEmail();
        mailService.sendText(email);

        // 因为是异步操作，所以这儿需要等待
        assertThat(greenMail.waitForIncomingEmail(1)).isTrue();

        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages).hasSize(1);
        System.out.println(messages[0]);
        String content = (String) messages[0].getContent();
        assertThat(content).isEqualTo(email.getBody());
        assertThat(messages[0].getSubject()).isEqualTo(email.getSubject());
    }

    @Test
    public void sendHtml() throws MessagingException, IOException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("message", "测试 html 邮件");
        Email email = new Email.HtmlEmailBuilder()
                .to("someok@qq.com")
                .subject("BMS 测试 html 邮件")
                .templatePath("mail/demo/demo")
                .templateParams(variables)
                .dangerMessage("这是个 danger 信息")
                .createEmail();
        mailService.sendHtml(email);

        Map<String, Object> params = new HashMap<>(variables);
        params.put("dangerMessage", email.getDangerMessage());
        String body = mailContentService
                .htmlBody(email.getTemplatePath(), params);

        HtmlTempFile.save("MailServiceTest/sendHtml.html", body);

        assertThat(greenMail.waitForIncomingEmail(1)).isTrue();

        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages).hasSize(1);
        assertThat(messages[0].getSubject()).isEqualTo(email.getSubject());

        String content = (String) messages[0].getContent();
        // 由于邮件返回的 html 的换行符是 CRLF 格式的
        // 所以这儿为了比较需要将其转换为 LF
        content = content.replace("\r", "");
        assertThat(content).isEqualTo(body);
    }
}
