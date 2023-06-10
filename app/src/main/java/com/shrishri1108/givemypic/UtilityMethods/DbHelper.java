package com.shrishri1108.givemypic.UtilityMethods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shrishri1108.givemypic.Activity.compress_image_code.CompressedImageModel;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_images";
    private static final int DATABASE_VERSION = 1;
    private String TABLE_NAME;
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String TYPE_COL = "type";
    private static final String COMPRESSION_RATIO_COL = "compression_ratio";
    private static final String IMAGE_COL = "image";
    private static final String SIZE_COL = "size";
    private static final String CREATED_COL = "created_at";

    public DbHelper(Context context, String Table_Name) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.TABLE_NAME = Table_Name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                NAME_COL + " TEXT, " +
                IMAGE_COL +" IMAGE," +
                COMPRESSION_RATIO_COL + " TEXT," +
                SIZE_COL + " TEXT," +
                TYPE_COL + " TEXT," +
                CREATED_COL + " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    public void addNewImage(String img_name ,byte [] img_val, String img_compress_ratio , String img_size , String img_types , String created_at ) {

        SQLiteDatabase dbs = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(NAME_COL , img_name);
        newValues.put(IMAGE_COL , img_val);
        newValues.put(COMPRESSION_RATIO_COL , img_compress_ratio);
        newValues.put(SIZE_COL , img_size);
        newValues.put(TYPE_COL , img_types);
        newValues.put(CREATED_COL , created_at);

        dbs.insert(TABLE_NAME , null  , newValues );

        dbs.close();
    }

    public ArrayList<CompressedImageModel> getAllImages(){
         SQLiteDatabase dbs = this.getReadableDatabase();
        Cursor compressedImgCursor = dbs.rawQuery("SELECT *  FROM "+ TABLE_NAME , null );
        ArrayList<CompressedImageModel> compressedImageLists = new ArrayList<>();
         compressedImageLists.add(new CompressedImageModel());
        if (compressedImgCursor.moveToFirst()) {
            do {
                 compressedImageLists.add(new CompressedImageModel( compressedImgCursor.getInt(0),
                         compressedImgCursor.getString(1),
                         compressedImgCursor.getBlob(2),
                         compressedImgCursor.getString(3),
                         compressedImgCursor.getString(4),
                         compressedImgCursor.getString(5),
                         compressedImgCursor.getString(6)));
            }   while ( compressedImgCursor.moveToNext());
        }
        compressedImgCursor.close();
        return compressedImageLists ;
    }
}

