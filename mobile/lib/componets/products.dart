import 'package:app_e_commerce/pages/details.dart';
import 'package:flutter/material.dart';


class Products extends StatefulWidget {
  @override
  _ProductsState createState() => _ProductsState();
}

class _ProductsState extends State<Products> {
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
  @override
  Widget build(BuildContext context) {
    return GridView.builder(
        itemCount: produits.length,
        gridDelegate:
            new SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
        // ignore: missing_return
        itemBuilder: (BuildContext context, int index) {
          return Single_prodcucts(
            prod_name: produits[index]['name'],
            prod_picture: produits[index]['picture'],
            prod_old_price: produits[index]['old_price'],
            prod_price: produits[index]['price'],
          );
        });
  }
}

class Single_prodcucts extends StatelessWidget {
  final prod_name;
  final prod_picture;
  final prod_old_price;
  final prod_price;

  Single_prodcucts(
      {this.prod_name,
      this.prod_picture,
      this.prod_old_price,
      this.prod_price});
  @override
  Widget build(BuildContext context) {
    return Card(
      child: Hero(
          tag: prod_name,
          child: Material(
            child: InkWell(
              onTap: () =>Navigator.of(context).push(new MaterialPageRoute(builder: (context) => new ProductDetail(
                products_details_name: prod_name,
                products_details_new_price: prod_price,
                products_details_old_price: prod_old_price,
                products_details_picture: prod_picture,
              ))),
              child: GridTile(
                  footer: Container(
                    color: Colors.white70,
                    child: ListTile(
                      leading: Text(
                        prod_name,
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      title: Text(
                        "\$$prod_price",
                        style: TextStyle(
                            color: Colors.red, fontWeight: FontWeight.w800),
                      ),
                      /*subtitle: Text(
                      "\$$prod_old_price",
                      style: TextStyle(
                      color: Colors.black54, fontWeight: FontWeight.w800,decoration: TextDecoration.lineThrough),
                    ),*/
                  ),
                  ),
                  child: Image.asset(
                    prod_picture,
                    fit: BoxFit.cover,
                  )),
            ),
          )),
    );
  }
}
