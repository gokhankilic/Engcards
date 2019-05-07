package tr.com.gokhan.kilic.engcards.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import tr.com.gokhan.kilic.engcards.models.Word;

public class Database extends SQLiteOpenHelper {

    private static String DB_NAME = "WordApplication.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();

    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }



    public ArrayList<Word> getWords(boolean isCustom) {
        String TABLE_NAME = "Words";

        int isCustomWords;

        if(isCustom){
            isCustomWords = 1;
        }else{
            isCustomWords = 0;
        }

        ArrayList<Word> words = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE is_custom=" + isCustomWords;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                boolean isInList = false;
                boolean isLearned = false;

                if(cursor.getInt(3) == 1){
                    isInList = true;
                }
                if(cursor.getInt(4) == 1){
                    isLearned = true;
                }


                Word word = new Word(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        isInList,
                        isLearned,
                        cursor.getInt(5));



                words.add(word);
            } while (cursor.moveToNext());
        }
        db.close();
        return words;
    }

    public Word getWordById(int id){
        String TABLE_NAME = "Words";

       Word word = new Word();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                boolean isInList = false;
                boolean isLearned = false;

                if(cursor.getInt(3) == 1){
                    isInList = true;
                }
                if(cursor.getInt(4) == 1){
                    isLearned = true;
                }


                word = new Word(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        isInList,
                        isLearned,
                        cursor.getInt(5));
            } while (cursor.moveToNext());
        }
        db.close();
        return word;
    }




    public void updateWords(int wordId, boolean isFavorited){

        int isWordFavorited;

        if(isFavorited){
            isWordFavorited = 1;
        }else{
            isWordFavorited = 0;
        }
        String selectQuery = "UPDATE Words SET is_in_list=" + isWordFavorited + " WHERE id=" + wordId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
            } while (cursor.moveToNext());
        }
        db.close();


    }



    public ArrayList<Word> getFavoritedWords() {
        String TABLE_NAME = "Words";

        ArrayList<Word> words = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE is_in_list=1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            do {
                boolean isInList = false;
                boolean isLearned = false;

                if(cursor.getInt(3) == 1){
                    isInList = true;
                }
                if(cursor.getInt(4) == 1){
                    isLearned = true;
                }

                Word word = new Word(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        isInList,
                        isLearned,
                        cursor.getInt(5));



                words.add(word);
            } while (cursor.moveToNext());
        }
        db.close();
        return words;
    }


}
