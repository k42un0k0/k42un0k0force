import 'package:flutter/material.dart';

class UsersIndexScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text('UsersIndex'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text("this is UsersIndex Screen"),
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
