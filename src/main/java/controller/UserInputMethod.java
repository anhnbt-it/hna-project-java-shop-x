package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputMethod {

    public String inputString(Scanner scanner, String title) {
        System.out.printf("\t%s", title);
        String str = scanner.nextLine();
        return str;
    }

    public Integer inputInteger(Scanner scanner, String title) {
        System.out.printf("\t%s", title);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Integers only, please");
                scanner.nextLine();
            }
        }
    }
}
