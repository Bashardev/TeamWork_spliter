package com.example.router;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessageRouter router = new MessageRouter(new KeywordClassifier());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type a customer message (empty line to quit):");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                break;
            }
            System.out.println("  -> " + router.routeTo(line));
        }
    }
}
