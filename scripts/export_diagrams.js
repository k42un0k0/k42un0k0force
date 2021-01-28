const chalk = require("chalk");
var glob = require('glob');
const { exec } = require('child_process');
const { cbToPromise } = require("./utils");
const path = require("path");

const pGlob = cbToPromise(glob);
const pExec = cbToPromise(exec);

const EXECUTE_PROCESS_PATH = "./";

async function main() {
    const inputDirName = 'doc';
    const outputDirName = 'directory_contains_actual_images';
    const jarFilePath = path.resolve(EXECUTE_PROCESS_PATH, 'scripts/plantuml.jar');

    console.log(chalk.green('export diagrams'));

    let files = [];
    try {
        // inputDirNameから*.puを検索
        files = await findAllPuml(inputDirName);
    } catch (error) {
        console.log(chalk.red(error));
        process.exit(-1);
    }

    // すべての.pu を全て画像化
    const promises = files.map(pumlToImage(inputDirName, outputDirName, jarFilePath));

    await Promise.all(promises);
    console.log(chalk.green('Done!!'));
}

function findAllPuml(rootDir) {
    console.log("find puml files");
    let globPattern = "**/*.pu";
    if (rootDir) {
        globPattern = `${rootDir}/${globPattern}`;
    }
    return pGlob(globPattern);
}

function pumlToImage(fromDirName, toDirName, jarFilePath) {
    console.log(`export image to ${toDirName}`);

    return function (file) {
        // 相対pathで出力用フォルダを指定する
        function generateOutputPath() {
            const fileDirPath = path.dirname(file);
            return path.join(path.relative(fileDirPath, EXECUTE_PROCESS_PATH), fileDirPath.replace(RegExp(fromDirName, "g"), toDirName));
        }
        const outputPath = generateOutputPath()
        console.log(`from ${file}`);
        return pExec(`java -Dfile.encoding=UTF-8 -jar ${jarFilePath} ${file} -o ${outputPath}`).then((res) => {
            console.log(`output of ${file}: ` + res);
            return res;
        })
    }
}

main();