const {src, dest, series} = require('gulp');
const mjml = require('gulp-mjml');
const cleanDest = require('gulp-clean-dest');
const replace = require('gulp-replace');

/**
 * 将 mjml 文件编译成 html 模板
 */
function compileMjml() {
    return src('src/*.mjml')
        .pipe(mjml())
        .pipe(replace(' /*/ ', '/*/')) // 去掉前后空格
        .pipe(
            cleanDest('dist', {
                extension: '.html',
            })
        )
        .pipe(dest('dist'));
}

/**
 * 将生成的 html 拷贝到 spring boot 的 templates 下
 */
function copy2SpringBoot() {
    return src('dist/*.html').pipe(dest('../spring-boot-demo/src/main/resources/templates/mail'));
}

exports.compile = compileMjml;
exports.default = series(compileMjml, copy2SpringBoot);
