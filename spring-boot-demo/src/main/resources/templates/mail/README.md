# 邮件模板说明

邮件模板使用的是 thymeleaf 实现，spring boot 已经开启 thymeleaf 支持。

## 布局

布局页面为 `layout.html`，此页面由 `layout.mjml` 生成（采用 [try-it-live](https://mjml.io/try-it-live)）。

> [mjml 官网](https://mjml.io/)

`layout.mjml` 中基本已经整合了 thymeleaf 的相关属性，不过为了满足 h5 验证，使用了 `data-th-*` 模式。

### mjml 生成后调整

- html 标签需增加 `data-th-fragment="layout(content, preview)"`
- 高亮信息采用的是 `<!--/*/ <th:block th:if="${infoMessage} != null"> /*/-->`，在生成之后会在 `/*/` 前后增加空格，需要将此空格删除

## 常用组件

`component.html` 中定义了一些常用的页面组件，如横线、按钮之类的：

- 按钮
    - infoButton
    - successButton
    - dangerButton
- 提示信息行
    - infoMessage
    - successMessage
    - dangerMessage
- divider: 横线


## 业务模板

具体的业务模板为正常的 H5 页面，需要在 `html` 标签上添加：

> data-th-replace="mail/layout :: layout(~{::section}, ~{:: #preview})"

在 `body` 中通过两个根标签实现内容注入：

- preview: 邮件预览内容
    > `<div id="preview">...</div>`
- section: 实际的邮件内容区域

具体可参见：`demo/demo.html`。