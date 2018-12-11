package com.example.mail.config;

import com.example.mail.config.app.Mail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本应用的一些自定义配置。
 *
 * @author wjx
 * @version 1.0.0
 */
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * 邮件配置
     */
    private Mail mail = new Mail();

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }
}
