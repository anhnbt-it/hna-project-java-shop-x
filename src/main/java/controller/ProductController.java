package controller;

import entity.Product;
import service.ProductServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductController {

    private ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    public void saveProduct(Scanner scanner) throws SQLException {
        System.out.println("Enter your name: ");
        Product product = new Product();
        product.setName(scanner.nextLine());
        System.out.println("Enter the price: ");
        product.setPrice(scanner.nextBigDecimal());
        productServiceImpl.createProduct(product);
    }

    public void showProductList() {
        List<Product> products = productServiceImpl.getProducts();
        for (Product prod : products) {
            System.out.println(prod.toString());
        }
    }

    public boolean deleteProduct(Integer id) {
        Optional<Product> product = productServiceImpl.findById(id);
        if (product.isPresent()) {
            return productServiceImpl.deleteById(id);
        }
        System.out.println("Not found.");
        return false;
    }
}
