package com.example.mail.service;

import com.example.mail.config.AppProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 邮件发送。
 *
 * @author wjx
 * @version 1.0.0
 */
@Service
public class MailService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    AppProperties appProperties;

    @Autowired
    MailContentService mailContentService;

    private boolean isEnabled() {
        return this.appProperties.getMail().isEnabled();
    }

    /**
     * 实际的发送操作.
     *
     * @param email Email 内容
     * @param html  是否 HTML 格式
     */
    private void send(Email email, boolean html) {

        // 由于 submail 在 smtp 模式下发送邮件时，如果 to 是多个收件人，将只能成功发送第一个人
        // 所以这儿只好挨个发送
        String[] addresses = StringUtils.split(email.getTo(), ",");
        MimeMessagePreparator[] messagePreparators = new MimeMessagePreparator[addresses.length];

        for (int i = 0; i < addresses.length; i++) {
            String address = addresses[i];

            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

                if (!StringUtils.isBlank(email.getFrom())) {
                    messageHelper.setFrom(email.getFrom());
                } else {
                    messageHelper.setFrom(this.appProperties.getMail().getDefaultFrom());
                }

                // 邮件接收人可以是多个，以","分隔
                //                String[] addresses = StringUtils.split(email.getTo(), ",");
                messageHelper.setTo(address);
                messageHelper.setSubject(email.getSubject());
                messageHelper.setText(email.getBody(), html);
            };

            messagePreparators[i] = messagePreparator;
        }

        this.mailSender.send(messagePreparators);
    }

    @Async
    public void sendText(Email email) {
        if (!isEnabled()) {
            return;
        }

        send(email, false);
    }

    @Async
    public void sendHtml(Email email) {
        if (!isEnabled()) {
            return;
        }

        // 根据 thymeleaf 模板生成内容
        if (Objects.nonNull(email.getTemplatePath())) {
            if (Objects.nonNull(email.getTemplateParams())) {
                email.getTemplateParams().put("infoMessage", email.getInfoMessage());
                email.getTemplateParams().put("successMessage", email.getSuccessMessage());
                email.getTemplateParams().put("dangerMessage", email.getDangerMessage());
            }

            String body = mailContentService
                .htmlBody(email.getTemplatePath(), email.getTemplateParams());
            email.setBody(body);
        }

        send(email, true);
    }
}
