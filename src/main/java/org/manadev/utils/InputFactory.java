package org.manadev.utils;

import java.util.Scanner;

import static org.manadev.utils.StringFactory.exitingPrompt;

public class InputFactory {
    static Scanner sc = new Scanner(System.in);

    public static <T> T getInputValue(Class<T> type) {
        while (true) {
            System.out.print("Choose an option: ");

            try {
                String input = sc.next();
                return castType(type, input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a value of type " + type.getSimpleName());
            } catch (ClassCastException e) {
                System.out.println("Input value cannot be cast to " + type.getSimpleName() + ". Please enter a value of type " + type.getSimpleName());
            }
        }
    }

    public static boolean quitPrompt() {
        System.out.println("Do you want to quit (Y / N)?");
        char answer = getInputValue(Character.class);
        if (answer == 'y') exitingPrompt();
        return answer != 'y';
    }

    // utilities methods for utils

    private static <T> T castType(Class<T> type, String input) {
        if (type == Integer.class) return type.cast(Integer.parseInt(input));
        else if (type == Double.class) return type.cast(Double.parseDouble(input));
        else if (type == String.class) return type.cast(input);
        else if (type == Character.class) return type.cast(input.toLowerCase().charAt(0));
        else throw new IllegalArgumentException("Type not supported: " + type.getSimpleName());
    }
}
