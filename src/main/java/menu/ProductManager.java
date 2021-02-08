package menu;

import dao.ProductDao;
import entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductManager {

    private ProductDao productDao;

    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void show() {
        List<Product> products = productDao.findAll();
        for (Product prod : products) {
            System.out.println(prod.toString());
        }
    }

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter your name: ");
        Product product = new Product();
        product.setName(scanner.nextLine());
        System.out.println("Enter the price: ");
        product.setPrice(scanner.nextDouble());
        if (productDao.save(product)) {
            System.out.println("Insert product successfully.");
        } else {
            System.out.println("Insert product failed");
        }
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Enter your name: ");
        Product product = new Product();
        product.setName(scanner.nextLine());
        System.out.println("Enter the price: ");
        product.setPrice(scanner.nextDouble());
        if (productDao.save(product)) {
            System.out.println("Insert product successfully.");
        } else {
            System.out.println("Insert product failed");
        }
    }

    public void delete(Scanner scanner) {
        System.out.println("Enter product id: ");
        Integer id = scanner.nextInt();
        Optional<Product> product = productDao.findById(id);
        if (product.isPresent()) {
            if (productDao.deleteById(product.get().getId())) {
                System.out.println("Delete product successfully.");
            } else {
                System.out.println("Delete product failed");
            }
        } else {
            System.out.println("No records found for the product id : " + id);
        }
    }
}
