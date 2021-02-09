import 'package:flutter/material.dart';
import 'package:k42un0k0force/screens/users/users_index_screen.dart';

class LoginScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text("this is Login Screen"),
            ElevatedButton(onPressed: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => UsersIndexScreen()));
            }, child: Text('go UsersIndex Page'))
          ],
        ),
      ),
    );
  }
}
