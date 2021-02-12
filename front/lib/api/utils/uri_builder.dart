
// Interfaces

import 'package:k42un0k0force/api/constants/urls.dart';

abstract class BuildBuilder<T> {
  T get _instance;
  String _baseUri;
  String _localPath;
  Map<String, String> _queries;

  BuildBuilder(this._baseUri, this._localPath, this._queries);

  T path(String str) {
    _localPath += "/$str";
    return _instance;
  }

  T addQuery(String key, String value) {
    _queries[key] = value;
    return _instance;
  }

  T setQueries(Map<String, String> queries) {
    _queries = queries;
    return _instance;
  }

  Uri build() => Uri.http(_baseUri, _localPath, _queries);
}

mixin IdBuilder<T> on BuildBuilder<T>{
  T id(int id){
    return path("$id");
  }
}


// Implements

/// トップのルート用のビルダー
///
/// メソッドを実行することで下位のビルダーが生成される、そのメソッドを実行するとさらに下位のビルダーが生成垂れる
/// 最終的に[build]を実行することで[Uri]が生成される
class RootUriBuilder extends BuildBuilder<RootUriBuilder> {
  /// [baseUri]は生成される[Uri]の先頭につく
  RootUriBuilder(
      String baseUri)
      : super(baseUri, "", {});

  @override
  RootUriBuilder get _instance => this;

  /// "/auth"以下のルーティング用のビルダーを返す
  AuthUriBuilder auth() {
    return AuthUriBuilder(_baseUri, _localPath, _queries).path("auth");
  }

  /// "/users"以下のルーティング用のビルダーを返す
  UsersUriBuilder users() {
    return UsersUriBuilder(_baseUri, _localPath, _queries).path("users");
  }
}

class UsersUriBuilder extends BuildBuilder<UsersUriBuilder> with IdBuilder {
  UsersUriBuilder(
      String _baseUri, String _localPath, Map<String, String> _queries)
      : super(_baseUri, _localPath, _queries);

  @override
  UsersUriBuilder get _instance => this;
}

class AuthUriBuilder extends BuildBuilder<AuthUriBuilder> {
  AuthUriBuilder(
      String _baseUri, String _localPath, Map<String, String> _queries)
      : super(_baseUri, _localPath, _queries);
  @override
  AuthUriBuilder get _instance => this;
}

// Variables

var apiUriBuilder = RootUriBuilder(apiUrl);