package com.example.mail.service;

import com.example.mail.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

public class DemoLayoutServiceTest extends AbstractTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired DemoLayoutService demoLayoutService;

    @Test
    public void getDemoContent() {
        String body = demoLayoutService.getDemoContent();
        System.out.println(body);
        assertThat(body).isNotBlank()
            .contains("<strong>", "success", "张三");

    }
}
