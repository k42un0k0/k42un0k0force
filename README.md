# K42un0k0Force

## .npmrc
k42un0k0の自作のパッケージに依存しており、かつそのパッケージがGPRに存在するので、`.npmrc`をプロジェクトルートに作成して以下を記入してください

`${{token}}`は github の [personal access token](https://github.com/settings/tokens) から作成してください

```.npmrc
@k42un0k0:registry=https://npm.pkg.github.com
//npm.pkg.github.com/:_authToken=${{token}}
```