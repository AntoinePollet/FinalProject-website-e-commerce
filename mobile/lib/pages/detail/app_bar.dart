import 'package:flutter/material.dart';


import 'package:app_e_commerce/constante.dart' as Constante;

AppBar buildAppBar(BuildContext context, {String title, List<Widget> actions, Widget leading}) {
  return AppBar(
    backgroundColor: Colors.transparent,
    elevation: 0,
    title: Text(
      title,
      style: TextStyle(
          color: Constante.kTextLightColor,
          fontSize: 18,
          fontWeight: FontWeight.w700
      ),
    ),
    centerTitle: false,
    leading: leading,
  );
}