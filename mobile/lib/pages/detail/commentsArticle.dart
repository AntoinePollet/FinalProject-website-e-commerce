import 'dart:convert';

import 'file:///C:/Users/elias/Desktop/Projects/Projet-e-commerce/FinalProject-website-e-commerce/mobile/lib/model/Comment.dart';
import 'package:flutter/material.dart';
import 'package:app_e_commerce/constante.dart' as Constante;
import 'package:http/http.dart' as http;

class CommentsArticles extends StatefulWidget {

@override
_CommentsArticles createState() => _CommentsArticles();
}

class _CommentsArticles extends State<CommentsArticles> {
  String url = "${Constante.BASE_URL}/api/v1/comments/all";
  Comment com;

  Future<List<Comment>> _getcomment() async {
    var response = await http.get(Uri.parse(url));
    var jsonData = json.decode(response.body);
    List<Comment> producsts = [];

    for (var u in jsonData) {
      Comment proo =
      Comment(u["username"], u["message"], u["date"]);
      producsts.add(proo);
    }
    return producsts;
  }
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        body: Container(
        child: FutureBuilder<List<Comment>>(
        future: _getcomment(),
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
                      crossAxisCount: 1),
                    itemBuilder: (BuildContext context, int index) {
                      return Container(
                        child : Text(snapshot.data[index].message,style: TextStyle(fontSize: 20.0,fontStyle: FontStyle.italic),)
                      );

                    },

                );
              }
            }
    ),
    ),
    );
  }
}

