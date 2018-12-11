# mjml-thymeleaf-mail-layout

使用 MJML 标记语言配合 Thymeleaf 生成邮件模板

## 子项目说明

- mjml-template: mjml 模板项目，可以维护模板并编译发布 HTML
- spring-boot-demo: spring boot demo 项目，提供一些单元测试代码用于测试生成的 HTML 模板

## mjml-template 项目说明

具体参见 `mjml-template` 目录下的 [readme](mjml-template/README.md)

## spring-boot-demo 项目说明

### 单元测试

> .gradlew test

测试完成后会生成若干 HTML 文件，位置是： `${user.homt}/temp/mail_html/`，方便直接在浏览器中查看。
