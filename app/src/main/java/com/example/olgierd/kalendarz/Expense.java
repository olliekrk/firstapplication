package com.example.olgierd.kalendarz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Expense {

    static class expenseDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "expensesDatabase";
        private static final String TABLE_NAME = "expensesTable";
        private static final int DATABASE_Version = 1;

        private static final String NAME = "Name";
        private static final String CAT = "Category";
        private static final String AMOUNT = "Amount";
        private static final String DAY = "Day";
        private static final String MONTH = "Month";
        private static final String YEAR = "Year";
        private static final String ID = "_id";

        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + NAME + " VARCHAR(255) ,"
                        + CAT + " VARCHAR(225) ,"
                        + AMOUNT + " INTEGER ,"
                        + DAY + " INTEGER ,"
                        + MONTH + " INTEGER ,"
                        + YEAR + " INTEGER );";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private Context context;

        public expenseDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
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
        ContentValues contentValues = new ContentValues();
        contentValues.put(expenseDbHelper.NAME, name);
        contentValues.put(expenseDbHelper.CAT, category);
        contentValues.put(expenseDbHelper.AMOUNT, amount);
        contentValues.put(expenseDbHelper.DAY, day);
        contentValues.put(expenseDbHelper.MONTH, month);
        contentValues.put(expenseDbHelper.YEAR, year);
        long id = db.insert(expenseDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() { //returns whole content of database in string format
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {
                expenseDbHelper.ID,
                expenseDbHelper.NAME,
                expenseDbHelper.AMOUNT,
                expenseDbHelper.CAT,
                expenseDbHelper.DAY,
                expenseDbHelper.MONTH,
                expenseDbHelper.YEAR
        };
        Cursor cursor = db.query(expenseDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.ID));
            String name = cursor.getString(cursor.getColumnIndex(expenseDbHelper.NAME));
            String cat = cursor.getString(cursor.getColumnIndex(expenseDbHelper.CAT));
            double amount = cursor.getDouble(cursor.getColumnIndex(expenseDbHelper.AMOUNT));
            int day = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.DAY));
            int month = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.MONTH));
            int year = cursor.getInt(cursor.getColumnIndex(expenseDbHelper.YEAR));
            buffer.append(id + ". " + cat + ": " + name + " " + amount + "PLN " + day + "-" + month + "-" + year + "\n");
        }
        return buffer.toString();
    }

    public int deleteData(String expenseID) {//is it possible to somehow pass the id of expense we wish to delete?
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {expenseID};
        int count = db.delete(expenseDbHelper.TABLE_NAME, expenseDbHelper.ID + " =  ?", whereArgs);
        return count;
    }
    // ^ need a function to get ID of chosen expense

    public int updateData() {//not necessary to implement
        return 0;
    }
}
