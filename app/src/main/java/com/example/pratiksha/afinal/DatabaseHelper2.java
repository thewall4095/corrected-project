package com.example.pratiksha.afinal;

/**
 * Created by pratiksha on 21/9/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION2 = 1;

    // Database Name
    private static final String DATABASE_NAME2 = "SupplierManager.db";

    // User table name
    private static final String TABLE_SUPPLIER = "supplier";

    // User Table Columns names
    private static final String COLUMN_SUPPLIER_ID = "supplier_id";
    private static final String COLUMN_SUPPLIER_NAME = "supplier_name";
    private static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
    private static final String COLUMN_SUPPLIER_PASSWORD = "supplier_password";

    // create table sql query
    private String CREATE_SUPPLIER_TABLE = "CREATE TABLE " + TABLE_SUPPLIER + "("
            + COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUPPLIER_NAME + " TEXT,"
            + COLUMN_SUPPLIER_EMAIL + " TEXT," + COLUMN_SUPPLIER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_SUPPLIER_TABLE = "DROP TABLE IF EXISTS " + TABLE_SUPPLIER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME2, null, DATABASE_VERSION2);
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        db1.execSQL(CREATE_SUPPLIER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db1.execSQL(DROP_SUPPLIER_TABLE);

        // Create tables again
        onCreate(db1);

    }

    /**
     * This method is to create user record
     *
     * @param supplier
     */
    public void addSupplier(Supplier supplier) {
        SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUPPLIER_NAME, supplier.getName2());
        values.put(COLUMN_SUPPLIER_EMAIL, supplier.getEmail2());
        values.put(COLUMN_SUPPLIER_PASSWORD, supplier.getPassword2());

        // Inserting Row
        db1.insert(TABLE_SUPPLIER, null, values);
        db1.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Supplier> getAllSuppliers() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_SUPPLIER_ID,
                COLUMN_SUPPLIER_EMAIL,
                COLUMN_SUPPLIER_NAME,
                COLUMN_SUPPLIER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_SUPPLIER_NAME + " ASC";
       // List<Supplier> supplierList = new ArrayList<Supplier>();
        List<Supplier> supplierList = new ArrayList<Supplier>();
        SQLiteDatabase db1 = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor2 = db1.query(TABLE_SUPPLIER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor2.moveToFirst()) {
            do {
                Supplier supplier = new Supplier();
                supplier.setId2(Integer.parseInt(cursor2.getString(cursor2.getColumnIndex(COLUMN_SUPPLIER_ID))));
                supplier.setName2(cursor2.getString(cursor2.getColumnIndex(COLUMN_SUPPLIER_NAME)));
                supplier.setEmail2(cursor2.getString(cursor2.getColumnIndex(COLUMN_SUPPLIER_EMAIL)));
                supplier.setPassword2(cursor2.getString(cursor2.getColumnIndex(COLUMN_SUPPLIER_PASSWORD)));
                // Adding user record to list
                supplierList.add(supplier);
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        db1.close();

        // return user list
        return supplierList;
    }

    /**
     * This method to update user record
     *
     * @param supplier
     */
    public void updateSupplier(Supplier supplier) {
        SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SUPPLIER_NAME, supplier.getName2());
        values.put(COLUMN_SUPPLIER_EMAIL, supplier.getEmail2());
        values.put(COLUMN_SUPPLIER_PASSWORD, supplier.getPassword2());

        // updating row
        db1.update(TABLE_SUPPLIER, values, COLUMN_SUPPLIER_ID + " = ?",
                new String[]{String.valueOf(supplier.getId2())});
        db1.close();
    }

    /**
     * This method is to delete user record
     *
     * @param supplier
     */
    public void deleteSupplier(Supplier supplier) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        // delete user record by id
        db1.delete(TABLE_SUPPLIER, COLUMN_SUPPLIER_ID + " = ?",
                new String[]{String.valueOf(supplier.getId2())});
        db1.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkSupplier(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SUPPLIER_ID
        };
        SQLiteDatabase db1 = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_SUPPLIER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor2 = db1.query(TABLE_SUPPLIER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor2.getCount();
        cursor2.close();
        db1.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkSupplier(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SUPPLIER_ID
        };
        SQLiteDatabase db1 = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_SUPPLIER_EMAIL + " = ?" + " AND " + COLUMN_SUPPLIER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor2 = db1.query(TABLE_SUPPLIER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor2.getCount();

        cursor2.close();
        db1.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}