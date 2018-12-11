# 邮件模板说明

邮件模板使用的是 thymeleaf 实现，spring boot 已经开启 thymeleaf 支持。

## mjml 使用技巧

- thymeleaf 支持 `data-th-*` 格式注入，可以在编辑器中不会出现 H5 页面错误提示
- thymeleaf 支持 `注释` 方式的注入，配合 `mjml`（支持 xml 格式注释）可以比较方便地控制条件。
    例如：
    ```
    <!--/*/ <section data-th-fragment="divider"> /*/-->
    <mj-section padding="0">
      <mj-column>
        <mj-divider />
      </mj-column>
    </mj-section>
    <!--/*/ </section> /*/-->
    ```

    注意：上面的 `/*/` 在生成 html 之后可能会出现空格，而这个在 thymeleaf 上面是不支持的，所以使用 `gulp` 来编译并做相应的替换操作。

## 布局

布局页面为 `Layout.html`，此页面由 `Layout.mjml` 生成（采用 gulp 编译）。

> [mjml 官网](https://mjml.io/)

`Layout.mjml` 中基本已经整合了 thymeleaf 的相关属性，不过为了满足 h5 验证，使用了 `data-th-*` 模式。

## 常用组件

`Comp.html` 中定义了一些常用的页面组件，如横线、按钮之类的：

- 按钮
    - infoButton
    - successButton
    - dangerButton
- divider: 横线
- spacer: 50px 的空白行


## 业务模板

具体的业务模板为正常的 H5 页面，需要在 `html` 标签上添加：

> data-layout-decorate="~{mail/Layout}"

在 `body` 中通过两个根标签实现内容注入：

### preview: 邮件预览内容（可选）
```
<div data-layout-fragment="preview">
    这是个 preview 信息
</div>
```

### section: 实际的邮件内容区域
```
<section data-layout-fragment="content">
...
</section>
```

具体可参见：`spring-boot-demo/src/main/resources/templates/mail/demo/demo.html`。

## gulp 命令

- `npm run compile`: 编译 `src` 下的 `mjml` 文件，并发布到 `dist` 目录下
- `npm start`: 编译并将生成的 HTML 拷贝到 `spring-boot-demo/src/main/resources/templates/mail/`
