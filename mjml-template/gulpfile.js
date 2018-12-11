const { src, dest, series } = require('gulp');
const mjml = require('gulp-mjml');
const cleanDest = require('gulp-clean-dest');
const replace = require('gulp-replace');
const header = require('gulp-header');

const pkg = require('./package.json');
const banner = `<!--/*
    本文件通过 gulp 编译 mjml 生成
    !!!请不要直接修改!!!

    ${pkg.name} - ${pkg.description}
    @version v${pkg.version}
*/-->
`;

/**
 * 将 mjml 文件编译成 html 模板
 */
function compileMjml() {
    return src('src/*.mjml')
        .pipe(mjml())
        .pipe(replace(' /*/ ', '/*/')) // 去掉前后空格
        .pipe(header(banner))
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
