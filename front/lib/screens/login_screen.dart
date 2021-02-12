import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:k42un0k0force/api/constants/urls.dart';
import 'package:k42un0k0force/api/json/login/credentials.dart';
import 'package:k42un0k0force/api/utils/uri_builder.dart';
import 'package:http/http.dart' as http;

class MyCookies {
  String header;
  Map<String, String> _cookies = {};

  MyCookies(String cookie) {
    header = cookie;
    _cookies = cookie.split(";").fold({}, (previousValue, item) {
      var keyValue = item.split("=");
      print(keyValue);
      if (keyValue.length == 2) {
        String key = keyValue[0];
        String value = keyValue[1];
        previousValue[key] = value;
      }
      return previousValue;
    });
  }

  String getValue(String name) {
    try {
      return _cookies[name];
    } catch (e) {
      return "";
    }
  }
}

class LoginScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => LoginScreenState();
}

class LoginScreenState extends State<LoginScreen> {
  MyCookies _cookies;

  void updateCookie(String cookie) =>
      setState(() => _cookies = MyCookies(cookie));

  Map<String, String> getHeaders() => {
        "Content-Type": "application/json",
        "X-XSRF-TOKEN": _cookies?.getValue("XSRF-TOKEN"),
        "Cookie": _cookies?.header,
      };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text("this is Login Screen"),
            ElevatedButton(
              child: Text('login'),
              onPressed: () {
                http
                    .post(RootUriBuilder(apiUrl).path("login").build(),
                        body:
                            jsonEncode(Credentials("hello", "world").toJson()),
                        headers: getHeaders())
                    .then((response) {
                  print('Response status: ${response.statusCode}');
                  print('Response header: ${response.headers}');
                  print('Response body: ${response.body}');
                  var cookie = response.headers["set-cookie"];
                  if (cookie != null) {
                    updateCookie(cookie);
                  }
                });
              },
            ),
            ElevatedButton(
              child: Text('user page'),
              onPressed: () {
                http
                    .get(RootUriBuilder(apiUrl).users().build(),
                        headers: getHeaders())
                    .then((response) {
                  print('Response status: ${response.statusCode}');
                  print('Response header: ${response.headers}');
                  print('Response body: ${response.body}');
                });
              },
            ),
            ElevatedButton(
              child: Text('logout'),
              onPressed: () {
                http
                    .post(RootUriBuilder(apiUrl).path("logout").build(),
                        headers: getHeaders())
                    .then((response) {
                  print('Response status: ${response.statusCode}');
                  print('Response header: ${response.headers}');
                  print('Response body: ${response.body}');
                  updateCookie("");
                });
              },
            )
          ],
        ),
      ),
    );
  }
}
