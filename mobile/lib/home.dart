import 'package:flutter/material.dart';
import "package:carousel_pro/carousel_pro.dart";
import 'package:app_e_commerce/componets/horizontal_list.dart';
import 'package:app_e_commerce/componets/products.dart';

class HomePage extends StatefulWidget  {
  HomePage({Key key}) : super(key: key);

  @override
  _HomePage createState() => _HomePage();

}

class _HomePage extends State<HomePage> {

  @override
  Widget build(BuildContext context) {
    Widget image_caroussel = new Container(
      height: 200.0,
      child: new Carousel(
        boxFit: BoxFit.cover,
        images: [
          AssetImage('images/lit.jpg'),
          AssetImage('images/2.jpg'),
          AssetImage('images/3.jpg'),
          AssetImage('images/1.jpg'),
          AssetImage('images/4.jpg'),
        ],
        autoplay: true,
        //animationCurve: Curves.fastOutSlowIn,
        //animationDuration: Duration(milliseconds: 1000),
        dotSize: 4.0,
        indicatorBgPadding: 3.0,
      ),
    );

    // TODO: implement build
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
              icon: Icon(Icons.search, color: Colors.white), onPressed: () {}),
          new IconButton(
              icon: Icon(Icons.shopping_cart, color: Colors.white),
              onPressed: () {})
        ],
      ),
      drawer: new Drawer(
          child: new ListView(
        children: <Widget>[
          //header
          new UserAccountsDrawerHeader(
              accountName: Text('Elias Lotfi'),
              accountEmail: Text("eliasltf1@gmail.com"),
              currentAccountPicture: GestureDetector(
                child: new CircleAvatar(
                  backgroundColor: Colors.grey[900],
                  child: Icon(Icons.person, color: Colors.white),
                ),
              ),
              decoration: new BoxDecoration(
                color: Color.fromRGBO(255, 13, 75, 1),
              )),
          // Body
          InkWell(
            onTap: () {
              Navigator.push(
                  context, MaterialPageRoute(builder: (_) => HomePage()));
            },
            child: ListTile(
              title: Text("Acceuil"),
              leading: Icon(Icons.home, color: Colors.red),
              selectedTileColor: Colors.black,
            ),
          ),
          InkWell(
            onTap: () {},
            child: ListTile(
              title: Text("Mon compte"),
              leading: Icon(Icons.person, color: Colors.red),
            ),
          ),
          InkWell(
            onTap: () {},
            child: ListTile(
              title: Text("Mon Panier"),
              leading: Icon(Icons.shopping_basket, color: Colors.red),
            ),
          ),
          InkWell(
            onTap: () {},
            child: ListTile(
              title: Text("Categories"),
              leading: Icon(Icons.dashboard, color: Colors.red),
            ),
          ),
          InkWell(
            onTap: () {},
            child: ListTile(
              title: Text("Favoris"),
              leading: Icon(Icons.favorite, color: Colors.red),
            ),
          ),

          Divider(),

          InkWell(
            onTap: () {},
            child: ListTile(
              title: Text("Paramétre"),
              leading: Icon(Icons.settings, color: Colors.grey),
            ),
          ),

          InkWell(
            onTap: () {},
            child: ListTile(
              title: Text("A propos de nous"),
              leading: Icon(Icons.help, color: Colors.green),
            ),
          ),
        ],
      )),
      body: new ListView(
        children: [
          image_caroussel,
          //Padding categorie
          new Padding(
            padding: const EdgeInsets.all(8.0),
            child: new Text('Categories'),
          ),
          //Horizontal list
          HorizontalList(),
          //Padding widget
          new Padding(
            padding: const EdgeInsets.all(20.0),
            child: new Text('Récente recherche'),
          ),
          Container(
            height: 320.0,
            child: Products(),
          )
        ],
      ),
    );
  }
}
