package controller;

import entity.Category;
import service.CategoryServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CategoryController implements BaseController {

    private CategoryServiceImpl categoryService;

    private UserInputMethod userInputMethod;

    public CategoryController() {
        this.categoryService = new CategoryServiceImpl();
        this.userInputMethod = new UserInputMethod();
    }

    @Override
    public void create(Scanner scanner) throws SQLException {            displayTitle("Create Category");
        displayTitle("Create Category");
        while (true) {
            String name = userInputMethod.inputString(scanner, "Enter the Category name: ");
            Category category = new Category(name);
            if (categoryService.save(category) != -1) {
                System.out.println("Created category record with name: " + category.getName());
            } else {
                System.out.println("Create category failed.");
            }
            String choice = userInputMethod.inputString(scanner, "Do you want to create another category? (y/N): ");

            if (!"y".equalsIgnoreCase(choice)) {
                break;
            }
        }
    }

    private void displayTitle(String title) {
        System.out.printf("--- %s ---\n", title.toUpperCase());
    }

    @Override
    public void show(Scanner scanner) throws SQLException {
        displayTitle("List all categories");
        List<Category> categories = categoryService.findAll();
        for (Category category : categories) {
            System.out.println(category.toString());
            scanner.nextLine();
        }
    }

    @Override
    public void update(Scanner scanner) throws SQLException {
        displayTitle("EDIT CATEGORY");
        while (true) {
            Integer id = userInputMethod.inputInteger(scanner, "Enter the Category ID: ");
            Optional<Category> category = categoryService.findById(id);
            if (category.isPresent()) {
                System.out.println("Found the category: " + category.get().getName());
                scanner.nextLine();
                String name = userInputMethod.inputString(scanner, "Enter the Category name: ");
                category.get().setName(name);
                if (categoryService.save(category.get()) != -1) {
                    System.out.println("Updated category record with name: " + category.get().getName());
                } else {
                    System.out.println("Update category failed");
                }
            } else {
                System.out.println("Not found");
            }
            String choice = userInputMethod.inputString(scanner, "Do you want to edit another category? (y/N): ");

            if (!"y".equalsIgnoreCase(choice)) {
                break;
            }
        }
    }

    @Override
    public void delete(Scanner scanner) {

    }
}
