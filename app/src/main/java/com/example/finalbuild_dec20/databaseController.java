package com.example.finalbuild_dec20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseController extends SQLiteOpenHelper {

    private static String db_name="finalApp.db";
    private static String my_table="users";
    private static String col_1="ID";
    private static String col_2="USERNAME";
    private static String col_3="PASSWORD";
    private static String col_4="FULLNAME";
    private static String col_5="PHONE";
    private static String col_6="ADDRESS";

    private static String my_table1="clients";
    private static String col1_1="ID";
    private static String col1_2="USERNAME";
    private static String col1_3="PASSWORD";
    private static String col1_4="FULLNAME";
    private static String col1_5="PHONE";
    private static String col1_6="HOMEADD";
    private static String col1_7="EXTRASERV";
    private static String col1_8="CATEGORY";

    private static String my_table2="orders";
    private static String col2_1="ID";
    private static String col2_2="CFULLNAME";
    private static String col2_3="CPHONE";
    private static String col2_4="CHOMEADD";
    private static String col2_5="CSERCATE";
    private static String col2_6="UFULLNAME";
    private static String col2_7="UPHONE";
    private static String col2_8="UHOMEADD";
    private static String col2_9="UREQDETAIL";
    private static String col2_10="CMESSAGE";
    private static String col2_11="CORDERAPP";
    private static String col2_12="RATEDESC";
    private static String col2_13="RATEPERC";
    private static String col2_14="CUMALTIVERATE";

    private static String my_table0="admin";
    private static String col0_1="ID";
    private static String col0_2="USERNAME";
    private static String col0_3="PASSWORD";

    public databaseController(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String USERS_TABLE = "CREATE TABLE " + my_table + " (" + col_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col_2 + " TEXT UNIQUE, " + col_3 + " TEXT, " + col_4 + " TEXT UNIQUE, " + col_5 + " TEXT UNIQUE, " + col_6 + " TEXT)";
        sqLiteDatabase.execSQL(USERS_TABLE);

        String CLIENT_TABLE = "CREATE TABLE " + my_table1 + " (" + col1_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col1_2 + " TEXT UNIQUE, " + col1_3 + " TEXT, " + col1_4 + " TEXT UNIQUE, " + col1_5 + " TEXT UNIQUE, " + col1_6 + " TEXT, " + col1_7 + " TEXT, " + col1_8 + " TEXT)";
        sqLiteDatabase.execSQL(CLIENT_TABLE);

        String ORDERS_TABLE = "CREATE TABLE " + my_table2 + " (" + col2_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col2_2 + " TEXT, " + col2_3 + " TEXT, " + col2_4 + " TEXT, " + col2_5 + " TEXT, " + col2_6 + " TEXT, " + col2_7 + " TEXT, " + col2_8 + " TEXT, " + col2_9 + " TEXT, " + col2_10 + " TEXT, " + col2_11 + " TEXT, " + col2_12 + " TEXT, " + col2_13 + " TEXT, " + col2_14 + " TEXT)";
        sqLiteDatabase.execSQL(ORDERS_TABLE);

        String ADMIN_TABLE = "CREATE TABLE " + my_table0 + " (" + col0_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col0_2 + " TEXT, " + col0_3 + " TEXT)";
        sqLiteDatabase.execSQL(ADMIN_TABLE);

        ContentValues data = new ContentValues();
        data.put(col0_2, "admin");
        data.put(col0_3, "admin");

        sqLiteDatabase.insert(my_table0, null, data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + my_table0);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + my_table);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + my_table1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + my_table2);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUsers(String uname, String passw, String fName, String phone, String address) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(col_2, uname);
        data.put(col_3, passw);
        data.put(col_4, fName);
        data.put(col_5, phone);
        data.put(col_6, address);

        long result = myDatabase.insert(my_table, null, data);

        return result != -1;
    }

    public boolean loginCheckUsr(String un, String pw) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table + " WHERE "+ col_2 +" = ? AND " + col_3 + " = ?", new String[] {un, pw});
        int count = cursor.getCount();
        cursor.close();
        close();

        return count > 0;
    }

    public boolean insertClient(String uname, String passw, String fName, String phone, String address, String extraS, String category) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(col1_2, uname);
        data.put(col1_3, passw);
        data.put(col1_4, fName);
        data.put(col1_5, phone);
        data.put(col1_6, address);
        data.put(col1_7, extraS);
        data.put(col1_8, category);

        long result = myDatabase.insert(my_table1, null, data);

        return result != -1;
    }

    public boolean loginCheckCli(String un, String pw) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table1 + " WHERE "+ col1_2 +" = ? AND " + col1_3 + " = ?", new String[] {un, pw});
        int count = cursor.getCount();
        cursor.close();
        close();

        return count > 0;
    }

    public Cursor getDataForUList() {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table, null);
        return cursor;
    }

    public Cursor getDataForCList() {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table1, null);
        return cursor;
    }

    public Cursor getUserInfo(String fname) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table + " WHERE "+ col_4 +" = ?", new String[] {fname});
        return cursor;
    }

    public Cursor getClientInfo(String fname) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table1 + " WHERE "+ col1_4 +" = ?", new String[] {fname});
        return cursor;
    }

    public boolean insertRequestForm(String cfname, String cphone, String chome, String ccate, String ufname, String uphone, String uhome, String ureqdet) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(col2_2, cfname);
        data.put(col2_3, cphone);
        data.put(col2_4, chome);
        data.put(col2_5, ccate);
        data.put(col2_6, ufname);
        data.put(col2_7, uphone);
        data.put(col2_8, uhome);
        data.put(col2_9, ureqdet);

        long result = myDatabase.insert(my_table2, null, data);

        return result != -1;
    }

    public Cursor getOrder(Integer id) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table2 + " WHERE "+ col2_1 +" = ?", new String[] {String.valueOf(id)});
        return cursor;
    }

    public Cursor getOrderFromName(String name) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table2 + " WHERE "+ col2_6 +" = ?", new String[] {name});
        return cursor;
    }

    public Cursor getUserData(String user) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table + " WHERE "+ col_2 +" = ?", new String[] {user});
        return cursor;
    }

    public Cursor getClientOrders(String user) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table2 + " WHERE "+ col2_2 +" = ?", new String[] {user});
        return cursor;
    }

    public Cursor getClientNameData(String category) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table1 + " WHERE "+ col1_8 +" = ?", new String[] {category});
        return cursor;
    }

    public Cursor getClientData(String user) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + my_table1 + " WHERE "+ col1_2 +" = ?", new String[] {user});
        return cursor;
    }

    public boolean updateFromUser(Integer pk, String ratV, String ratD) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(col2_13, ratV);
        data.put(col2_12, ratD);

        long result = myDatabase.update(my_table2, data, col2_1 + " = ?", new String[] {String.valueOf(pk)});

        return result != -1;
    }

    public boolean updateFromClient(Integer pk, String cMess, String cAppr) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(col2_10, cMess);
        data.put(col2_11, cAppr);

        long result = myDatabase.update(my_table2, data, col2_1 + " = ?", new String[] {String.valueOf(pk)});

        return result != -1;
    }
}
