import 'dart:convert';

import 'package:app_e_commerce/model/CartModel.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:app_e_commerce/constante.dart' as Constante;
import 'file:///C:/Users/elias/Desktop/Projects/Projet-e-commerce/FinalProject-website-e-commerce/mobile/lib/model/Single_products.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:app_e_commerce/pages/detail/details.dart';

class Products extends StatefulWidget {
  @override
  _ProductsState createState() => _ProductsState();
}

class _ProductsState extends State<Products> {
  String url = "${Constante.BASE_URL}/api/v1/articles/all";

  Future<List<Single_prodcucts>> _getproducts() async {
    var response = await http.get(Uri.parse(url));
    var jsonData = json.decode(response.body);
    List<Single_prodcucts> producsts = [];

    for (var u in jsonData) {
      Single_prodcucts proo = Single_prodcucts(u["id"], u["name"],
          u["pictures"], u["price"], u["description"], u["quantity"]);
      producsts.add(proo);
    }
    return producsts;
  }

  @override
  Widget build(BuildContext context) {
   return new Scaffold(
      body: Container(
        child: FutureBuilder<List<Single_prodcucts>>(
            future: _getproducts(),
            // ignore: missing_return
            builder: (context, snapshot) {
              if (snapshot.data == null) {
                return Container(
                  child: Center(
                    child: Text("Chargement..."),
                  ),
                );
              }
              if (snapshot.hasData) {
                return GridView.builder(
                    itemCount: snapshot.data.length,
                    gridDelegate: new SliverGridDelegateWithFixedCrossAxisCount(
                        crossAxisCount: 2),
                    itemBuilder: (BuildContext context, int index) {
                    return ScopedModelDescendant<CartModel>(
                        builder: (context, child, model) {
                      return Card(
                        child: Hero(
                          tag: snapshot.data[index].name,
                          child: Material(
                            child: InkWell(
                              onTap: () => Navigator.of(context).push(
                                new MaterialPageRoute(
                                  builder: (context) => new ProductDetail(
                                    products_details_id:
                                      snapshot.data[index].id,
                                    products_details_name:
                                        snapshot.data[index].name,
                                    products_details_new_price:
                                        snapshot.data[index].price,
                                    products_details_picture:
                                        snapshot.data[index].picture,
                                      products_details_description :
                                        snapshot.data[index].description,
                                      products_details_quantity:
                                    snapshot.data[index].qty
                                    ,
                                  ),
                                ),
                              ),
                              child: GridTile(
                                footer: Container(
                                  color: Colors.white70,
                                  child: ListTile(
                                    leading: Text(
                                      snapshot.data[index].name,
                                      style: TextStyle(
                                          fontWeight: FontWeight.bold),
                                    ),
                                    title: Text(
                                        "\$"+snapshot.data[index].price.toString(),
                                      style: TextStyle(
                                          color: Colors.red, fontWeight: FontWeight.w800),
                                    ),


                                  ),
                                ),
                                child: Image.network(
                                  snapshot.data[index].picture[0],
                                  fit: BoxFit.cover,
                                ),
                              ),
                            ),
                          ),
                        ),
                      );
                        });
                    });
              }
            }),
      ),
    );
  }
}






/*
    return new Scaffold(
      body: Container(
        child: FutureBuilder<List<Single_prodcucts>>(
            future: _getproducts(),
            // ignore: missing_return
            builder: (context, snapshot) {
              if (snapshot.data == null) {
                return Container(
                  child: Center(
                    child: Text("Chargement..."),
                  ),
                );
              }

              if (snapshot.hasData) {
                return GridView.builder(
                  itemCount: snapshot.data.length,
                  gridDelegate: new SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 2),
                  itemBuilder: (BuildContext context, int index) {
                    return ScopedModelDescendant<CartModel>(
                        builder: (context, child, model) {
                      return Card(
                          child: Column(children: <Widget>[
                        Image.network(
                          snapshot.data[index].picture[0],
                          height: 100,
                          width: 100,
                        ),
                        Text(
                          snapshot.data[index].name,
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        Text("\$" + snapshot.data[index].price.toString()),
                          IconButton(icon : Icon(Icons.shopping_cart, color: Colors.black),
                            onPressed: () =>
                                model.addProduct(snapshot.data[index]))
                      ]));
                    });
                  },
                );
              }
            }),
      ),
    );
  }
}


 */