const gulp = require('gulp');
const babel = require("gulp-babel");
const browserSync = require('browser-sync').create();
const environments = require('gulp-environments');
const uglifycss = require('gulp-uglifycss');
const terser = require('gulp-terser');
const postcss = require('gulp-postcss');
const purgecss = require('gulp-purgecss');

const production = environments.production;

gulp.task('watch', () => {
    browserSync.init({
        proxy: 'localhost:8080',
    });

    gulp.watch(['assets/**/*.css'], gulp.series('copy-css-and-reload'));
    gulp.watch(['assets/**/*.js'], gulp.series('copy-js-and-reload'));
});

gulp.task('copy-css', () =>
    gulp.src(['assets/**/*.css'])
        .pipe(postcss([
            require('tailwindcss'),
            require('autoprefixer'),
        ]))
        .pipe(production(purgecss({
            content: ['src/main/resources/templates/**/*.html']
        })))
        .pipe(production(uglifycss()))
        .pipe(gulp.dest('src/main/resources/static'))
);

gulp.task('copy-js', () =>
    gulp.src(['assets/**/*.js'])
        .pipe(babel())
        .pipe(production(terser()))
        .pipe(gulp.dest('src/main/resources/static'))
);

gulp.task('copy-css-and-reload', gulp.series('copy-css', reload));
gulp.task('copy-js-and-reload', gulp.series('copy-js', reload));

gulp.task('build', gulp.series('copy-css', 'copy-js'));
gulp.task('default', gulp.series('watch'));

function reload(done) {
    browserSync.reload();
    done();
}