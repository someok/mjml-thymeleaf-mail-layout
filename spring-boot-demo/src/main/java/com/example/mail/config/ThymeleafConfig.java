package com.example.mail.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Thymeleaf 配置。
 */
@Configuration
public class ThymeleafConfig {

    /**
     * 增加 Thymeleaf Layout 支持
     *
     * @return {@link LayoutDialect}
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
