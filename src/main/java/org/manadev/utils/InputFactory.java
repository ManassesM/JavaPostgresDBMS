package org.manadev.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.manadev.utils.StringFactory.printValue;

public class InputFactory {
    static Scanner sc = new Scanner(System.in);

    public static <T> T getInputValue(Class<T> type) throws InputMismatchException {
        while (true) {
            System.out.print("Input: ");

            try {
                String input = sc.nextLine();
                if (input.equals("quit prompt")) throw new InputMismatchException("Returning...");
                return castType(type, input);
            } catch (InputMismatchException e) {
                throw new InputMismatchException(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a value of type " + type.getSimpleName());
            } catch (ClassCastException e) {
                System.out.println("Input value cannot be cast to " + type.getSimpleName() + ". Please enter a value of type " + type.getSimpleName());
            }
        }
    }

    public static boolean quitPrompt(String msg) {
        System.out.println("Do you want to quit (Y / N)?");
        char answer = getInputValue(Character.class);
        if (answer == 'y') printValue(msg);
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
