package cart;

import entity.Product;

import java.util.List;

public class ShoppingCart {
    double total;
    int numberOfItems;
    List<ShoppingCartItem> items;
    //checkout
    //0 items
    //confirmation

    public void addItem(Product product) {
        // addToCart
        boolean newItem = true;
        for (ShoppingCartItem scItem: items) {
            if (scItem.getProduct().getId() == product.getId()) {
                newItem = false;
                scItem.incrementQuantity();
            }
        }
        if (newItem) {
            ShoppingCartItem scItem = new ShoppingCartItem(product);
            items.add(scItem);
        }

    }

    public void viewCart() {
        // Your shopping cart contains ${cart.numberOfItems} items.
        //subtotal: $...
        // update
        // button: clear cart, continue shopping, process to checkout
    }

    public void update(Product product, short quantity) {
        // updateCart
        short qty = -1;
        qty = quantity;
        if (qty >= 0) {
            ShoppingCartItem item = null;

            for (ShoppingCartItem scItem: items) {
                if (scItem.getProduct().getId() == product.getId()) {
                    if (qty != 0) {
                        // set item quantity to new value
                        scItem.setQuantity(qty);
                    } else {
                        // if quantity equals 0, save item and break
                        item = scItem;
                        break;
                    }
                }
            }

            if (item != null) {
                // remove from cart
                items.remove(item);
            }
        }

    }

    public void checkout() {
        // proceed to checkout
    }
    //purchase
    //chooseLanguage

    public void clear() {
        // clear cart
        items.clear();
        numberOfItems = 0;
        total = 0;
    }

    public void continueShopping() {
        // continue shopping
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public int getNumberOfItems() {

        numberOfItems = 0;

        for (ShoppingCartItem scItem: items) {
            this.numberOfItems += scItem.getQuantity();
        }

        return numberOfItems;
    }

    public double getSubtotal() {
        double amount = 0;

        for (ShoppingCartItem scItem: items) {
            Product product = scItem.getProduct();
            amount += (scItem.getQuantity() * product.getPrice().doubleValue());
        }

        return amount;
    }

    public void calculateTotal(String surcharge) {

        double amount = 0;

        // cast surcharge as double
        double s = Double.parseDouble(surcharge);

        amount = this.getSubtotal();
        amount += s;

        total = amount;
    }

    public double getTotal() {
        return total;
    }

    //update quantity 1-99 and not 0
}
