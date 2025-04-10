package com.ExpenseTracker;

import java.io.*;
import java.util.*;

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.csv";
    private static List<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadExpenses();

        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Total Spent");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> deleteExpense();
                case 4 -> showTotal();
                case 5 -> {
                    saveExpenses();
                    System.out.println("Expenses saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addExpense() {
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Note: ");
        String note = scanner.nextLine();

        expenses.add(new Expense(amount, category, date, note));
        System.out.println("Expense added.");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        System.out.println("\n# | Amount | Category | Date | Note");
        int index = 1;
        for (Expense e : expenses) {
            System.out.println(index++ + ". " + e);
        }
    }

    private static void deleteExpense() {
        viewExpenses();
        System.out.print("Enter expense number to delete: ");
        int num = scanner.nextInt();
        if (num > 0 && num <= expenses.size()) {
            expenses.remove(num - 1);
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Invalid number.");
        }
    }

    private static void showTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total Spent: $" + total);
    }

    private static void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.println(e.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    private static void loadExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expenses.add(Expense.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("No existing data. Starting fresh.");
        }
    }
}
