import 'package:flutter/material.dart';
import 'package:app_e_commerce/constante.dart' as Constante;
import 'package:app_e_commerce/pages/detail/cart_button.dart';
import 'package:app_e_commerce/pages/detail/app_bar.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:app_e_commerce/model/CartModel.dart';
import 'package:app_e_commerce/model/Single_products.dart';

import '../home.dart';
import 'commentsArticle.dart';


class ProductDetail extends StatefulWidget {
  String products_details_id;
  String products_details_name;
  int products_details_new_price;
  List products_details_picture;
  String products_details_description;
  int products_details_quantity;
  int products_details_card;


  ProductDetail(
      { this.products_details_id,
        this.products_details_name,
      this.products_details_new_price,
      this.products_details_picture,
      this.products_details_description,
      this.products_details_quantity,
      this.products_details_card=1});
  @override
  _ProductDetailState createState() => _ProductDetailState();
}

class _ProductDetailState extends State<ProductDetail> {

  //widget.products_details_name,widget.products_details_new_price,widget.products_details_picture,widget.products_details_description,widget.products_details_quantity
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return ScopedModelDescendant<CartModel>(
        builder: (context, child, model) {
          return Scaffold(
            appBar: buildAppBar(
                context,
                title: 'ITEM DETAILS',
                leading: IconButton(
                    icon: Icon(Icons.arrow_back, color: Constante.kIconColor),
                    onPressed: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => HomePage(),
                          ));
                    }
                )
            ),
            body: Stack(
              children: [
                SafeArea(
                  child: SingleChildScrollView(
                    padding: EdgeInsets.symmetric(
                        horizontal: Constante.kDefaultPadding),
                    child: Container(
                      width: double.infinity,
                      margin: EdgeInsets.only(bottom: 100),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          // Product Name
                          Align(
                            alignment: Alignment.center,
                            child: Text(
                              widget.products_details_name,
                              style: TextStyle(
                                  fontSize: 24,
                                  fontWeight: FontWeight.bold
                              ),
                            ),
                          ),

                          SizedBox(height: Constante.kDefaultPadding),

                          // Product Image
                          ClipRRect(
                              borderRadius: BorderRadius.circular(
                                  Constante.kDefaultPadding),
                              child: Image.network(
                                  widget.products_details_picture[0],
                                  width: size.width -
                                      (Constante.kDefaultPadding * 2))
                          ),

                          SizedBox(height: Constante.kDefaultPadding),

                          // Price
                          Text(
                            "\$${widget.products_details_new_price}",
                            style: TextStyle(
                                fontSize: 24,
                                fontWeight: FontWeight.w700,
                                color: Constante.kTextColor
                            ),
                          ),

                          SizedBox(height: Constante.kDefaultPadding),

                          // Dropdown List
                          Row(
                            children: [
                              /*
                        Expanded(
                          flex: 3,
                          child: CustomDropDown(
                            items: widget.products_details_color[0],
                            hint: 'Color',
                          ),
                        ),

                       */

                              SizedBox(width: Constante.kDefaultPadding),

                            ],
                          ),

                          SizedBox(height: Constante.kDefaultPadding),

                          // Cart Button
                          CartButton(
                              tapEvent: () {
                                ProductDetail pro = new ProductDetail();
                                pro.products_details_id=widget.products_details_id;
                                pro.products_details_name=widget.products_details_name;
                                pro.products_details_quantity=widget.products_details_quantity;
                                pro.products_details_new_price=widget.products_details_new_price;
                                pro.products_details_description=widget.products_details_description;
                                pro.products_details_picture=widget.products_details_picture;
                                pro.products_details_card=widget.products_details_card;
                                print(widget.products_details_quantity);
                                model.addProduct(pro);


                              }


                          ),

                          SizedBox(height: Constante.kDefaultPadding),

                          // Description
                          Text(
                            widget.products_details_description,
                            style: TextStyle(
                                color: Constante.kTextLightColor,
                                fontSize: 15,
                                fontWeight: FontWeight.w700
                            ),
                          ),
                          Divider(

                          ),
                          Container(
                            height: 200.0,
                            child: CommentsArticles(),
                          )
                        ],
                      ),
                    ),
                  ),
                ),
              ],
            ),
          );
        }
    );
  }
}

