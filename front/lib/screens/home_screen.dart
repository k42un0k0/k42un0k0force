import 'package:flutter/material.dart';
import 'package:k42un0k0force/api/utils/uri_builder.dart';
import 'package:k42un0k0force/constants/urls.dart';
import 'package:k42un0k0force/screens/auth/login_screen.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Text("this is Home Screen"),
            ElevatedButton(
                onPressed: () {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => LoginScreen()));
                },
                child: Text('go Login Page'))
          ],
        ),
      ),
    );
  }
}
