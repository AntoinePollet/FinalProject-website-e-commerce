import 'dart:convert';

import 'package:app_e_commerce/model/CartModel.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:app_e_commerce/constante.dart' as Constante;
import 'package:scoped_model/scoped_model.dart';
import 'package:app_e_commerce/pages/detail/details.dart';



class Cardshop extends StatefulWidget {
  @override
  _Cardshop createState() => _Cardshop();
}

class _Cardshop extends State<Cardshop> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          flexibleSpace: Image(
              fit: BoxFit.cover,
              color: Color.fromRGBO(255, 255, 255, 0.5),
              colorBlendMode: BlendMode.modulate,
              image: AssetImage('images/sakura.jpg')),
          backgroundColor: Color(0x44000000),
          title: Text("Cart"),
          actions: <Widget>[
            FlatButton(
                child: Text(
                  "Clear",
                  style: TextStyle(color: Colors.white),
                ),
                onPressed: () => ScopedModel.of<CartModel>(context).clearCart())
          ],
        ),
        body: ScopedModel.of<CartModel>(context, rebuildOnChange: true)
            .cart
            .length ==
            0
            ? Center(
          child: Text("No items in Cart"),
        )
            : Container(
            padding: EdgeInsets.all(8.0),
            child: Column(children: <Widget>[
              Expanded(
                child: ListView.builder(
                  itemCount: ScopedModel.of<CartModel>(context,
                      rebuildOnChange: true)
                      .total,
                  itemBuilder: (context, index) {
                    return ScopedModelDescendant<CartModel>(
                      builder: (context, child, model) {
                        return ListTile(
                          title: Text(model.cart[index].products_details_name),
                          subtitle: Text(model.cart[index].products_details_card.toString() +
                              " x " +
                              model.cart[index].products_details_new_price.toString() +
                              " = " +
                              (model.cart[index].products_details_card *
                                  model.cart[index].products_details_new_price)
                                  .toString()),
                          trailing: Row(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                IconButton(
                                  icon: Icon(Icons.add),
                                  onPressed: () {
                                    model.updateProduct(model.cart[index],
                                        model.cart[index].products_details_card + 1);
                                    // model.removeProduct(model.cart[index]);
                                  },
                                ),
                                IconButton(
                                  icon: Icon(Icons.remove),
                                  onPressed: () {
                                    model.updateProduct(model.cart[index],
                                        model.cart[index].products_details_card - 1);
                                    // model.removeProduct(model.cart[index]);
                                  },
                                ),
                              ]),
                        );
                      },
                    );
                  },
                ),
              ),
              Container(
                  padding: EdgeInsets.all(8.0),
                  child: Text(
                    "Total: \$ " +
                        ScopedModel.of<CartModel>(context,
                            rebuildOnChange: true)
                            .totalCartValue
                            .toString() +
                        "",
                    style: TextStyle(
                        fontSize: 24.0, fontWeight: FontWeight.bold),
                  )),
              SizedBox(
                  width: double.infinity,
                  child: RaisedButton(
                    color: Colors.yellow[900],
                    textColor: Colors.white,
                    elevation: 0,
                    child: Text("BUY NOW"),
                    onPressed: () {},
                  ))
            ])));
  }
}
