import 'dart:convert';

import 'package:app_e_commerce/pages/details.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:app_e_commerce/constante.dart' as Constante;
import 'package:app_e_commerce/componets/classproducts.dart';

class Products extends StatefulWidget {
  @override
  _ProductsState createState() => _ProductsState();
}

class _ProductsState extends State<Products> {
  String url = "${Constante.BASE_URL}/api/articles/all";

  Future<List<Single_prodcucts>> _getproducts() async {
    var response = await http.get(Uri.parse(url));
    var jsonData = json.decode(response.body);
    List<Single_prodcucts> producsts = [];

    for (var u in jsonData) {
      Single_prodcucts proo =
          Single_prodcucts(u["name"], u["pictures"], u["price"],u["description"]);
      producsts.add(proo);
    }
    return producsts;
  }
/*
  var produits = [
    {
      "name": "Table",
      "picture": "images/1.jpg",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Chaise",
      "picture": "images/2.jpg",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Fauteuil",
      "picture": "images/3.jpg",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Etagere",
      "picture": "images/4.jpg",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Cuisine",
      "picture": "images/5.jpg",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Sale de bain",
      "picture": "images/sasuke.png",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Lit",
      "picture": "images/lit.jpg",
      "old_price": 120,
      "price": 85,
    },
    {
      "name": "Sakura",
      "picture": "images/sakura.jpg",
      "old_price": 120,
      "price": 85,
    },
  ];
*/
  /*
  @override
  Widget build(BuildContext context)  {
    return GridView.builder(
        itemCount:
        gridDelegate:
        new SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
        // ignore: missing_return
        itemBuilder: (BuildContext context, int index) {
          return Single_prodcucts(
            name: data[1]['name'],
            picture: data[2]['pictures'],
            price: data[5]['price'],
          );
        });
  }
}
*/

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
                      return Card(
                        child: Hero(
                          tag: snapshot.data[index].name,
                          child: Material(
                            child: InkWell(
                              onTap: () => Navigator.of(context).push(
                                new MaterialPageRoute(
                                  builder: (context) => new ProductDetail(
                                    products_details_name:
                                        snapshot.data[index].name,
                                    products_details_new_price:
                                        snapshot.data[index].price,
                                    products_details_picture:
                                        snapshot.data[index].picture,
                                      products_details_description :
                                        snapshot.data[index].description
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
              }
            }),
      ),
    );
  }
}
