import 'package:flutter/material.dart';
import 'file:///C:/Users/k42un/Documents/workspace/projects/K42un0k0Force/front/lib/screens/login_screen.dart';

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
                onPressed:  (){
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
