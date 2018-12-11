const {src, dest, series} = require('gulp');
const mjml = require('gulp-mjml');
const cleanDest = require('gulp-clean-dest');
const replace = require('gulp-replace');

/**
 * 将 mjml 文件编译成 html 模板
 *
 * 编译中需要将 &lt;!-- \/*\/ 中的空格去掉
 */
function compileMjml() {
    return src('src/*.mjml')
        .pipe(mjml())
        .pipe(replace(' /*/ ', '/*/'))
        .pipe(cleanDest('dist', {extension: '.html'}))
        .pipe(dest('dist'));
}

exports.default = series(compileMjml);
