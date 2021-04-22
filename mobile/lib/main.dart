import 'package:app_e_commerce/pages/Card/card.dart';
import 'package:flutter/material.dart';
import 'package:app_e_commerce/authentification/loginsuser.dart';
import 'package:scoped_model/scoped_model.dart';

import 'model/CartModel.dart';

/*
void main(){
  runApp(
    new MaterialApp(
      debugShowCheckedModeBanner: false,
      home : Loginusers()
    )
  );
}
*/


void main() => runApp(MyApp(
  model: CartModel(),
));


class MyApp extends StatelessWidget{

  final CartModel model;

  const MyApp({Key key, @required this.model}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ScopedModel<CartModel>(
      model: model,
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Shopping Cart',
        home: Loginusers(),
        routes: {'/cart': (context) => Cardshop()},
      ),
    );
  }
}