import 'package:app_e_commerce/constante.dart' as Constante;
import 'package:flutter/material.dart';

class DropDownContainer extends StatelessWidget {
  const DropDownContainer({
    Key key,
    @required this.child
  }) : super(key: key);

  final Widget child;

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(Constante.kDefaultPadding * 2.5),
          border: Border.all(color: Constante.kIconColor)
      ),
      padding: EdgeInsets.symmetric(horizontal: Constante.kDefaultPadding),
      child: child,
    );
  }
}