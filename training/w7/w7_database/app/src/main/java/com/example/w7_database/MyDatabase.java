package com.example.w7_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static String DB_NAME = "MonHoc";
    private static int DB_VERSION = 1;

    // Dinh nghia table MonHoc
    private static final String TB_MONHOCS = "tbMonHoc";
    private static final String ID = "id";
    private static final String COL_MONHOC_MA = "monhoc_ma";
    private static final String COL_MONHOC_TEN = "monhoc_ten";
    private static final String COL_MONHOC_SOTIET = "monhoc_sotiet";
    private Context context;
    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlscript = "CREATE TABLE "+TB_MONHOCS+"(" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                COL_MONHOC_TEN +" TEXT," +
                COL_MONHOC_MA +" TEXT," +
                COL_MONHOC_SOTIET +" TEXT)";
        // Thuc thi scipt.
        db.execSQL(sqlscript);
//                "CREATE TABLE TB_MONHOCS ("
//                +"ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//                +"COL_MONHOC_TEN TEXT, "
//                +"COL_MONHOC_MA TEXT, "
//                +"COL_MONHOC_SOTIET TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_MONHOCS);
        onCreate(db);
    }


    public void getMonHocS(ArrayList<Monhoc> monhocs){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_MONHOCS,
                new String[]{COL_MONHOC_TEN,COL_MONHOC_MA,COL_MONHOC_SOTIET},null,
                null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Monhoc monhoc = new Monhoc();
                monhoc.setmTen(cursor.getString(cursor.getColumnIndex(COL_MONHOC_TEN)));
                monhoc.setmMa(cursor.getString(cursor.getColumnIndex(COL_MONHOC_MA)));
                monhoc.setmTiet(cursor.getString(cursor.getColumnIndex(COL_MONHOC_SOTIET)));
                monhocs.add(monhoc);
            }while (cursor.moveToNext());
        }
    }
    // Insert MonHoc
    // ContentValues object dung de chua cap name/value cua data
    public void insertMonhocs(Monhoc monhoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // muon insert 1 row data su dung put method de insert voi ten cua moi column
        values.put(COL_MONHOC_TEN, monhoc.getmTen());
        values.put(COL_MONHOC_MA, monhoc.getmMa());
        values.put(COL_MONHOC_SOTIET, monhoc.getmTiet());
        db.insert(TB_MONHOCS, null, values);

        db.close();
    }
}
