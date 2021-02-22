import 'package:flutter/material.dart';
import 'home.dart';


class Createaccount extends StatefulWidget{
  @override
  _Createaccount createState() => new _Createaccount();

}
class _Createaccount extends State<Createaccount> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    new MaterialApp(
      debugShowCheckedModeBanner: false,


    );
    return new Scaffold(
        appBar: new AppBar(
          title: new Text('Inscription'),
            backgroundColor: Color.fromRGBO(100, 99, 70, 1)
        ),
        body: Container(
            padding : EdgeInsets.symmetric(vertical: 10, horizontal: 30),
            child: Container(
              child: ListView(
                children: <Widget>[
                  Text("                    "),
                  Text("                    "),
                  Text("                    "),
                  Text("Entrez votre nom"),
                  TextFormField(
                    decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: ''
                    ),
                  ),
                  Text("                    "),
                  Text("Entrez votre prénom"),
                  TextFormField(
                    decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: ''
                    ),
                  ),
                  Text("                    "),
                  Text("Entrez votre adresse mail"),
                  TextFormField(
                    decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: ''
                    ),
                  ),
                  Text("                    "),
                  Text("Entrez votre mot de passe"),
                  TextFormField(
                    obscureText: true,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: '',
                      suffix:  IconButton(icon: Icon(Icons.visibility), onPressed: () {}) ,
                    ),
                  ),
                  Text("                    "),
                  RaisedButton(
                    onPressed: () {
                           Navigator.push(
                          context, MaterialPageRoute(builder: (_) => HomePage()));

                    },
                    child: Text('Je m\'inscris'),
                  )
                ],
              ),
            )
        )
    );
  }
}