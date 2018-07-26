package com.example.olgierd.kalendarz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Expense {
    public enum ExpenseCategory {
        FOOD, SCHOOL, COMMUTING, BEER, OTHER;
    }

    static class expenseDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "expensesDatabase";
        private static final String TABLE_NAME = "expensesTable";
        private static final int DATABASE_Version = 1;

        private static final String name = "Name";
        private static final String expenseCategory = "Category";
        private static final String amount = "0";
        private static final String day = "0";
        private static final String month = "0";
        private static final String year = "0";

        private static final String CREATE_MSG = TABLE_NAME;//
        private static final String DROP_MSG = TABLE_NAME;//
        private Context context;

        public expenseDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_MSG);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_MSG);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }

    expenseDbHelper helper;

    public Expense(Context context) {
        helper = new expenseDbHelper(context);
    }

    public long insertData(String name, String category, double amount, int day, int month, int year) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();//trzeba pokminic z konwersja
        contentValues.put(expenseDbHelper.name, name);
        contentValues.put(expenseDbHelper.expenseCategory, category);
        contentValues.put(expenseDbHelper.amount, amount);
        contentValues.put(expenseDbHelper.day, day);
        contentValues.put(expenseDbHelper.month, month);
        contentValues.put(expenseDbHelper.year, year);
        long id = db.insert(expenseDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {expenseDbHelper.name,
                expenseDbHelper.amount,
                expenseDbHelper.expenseCategory,
                expenseDbHelper.day,
                expenseDbHelper.month,
                expenseDbHelper.year};
        Cursor cursor = db.query(expenseDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(expenseDbHelper.name));
            String cat = cursor.getString(cursor.getColumnIndex(expenseDbHelper.expenseCategory));
            double amount = cursor.getDouble(cursor.getColumnIndex(expenseDbHelper.amount));
            int day = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.day));
            int month = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.month));
            int year = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.year));
            buffer.append(name + " " + cat + " " + amount + " " + day + " " + month + " " + year + "\n");
        }
        return buffer.toString();
    }

    public int deleteData() {//musi byc jakies ID do rozpoznawanie co usuwac
        return 0;
    }

    public int updateData() {
        return 0;
    }
}
