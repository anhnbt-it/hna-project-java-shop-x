import model.DBUtil;
import entity.Product;
import service.ProductServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int USER_OPTION_SELECTALL = 1;
    private static final int USER_OPTION_SELECTONE = 2;
    private static final int USER_OPTION_INSERT = 3;
    private static final int USER_OPTION_UPDATE = 4;
    private static final int USER_OPTION_DELETE = 5;
    private static final int USER_OPTION_EXIT = 0;

    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        Connection conn = DBUtil.getConnection();
        ProductServiceImpl productService = new ProductServiceImpl();
        try {
            do {
                int userOption = getUserOption(scanner);
                switch(userOption) {
                    case USER_OPTION_SELECTONE :
//                        selectOne(conn);
                        break;
                    case USER_OPTION_SELECTALL :
                        selectAll(productService);
                        break;
                    case USER_OPTION_INSERT :
//                        insert(conn);
                        break;
                    case USER_OPTION_UPDATE :
//                        update(conn);
                        break;
                    case USER_OPTION_DELETE :
//                        delete(conn);
                        break;
                    case USER_OPTION_EXIT :
                        show("Exiting application...bye !!");
                        return;
                    default :
                        show("Invalid option: " + userOption);
                }
            } while (true);
        }
        finally {
            conn.close();
        }
    }

    private static void createProductDetails(Scanner scanner, ProductServiceImpl productService) throws SQLException {
        while (true) {
            System.out.print("\nEnter the Product's name: ");
            String name = scanner.nextLine();

            System.out.print("\nEnter the Product's price: ");
            double price = scanner.nextDouble();

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCreatedAt(LocalDateTime.now());

            productService.create(product);
            System.out.println("Created product record with name: " + product.getName());

            scanner.nextLine();
            System.out.print("Do you want to create another product? (y/n): ");
            String choice = scanner.nextLine();

            if (!"y".equalsIgnoreCase(choice)) {
                break;
            }
        }
    }

    private static void selectAll(ProductServiceImpl productService) {
        System.out.println("ID\tNAME\tPRICE\tCREATED");
        System.out.println("/----------------------------------------------------------------/");
        List<Product> products = productService.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.print(String.format("%d\t%s\t%s\t", product.getId(), product.getName(), product.getPrice()));
                System.out.println(product.getCreatedAt().format(formatter));
            }
        } else {
            System.out.print(String.format("No Product record found."));
        }
        System.out.println("/----------------------------------------------------------------/");
    }

    private static int getUserOption(Scanner scanner) {
        int userOption = -1;
        try {
            System.out.println("========================================================="
                    + "\n\t\tWelcome to the Shop X!"
                    + "\n\t\tby Nguyen Ba Tuan Anh"
                    + "\n=========================================================");
            System.out.println("Please enter a choice and press <enter>: ");
            System.out.println("\t1. List all product");
            System.out.println("\t2. Create a new product");
            System.out.println("\t3. View all customers");
            System.out.println("\t4. View all orders");
            System.out.println("\t5. Sign out");
            System.out.println("\t6. Quit the application");
            System.out.print("Enter you choice: ");
            userOption = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e) {
            /* Ignore exception */
        }
        return userOption;
    }

    private static Product getProductFromConsole(Scanner scanner) {
        Product prodObj = null;
        try {
            System.out.println("Enter Product Details");
            System.out.println("Name: ");
            String name = scanner.nextLine();
            System.out.println("Description: ");
            String description = scanner.nextLine();
            System.out.println("Price: ");
            double price = Double.parseDouble(scanner.nextLine());
            prodObj = new Product(name, price, description);
        }
        catch (Exception e) {
            /* Ignore exception */
        }
        return prodObj;
    }

    private static int getProductIDFromConsole(Scanner scanner) {
        int prodId = -1;
        try {
            System.out.println("Enter Product ID :");
            prodId = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e) {
            /* Ignore exception */
        }
        return prodId;
    }

    private static void show(String msg) {
        System.out.println(msg);
    }

    private static void showError(String msg) {
        System.out.println("Error : " + msg);
    }

}
