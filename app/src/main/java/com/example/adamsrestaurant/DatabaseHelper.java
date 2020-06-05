package com.example.adamsrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.activeandroid.util.Log;

import java.text.DecimalFormat;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "AdamsRestaurant.db";

    public static final String USER_TABLE = "Users";
    public static final String UserCOL_1 = "ID";
    public static final String UserCOL_2 = "FullName";
    public static final String UserCOL_3 = "Username";
    public static final String UserCOL_4 = "Email";
    public static final String UserCOL_5 = "MobilePrefix";
    public static final String UserCOL_6 = "MobileNumber";
    public static final String UserCOL_7 = "Password";

    public static final String DISHES_TABLE = "Dishes";
    public static final String DishCOL_1 = "ID";
    public static final String DishCOL_2 = "Category";
    public static final String DishCOL_3 = "Item";
    public static final String DishCOL_4 = "Ingredients";
    public static final String DishCOL_5 = "Spicy";
    public static final String DishCOL_6 = "Lactose";
    public static final String DishCOL_7 = "Nutty";
    public static final String DishCOL_8 = "Gluten";
    public static final String DishCOL_9 = "Vegitarian";
    public static final String DishCOL_10 = "Price";
    public static final String DishCOL_11 = "Link";
    public static final String DishCOL_12 = "Favourite";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+USER_TABLE+" ("+UserCOL_1+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+UserCOL_2+" VARCHAR(50) NOT NULL," + UserCOL_3 +
                " VARCHAR(20), "+UserCOL_4+" TINYTEXT NOT NULL, "+UserCOL_5+" VARCHAR(5),"+UserCOL_6+" VARCHAR(15), "+UserCOL_7 +" LONGTEXT)");


        db.execSQL("CREATE TABLE IF NOT EXISTS "+DISHES_TABLE+" ("+DishCOL_1+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+DishCOL_2+" TINYTEXT NOT NULL,"+DishCOL_3+
                " TINYTEXT NOT NULL, "+DishCOL_4+" TINYTEXT(1) DEFAULT NULL, "+DishCOL_5+" TINYTEXT(1) DEFAULT NULL, "+DishCOL_6+" TINYTEXT(1) DEFAULT NULL, "+DishCOL_7 +
                " TINYTEXT(1) DEFAULT NULL, "+DishCOL_8+" TINYTEXT(1) DEFAULT NULL, "+DishCOL_9+" TINYTEXT(1) DEFAULT NULL, "+DishCOL_10+" DECIMAL(5,2) NOT NULL, "+DishCOL_11+
                " BLOG, "+DishCOL_12+" TINYTEXT(1) DEFAULT NULL)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }

    public boolean insertUser(String fullName, String username, String email, String mobilePrefix, String mobileNumber, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserCOL_2, fullName);
        contentValues.put(UserCOL_3, username);
        contentValues.put(UserCOL_4, email);
        contentValues.put(UserCOL_5, mobilePrefix);
        contentValues.put(UserCOL_6, mobileNumber);
        contentValues.put(UserCOL_7, password);
        long result = db.insert(USER_TABLE, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * From "+USER_TABLE, null);
        return result;
    }

    public Cursor checkUsername(String iusername){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("Select * From "+USER_TABLE+" where "+UserCOL_3+" = ?",new String[]{iusername});
        return result;
    }

    public Cursor checkEmail(String iemail){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("Select * From "+USER_TABLE+" where "+UserCOL_4+" = ?",new String[]{iemail});
        return result;
    }

    public Cursor checkLogIn(String iusernameEmail, String ipassword){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("Select * From "+USER_TABLE+" where ("+UserCOL_4+" = ? OR "+UserCOL_3+" = ?) AND "+UserCOL_7+" = ?",new String[]{iusernameEmail, iusernameEmail, ipassword});
        return result;
    }

    public void insertDish(String category, String item, String ingredients, String spicy, String lactose, String nutty, String gluten, String vegetarian, double  price, byte[] link, String favourites){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DishCOL_2, category);
        contentValues.put(DishCOL_3, item);
        contentValues.put(DishCOL_4, ingredients);
        contentValues.put(DishCOL_5, spicy);
        contentValues.put(DishCOL_6, lactose);
        contentValues.put(DishCOL_7, nutty);
        contentValues.put(DishCOL_8, gluten);
        contentValues.put(DishCOL_9, vegetarian);
        contentValues.put(DishCOL_10, price);
        contentValues.put(DishCOL_11, link);
        contentValues.put(DishCOL_12, favourites);

        long result = db.insert(DISHES_TABLE, null, contentValues);

    }

    public Cursor getCategory(String icategory){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultCategories = db.rawQuery("Select * from "+DISHES_TABLE+" where "+DishCOL_2+" = ?", new String[]{icategory});
        return resultCategories;
    }

    public Cursor getRowAt(String iposition){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor currentRow = db.rawQuery("Select * from "+DISHES_TABLE+" where "+DishCOL_1+" = ?", new String[]{iposition});
        return currentRow;
    }

    public static void deleteDatabase(Context mContext) {
        mContext.deleteDatabase(DATABASE_NAME);
    }
}
