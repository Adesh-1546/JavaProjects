package com.ExpenseTracker;

public class Expense {
    private double amount;
    private String category;
    private String date;
    private String note;

    public Expense(double amount, String category, String date, String note) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getNote() { return note; }

    @Override
    public String toString() {
        return amount + " | " + category + " | " + date + " | " + note;
    }

    public String toCSV() {
        return amount + "," + category + "," + date + "," + note;
    }

    public static Expense fromCSV(String line) {
        String[] data = line.split(",", 4);
        return new Expense(
            Double.parseDouble(data[0]),
            data[1],
            data[2],
            data[3]
        );
    }
}