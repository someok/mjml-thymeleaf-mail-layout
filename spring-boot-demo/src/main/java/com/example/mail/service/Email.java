package com.example.mail.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangjxe
 */
public class Email implements Serializable {
    private static final long serialVersionUID = -800564180149934419L;

    /**
     * 邮件接收方
     * <p>
     * 如果是多个邮件地址，需要用逗号分隔
     */
    private String to;

    private String from;

    private String subject;

    private String body;

    private boolean plainText = false;

    /**
     * 如果提供了模板路径，则忽略 body 属性
     */
    private String templatePath;

    private Map<String, Object> templateParams = new HashMap<>();

    private String infoMessage;

    private String successMessage;

    private String dangerMessage;

    public Email() {
    }

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;

        this.plainText = true;
    }

    public Email(String to, String subject, String templatePath,
        Map<String, Object> templateParams,
        String infoMessage, String successMessage, String dangerMessage) {
        this.to = to;
        this.subject = subject;
        this.templatePath = templatePath;
        this.templateParams = templateParams;
        this.infoMessage = infoMessage;
        this.successMessage = successMessage;
        this.dangerMessage = dangerMessage;

        this.plainText = false;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isPlainText() {
        return plainText;
    }

    public void setPlainText(boolean plainText) {
        this.plainText = plainText;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Map<String, Object> getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(Map<String, Object> templateParams) {
        this.templateParams = templateParams;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getDangerMessage() {
        return dangerMessage;
    }

    public void setDangerMessage(String dangerMessage) {
        this.dangerMessage = dangerMessage;
    }

    public static class PlainEmailBuilder {
        private String to;

        private String subject;

        private String body;

        public PlainEmailBuilder to(String to) {
            this.to = to;
            return this;
        }

        public PlainEmailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public PlainEmailBuilder body(String body) {
            this.body = body;
            return this;
        }

        public Email createEmail() {
            return new Email(to, subject, body);
        }

    }

    public static class HtmlEmailBuilder {
        private String to;

        private String subject;

        private String templatePath;

        private Map<String, Object> templateParams = new HashMap<>();

        private String infoMessage;

        private String successMessage;

        private String dangerMessage;

        public HtmlEmailBuilder to(String to) {
            this.to = to;
            return this;
        }

        public HtmlEmailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public HtmlEmailBuilder templatePath(String templatePath) {
            this.templatePath = templatePath;
            return this;
        }

        public HtmlEmailBuilder templateParams(Map<String, Object> templateParams) {
            this.templateParams = templateParams;
            return this;
        }

        public HtmlEmailBuilder addParams(String key, Object value) {
            if (Objects.isNull(this.templateParams)) {
                this.templateParams = new HashMap<>();
            }

            this.templateParams.put(key, value);
            return this;
        }

        public HtmlEmailBuilder infoMessage(String infoMessage) {
            this.infoMessage = infoMessage;
            return this;
        }

        public HtmlEmailBuilder successMessage(String successMessage) {
            this.successMessage = successMessage;
            return this;
        }

        public HtmlEmailBuilder dangerMessage(String dangerMessage) {
            this.dangerMessage = dangerMessage;
            return this;
        }

        public Email createEmail() {
            return new Email(to, subject, templatePath, templateParams,
                infoMessage, successMessage, dangerMessage);
        }
    }

}
