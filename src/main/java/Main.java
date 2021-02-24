import controller.CategoryController;
import entity.Product;
import service.ProductServiceImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        ProductServiceImpl productService = new ProductServiceImpl();
        CategoryController categoryController = new CategoryController();
        do {
            System.out.println("========================================================="
                    + "\n\t\tWelcome to the Shop X!"
                    + "\n=========================================================");
            System.out.println("Please enter a choice and press <enter>: ");
            System.out.println("\t1. List all product");
            System.out.println("\t2. Create a new product");
            System.out.println("\t4. View all customers");
            System.out.println("\t5. View all orders");
            System.out.println("\t6. Sign out");
            System.out.println("\t7. List all category");
            System.out.println("\t8. Create a new category");
            System.out.println("\t9. Edit a category");
            System.out.println("\t10. Quit the application");
            System.out.print("Enter you choice: ");

            final int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    findProduct(productService);
                    break;
                case 2:
                    createProductDetails(scanner, productService);
                    break;
                case 3:
                    System.out.println("Customers");
                    break;
                case 4:
                    System.out.println("Customer details");
                    break;
                case 7:
                    categoryController.show(scanner);
                    break;
                case 8:
                    categoryController.create(scanner);
                    break;
                case 9:
                    categoryController.update(scanner);
                    break;
                case 10:
                    System.out.println("Exiting application...bye.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice\n\n");
            }
        } while (true);
    }

    private static void createProductDetails(Scanner scanner, ProductServiceImpl productService) throws SQLException {
        while (true) {
            System.out.print("\nEnter the Product's name: ");
            String name = scanner.nextLine();

            System.out.print("\nEnter the Product's price: ");
            BigDecimal price = scanner.nextBigDecimal();

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCreatedAt(LocalDate.now());

            productService.createProduct(product);
            System.out.println("Created product record with name: " + product.getName());

            scanner.nextLine();
            System.out.print("Do you want to create another product? (y/n): ");
            String choice = scanner.nextLine();

            if (!"y".equalsIgnoreCase(choice)) {
                break;
            }
        }
    }

    private static void findProduct(ProductServiceImpl productService) {
        System.out.println("ID\tNAME\tPRICE");
        System.out.println("==================================");
        List<Product> products = productService.getProducts();
        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(String.format("%d\t%s\t%s", product.getId(), product.getName(), product.getPrice()));
//                System.out.println(DATE_FORMAT.format(person.getCreatedDateTime()));//NOSONAR
            }
        } else {
            System.out.print(String.format("No Product record found."));
        }
        System.out.println("==================================\n");
    }

}
