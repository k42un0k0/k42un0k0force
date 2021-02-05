# タスク管理ツールを使ってないのでメモ代わり

- spring securityのよさげなきーたを見つけた(emploeeへの移行に対しても使用もできそう)
- h2consoleにアクセスするため、csrfとframe denyを無効化
    - まぁまぁ機能への影響がでかいので今後どうするか考える

# k42un0k0force backend

## name convention

### which add a suffix or prefix? interface vs implement

調査の結果、C#ではインターフェースには`I`の接頭辞をつけるらしい

だが、Javaでは実装に`Imple`の接尾辞をつけるらしい

今回はJavaなのでインターフェースには何もつけず実装に`Imple`をつけることにする