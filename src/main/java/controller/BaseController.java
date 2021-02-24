package controller;

import java.sql.SQLException;
import java.util.Scanner;

public interface BaseController {

    void create(Scanner scanner) throws SQLException;

    void show(Scanner scanner) throws SQLException;

    void update(Scanner scanner) throws SQLException;

    void delete(Scanner scanner);

}
