package com.InventoryManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.loadItemsFromFile();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Item\n2. View Items\n3. Update Item\n4. Delete Item"
            		+ "\n5. Search Item\n6. Save & Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    manager.addItem(new InventoryItem(id, name, qty, price));
                    break;

                case 2:
                    manager.viewItems();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    System.out.print("New Quantity: ");
                    int uq = sc.nextInt();
                    System.out.print("New Price: ");
                    double up = sc.nextDouble();
                    manager.updateItem(uid, uq, up);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    manager.deleteItem(did);
                    break;

                case 5:
                    System.out.print("Enter name to search: ");
                    String key = sc.nextLine();
                    manager.searchItem(key);
                    break;

                case 6:
                    manager.saveItemsToFile();
                    System.out.println("Inventory saved. Exiting.");
                    return;

                default:
                    System.out.println("Invalid choice.");
                    sc.close();
            }
        }
    }
}
