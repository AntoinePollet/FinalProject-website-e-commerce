import 'package:flutter/material.dart';

class ProductDetail extends StatefulWidget {
  String products_details_name;
  int products_details_new_price;
  List products_details_picture;
  String products_details_description;
  ProductDetail(
      {this.products_details_name,
      this.products_details_new_price,
      this.products_details_picture,
      this.products_details_description});
  @override
  _ProductDetailState createState() => _ProductDetailState();
}

class _ProductDetailState extends State<ProductDetail> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: new AppBar(
          title: Text("Kyōkyū"),
          flexibleSpace: Image(
              fit: BoxFit.cover,
              color: Color.fromRGBO(255, 255, 255, 0.5),
              colorBlendMode: BlendMode.modulate,
              image: AssetImage('images/sakura.jpg')),
          backgroundColor: Color(0x44000000),
          actions: <Widget>[
            new IconButton(
                icon: Icon(Icons.search, color: Colors.white),
                onPressed: () {}),
            new IconButton(
                icon: Icon(Icons.shopping_cart, color: Colors.white),
                onPressed: () {})
          ],
        ),
        body: new ListView(
          children: <Widget>[
            new Container(
              height: 300.0,
              width: 150.0,
              child: GridTile(
                  child: Container(
                    color: Colors.white,
                    child: Image.network(widget.products_details_picture[0]),
                  ),
                  footer: new Container(
                    color: Colors.white70,
                    child: ListTile(
                      leading: new Text(
                        widget.products_details_name,
                        style: TextStyle(
                            fontWeight: FontWeight.bold, fontSize: 16.0),
                      ),
                      title: new Row(
                        children: <Widget>[
                          /*
                          Expanded(
                              child: new Text(
                            "\$${widget.products_details_old_price}",
                            style: TextStyle(
                                color: Colors.grey,
                                decoration: TextDecoration.lineThrough),
                          )),
                          */
                          Expanded(
                              child: new Text(
                            "\$${widget.products_details_new_price}",
                            style: TextStyle(
                                fontWeight: FontWeight.bold, color: Colors.red),
                          )),
                        ],
                      ),
                    ),
                  )),
            ),
            //size quantite
            Row(
              children: <Widget>[
                Expanded(
                    child: MaterialButton(
                      onPressed: () {
                        showDialog(context: context,
                            builder: (context){
                              return new AlertDialog(
                                title: new Text("Couleur"),
                                content: new Text("Choix de la couleur"),
                                actions: <Widget>[
                                  new MaterialButton(onPressed: (){
                                    Navigator.of(context).pop(context);
                                  },
                                    child: new Text("Fermer"),
                                  )
                                ],
                              );
                            }
                        );
                      },
                  color: Colors.white,
                  textColor: Colors.grey,
                  elevation: 0.2,
                  child: Row(
                    children: <Widget>[
                      Expanded(child: new Text("Quatité")),
                      Expanded(child: new Icon(Icons.arrow_drop_down)),
                    ],
                  ),
                ),
                ),
                //size color
                Expanded(
                  child: MaterialButton(
                    onPressed: () {
                      showDialog(context: context,
                          builder: (context){
                            return new AlertDialog(
                              title: new Text("Couleur"),
                              content: new Text("Choix de la couleur"),
                              actions: <Widget>[
                                new MaterialButton(onPressed: (){
                                  Navigator.of(context).pop(context);
                                },
                                  child: new Text("Fermer"),
                                )
                              ],
                            );
                          }
                      );
                    },
                    color: Colors.white,
                    textColor: Colors.grey,
                    elevation: 0.2,
                    child: Row(
                      children: <Widget>[
                        Expanded(child: new Text("Color")),
                        Expanded(child: new Icon(Icons.arrow_drop_down)),
                      ],
                    ),
                  ),
                ),
              ],
            ),
            //seconde button
            Row(
              children: <Widget>[
                Expanded(
                  child: MaterialButton(
                    onPressed: () {},
                    color: Colors.red,
                    textColor: Colors.white,
                    elevation: 0.2,
                    child: new Text('Acheter maintenant')
                    ),
                  ),
                new IconButton(icon: Icon(Icons.favorite_border),color: Colors.red,onPressed: (){}),
                new IconButton(icon: Icon(Icons.add_shopping_cart),color: Colors.red,onPressed: (){}),




          ],
            ),
            Divider(),
            new ListTile(
              title: new Text("Détail"),
              subtitle: new Text(widget.products_details_description),
            )

      ],
        ),
    );
  }
}
