const gulp = require('gulp');
const babel = require("gulp-babel");
const environments = require('gulp-environments');
const uglifycss = require('gulp-uglifycss');
const terser = require('gulp-terser');
const postcss = require('gulp-postcss');
const purgecss = require('gulp-purgecss');
const changed  = require('gulp-changed');
const imagemin = require('gulp-imagemin');
const imageminJpg = require('imagemin-jpeg-recompress');
const imageminPng = require('imagemin-pngquant');
const imageminGif = require('imagemin-gifsicle');
const svgmin = require('gulp-svgmin');

const production = environments.production;

const distPath = 'src/main/resources/static';

const generateAssetsPath = (ext)=>`assets/**/*.${ext}`

const cssPath = generateAssetsPath("css")
const jsPath = generateAssetsPath("js")
const imgPath = generateAssetsPath("+(png|jgp|jpeg|gif)")
const svgPath = generateAssetsPath("svg")

gulp.task('watch', () => {
    gulp.watch([cssPath], gulp.series('copy-css'));
    gulp.watch([jsPath], gulp.series('copy-js'));
    gulp.watch([imgPath], gulp.series('copy-img'));
    gulp.watch([svgPath], gulp.series('copy-svg'));
});

gulp.task('copy-css', () =>
    gulp.src([cssPath])
        .pipe(postcss([
            require('tailwindcss'),
            require('autoprefixer'),
        ]))
        .pipe(production(purgecss({
            content: ['src/main/resources/templates/**/*.html']
        })))
        .pipe(production(uglifycss()))
        .pipe(gulp.dest(distPath))
);

gulp.task('copy-js', () =>
    gulp.src([jsPath])
        .pipe(babel())
        .pipe(production(terser()))
        .pipe(gulp.dest(distPath))
);

gulp.task('copy-img', () =>
    gulp.src([imgPath])
        .pipe(changed(distPath))
        .pipe(imagemin([
                imageminPng(),
                imageminJpg(),
                imageminGif({
                    interlaced: false,
                    optimizationLevel: 3,
                    colors:180
                })
        ]))
        .pipe(gulp.dest(distPath))
);

gulp.task('copy-svg', function(){
    gulp.src(['assets/**/*.svg'])
        .pipe(svgmin())
        .pipe(gulp.dest(distPath))
});

gulp.task('build', gulp.series('copy-css', 'copy-js', 'copy-img','copy-svg'));
gulp.task('default', gulp.series('watch'));