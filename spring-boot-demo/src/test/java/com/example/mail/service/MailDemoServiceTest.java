package com.example.mail.service;

import com.example.mail.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.exceptions.TemplateInputException;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

/**
 * @author wjx
 * @version 1.0.0
 */
public class MailDemoServiceTest extends AbstractTest {

    @Autowired MailDemoService mailDemoService;

    @Test
    public void send() throws IOException {
        String body = mailDemoService.send();
        HtmlTempFile.save("MailDemoServiceTest/send.html", body);
        System.out.println(body);
        assertThat(body).contains("<strong>这是个一般信息！</strong>",
                "<strong>这是个 info 信息！</strong>");
    }

    @Test
    public void sendNoMessage() throws IOException {
        String body = mailDemoService.sendNoMessage();
        HtmlTempFile.save("MailDemoServiceTest/sendNoMessage.html", body);
        System.out.println(body);
        assertThat(body).contains("<strong>这是个一般信息！</strong>")
                .doesNotContain("<strong>这是个 info 信息！</strong>");
    }

    @Test
    public void sendNoVariables() throws IOException {
        String body = mailDemoService.sendNoVariables();
        HtmlTempFile.save("MailDemoServiceTest/sendNoVariables.html", body);
        System.out.println(body);
        assertThat(body).contains("<p></p>");
    }

    @Test
    public void sendNoTemplate() {
        catchThrowableOfType(() -> mailDemoService.sendNoTemplate(),
                TemplateInputException.class);
    }

    @Test
    public void ifData() {
        String body = mailDemoService.ifData();
        System.out.println(body);
    }
}
