package com.InventoryManagement;

import java.util.*;
import java.io.*;

public class InventoryManager {
    private List<InventoryItem> itemList = new ArrayList<>();
    private final String filePath = "inventory.txt";

    public void loadItemsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            itemList.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                InventoryItem item = new InventoryItem(
                    Integer.parseInt(data[0]),
                    data[1],
                    Integer.parseInt(data[2]),
                    Double.parseDouble(data[3])
                );
                itemList.add(item);
            }
        } catch (IOException e) {
            System.out.println("No inventory file found, starting fresh.");
        }
    }

    public void saveItemsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (InventoryItem item : itemList) {
                bw.write(item.getId() + "," + item.getName() + "," + item.getQuantity() + "," + item.getPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void addItem(InventoryItem item) {
        itemList.add(item);
        System.out.println("Item added!");
    }

    public void viewItems() {
        if (itemList.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (InventoryItem item : itemList) {
            System.out.println(item);
        }
    }

    public void updateItem(int id, int quantity, double price) {
        for (InventoryItem item : itemList) {
            if (item.getId() == id) {
                item.setQuantity(quantity);
                item.setPrice(price);
                System.out.println("Item updated.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void deleteItem(int id) {
        itemList.removeIf(item -> item.getId() == id);
        System.out.println("Item deleted if it existed.");
    }

    public void searchItem(String keyword) {
        for (InventoryItem item : itemList) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item);
            }
        }
    }
}