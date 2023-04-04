package org.manadev.utils;

public class StringFactory {

    public static void printValue(String value) {
        int width = value.length() + 2;
        System.out.printf("""
                                  %s
                                  %s
                                  %s%n""", "-".repeat(width), value, "-".repeat(width));
    }
}
