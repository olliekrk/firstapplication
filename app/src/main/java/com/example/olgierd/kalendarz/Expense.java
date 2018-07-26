package com.example.olgierd.kalendarz;

public class Expense {
    public enum ExpenseCategory {
        FOOD, SCHOOL, COMMUTING, BEER, OTHER;
    }

    private String name;
    private ExpenseCategory expenseCategory;
    private double amount;
    private int day;
    private int month;
    private int year;

    
}
