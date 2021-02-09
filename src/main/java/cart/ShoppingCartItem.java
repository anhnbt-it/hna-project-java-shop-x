package cart;

import entity.Product;

public class ShoppingCartItem {
    private Product product;
    private short quantity;

    public ShoppingCartItem() {}

    public ShoppingCartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        this.quantity--;
    }

    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * this.product.getPrice().doubleValue());
        return amount;
    }
}
