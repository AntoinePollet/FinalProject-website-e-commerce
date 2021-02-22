import 'package:flutter/material.dart';
import 'home.dart';
import 'createaccount.dart';


class HomeConnexion extends StatefulWidget{
  @override
  _Homeconnexion createState() => new _Homeconnexion();

}
class _Homeconnexion extends State<HomeConnexion>{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    new MaterialApp(
        debugShowCheckedModeBanner: false,


    );
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Kyōkyū"),
        backgroundColor: Color.fromRGBO(67, 0, 153, 1)


      ),
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(top: 60.0),
              child: Center(
                child: Container(
                    width: 200,
                    height: 150,
                ),
              ),
            ),
            Padding(
              //padding: const EdgeInsets.only(left:15.0,right: 15.0,top:0,bottom: 0),
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Email',
                    hintText: 'Entrez votre adresse mail abc@gmail.com'),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 0),
              //padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(

                obscureText: true,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Mot de passe',
                    hintText: 'Entrez votre mot de passe'),
              ),
            ),
            FlatButton(
              onPressed: (){
                //TODO FORGOT PASSWORD SCREEN GOES HERE
              },
              child: Text(
                'Mot de passe oublié',
                style: TextStyle(color: Colors.red, fontSize: 15),
              ),
            ),
            Container(
              height: 50,
              width: 250,
              decoration: BoxDecoration(
                  color: Colors.red, borderRadius: BorderRadius.circular(20)),
              child: FlatButton(
                onPressed: () {
                  _showSnackBar();
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => HomePage()));
                },
                child: Text(
                  'Se connecter',
                  style: TextStyle(color: Colors.white, fontSize: 25),
                ),
              ),
            ),
            SizedBox(
              height: 130,
            ),
            FlatButton(
              onPressed: () {
              Navigator.push(
                  context, MaterialPageRoute(builder: (_) => Createaccount()));

              },
              child: Text(
                'New account ? Create a new account',
                style: TextStyle(color: Colors.black, fontSize: 15),
              ),
            ),

          ],
        ),
      ),
        key: _scaffoldkey,

    );
  }

}
final GlobalKey<ScaffoldState> _scaffoldkey = new GlobalKey<ScaffoldState>();
_showSnackBar(){
  print("Show SnackBar");
  final snackBar = new SnackBar(content: new Text("Hello elias"),
    duration: new Duration(seconds: 3),
  );
  _scaffoldkey.currentState.showSnackBar(snackBar);
}


