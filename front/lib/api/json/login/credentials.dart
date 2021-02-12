class Credentials {
  final String username;
  final String password;

  Credentials(this.username, this.password);

  Credentials.fromJson(Map<String, String> json)
      : username = json["username"],
        password = json["password"];

  Map<String, String> toJson() => {"username": username, "password": password};
}