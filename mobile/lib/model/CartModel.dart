import 'package:app_e_commerce/pages/detail/details.dart';
import 'package:scoped_model/scoped_model.dart';

import 'Single_products.dart';

class CartModel extends Model {
  List<ProductDetail> cart = [];

  double totalCartValue = 0;

  int get total => cart.length;

  void addProduct(product) {

    int index = cart.indexWhere((i) => i.products_details_id == product.products_details_id);
    print(index);
    if (index != -1) {
      updateProduct(product, product.products_details_card + 1);
    }else {
      cart.add(product);
      calculateTotal();
      notifyListeners();
    }
  }

  void removeProduct(product) {
    int index = cart.indexWhere((i) => i.products_details_id == product.id);
    cart[index].products_details_card = 1;
    cart.removeWhere((item) => item.products_details_id == product.id);
    calculateTotal();
    notifyListeners();
  }

  void updateProduct(product, qty) {
    int index = cart.indexWhere((i) => i.products_details_id == product.products_details_id);
    cart[index].products_details_card = qty;
    if (cart[index].products_details_card == 0)
      removeProduct(product);

    calculateTotal();
    notifyListeners();
  }

  void clearCart() {
    cart.forEach((f) => f.products_details_card = 1);
    cart = [];
    notifyListeners();
  }

  void calculateTotal() {
    totalCartValue = 0;
    cart.forEach((f) {
      totalCartValue += f.products_details_new_price * f.products_details_card;
    });
  }
}