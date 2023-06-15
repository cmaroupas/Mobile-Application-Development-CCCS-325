//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 2;  // update the database version

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RESTAURANT_NAME = "restaurant_name";
    private static final String COLUMN_IMAGE_URL = "image_url";  // new column for image URL

    public FavoritesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_RESTAURANT_NAME + " TEXT,"
                + COLUMN_IMAGE_URL + " TEXT"  // add the new column to the table
                + ")";

        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_FAVORITES + " ADD COLUMN " + COLUMN_IMAGE_URL + " TEXT");
        }
    }

    public long addFavorite(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, restaurant.getId());
        values.put(COLUMN_RESTAURANT_NAME, restaurant.getName());
        values.put(COLUMN_IMAGE_URL, restaurant.getImageUrl());  // add the image URL to the values

        long result = db.insertWithOnConflict(TABLE_FAVORITES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

        return result;
    }

    public boolean removeFavorite(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_FAVORITES, COLUMN_ID + " = ?", new String[]{id});
        db.close();
        return rowsAffected > 0;
    }

    public List<Restaurant> getAllFavorites() {
        List<Restaurant> favorites = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_FAVORITES + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_RESTAURANT_NAME);
        int imageUrlIndex = cursor.getColumnIndex(COLUMN_IMAGE_URL);

        if (cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(cursor.getString(idIndex));
                restaurant.setName(cursor.getString(nameIndex));
                restaurant.setImageUrl(cursor.getString(imageUrlIndex));

                favorites.add(restaurant);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return favorites;
    }

}