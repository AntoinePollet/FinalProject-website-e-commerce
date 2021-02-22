import 'package:flutter/material.dart';

class HorizontalList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 100.0,
      child: ListView(
        scrollDirection: Axis.horizontal,
        children: <Widget>[
          Categories(
            image_location: 'images/cats/armchair.png',
            image_caption: 'Fauteuil',
          ),

          Categories(
            image_location: 'images/cats/bed.png',
            image_caption: 'Lit',
          ),

          Categories(
            image_location: 'images/cats/chair.png',
            image_caption: 'Chaise',
          ),

          Categories(
            image_location: 'images/cats/closet.png',
            image_caption: 'Armoire',
          ),

          Categories(
            image_location: 'images/cats/couch.png',
            image_caption: 'Canapé',
          ),

          Categories(
            image_location: 'images/cats/shelves.png',
            image_caption: 'Etagére',
          ),

          Categories(
            image_location: 'images/cats/round-table.png',
            image_caption: 'Table  ',
          ),
        ],
      ),
    );
  }
}

class Categories extends StatelessWidget {
  final String image_location;
  final String image_caption;
  Categories({
    this.image_location,
    this.image_caption});
  @override
  Widget build(BuildContext context) {
    return Padding(padding: const EdgeInsets.all(2.0),
    child: InkWell(
      onTap:(){},
      child: Container(
        width: 100.0,
        child: ListTile(
          title: Image.asset(image_location,
          width: 100.0,
          height: 80.0,),
          subtitle: Container(
            alignment: Alignment.topCenter,
            child: Text(image_caption, style: new TextStyle(fontSize: 12.0 ),),
          ),
        ),
      ),
    ),
    );
  }
}
