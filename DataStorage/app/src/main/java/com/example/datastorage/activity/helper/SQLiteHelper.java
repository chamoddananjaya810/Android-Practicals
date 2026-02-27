package com.example.datastorage.activity.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
private static final String DATABASE_NAME="students.db";

private static final int DB_VERSION=1;

private static  SQLiteHelper sqLiteHelper;

    private SQLiteHelper(@Nullable Context context) {
        super(context, SQLiteHelper.DATABASE_NAME, null, DB_VERSION);
    }

    @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String studentTableQuery = "CREATE TABLE `student` (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(150), " +
                "age INTEGER" +
                ")";

        sqLiteDatabase.execSQL(studentTableQuery);
        }
public static synchronized SQLiteHelper getInstance(Context context){
        if (sqLiteHelper ==null){
            sqLiteHelper=new SQLiteHelper(context.getApplicationContext());
        }
        return sqLiteHelper;
}
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String dropStudentTable = "DROP TABLE IF EXISTS `student`";

        // Pass the SQL string here
        sqLiteDatabase.execSQL(dropStudentTable);

        // Re-run onCreate to create the fresh table
        onCreate(sqLiteDatabase);
    }




    }
