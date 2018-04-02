package io.github.lfasmpao.lyrica;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteAssetHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lyrica.db";
    private static final String TABLE_NAME = "songs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ARTIST= "artist";
    private static final String COLUMN_LYRICS = "lyrics";

    DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addLyrics(Mutator mutator){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, mutator.getTitle());
        contentValues.put(COLUMN_ARTIST, mutator.getArtist());
        contentValues.put(COLUMN_LYRICS, mutator.getLyrics());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateLyrics(Mutator mutator){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, mutator.getTitle());
        contentValues.put(COLUMN_ARTIST, mutator.getArtist());
        contentValues.put(COLUMN_LYRICS, mutator.getLyrics());
        db.update(TABLE_NAME, contentValues, COLUMN_ID + "= ?", new String[]{String.valueOf(mutator.getID())});
        db.close();
    }

    public void deleteLyrics(Mutator mutator){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "= ?", new String[]{String.valueOf(mutator.getID())});
        db.close();
    }

    List<Mutator> getAllLyrics(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        List<Mutator> accessor = new ArrayList<>();
        Mutator mutator;
        if (cursor.getCount() > 0){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                mutator = new Mutator();
                mutator.setID(cursor.getInt(0));
                mutator.setTitle(cursor.getString(1));
                mutator.setArtist(cursor.getString(2));
                mutator.setLyrics(cursor.getString(3));
                accessor.add(mutator);
            }
        }
        cursor.close();
        db.close();
        return accessor;
    }


    List<Mutator> getLyrics(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {COLUMN_ID, COLUMN_TITLE, COLUMN_LYRICS}, COLUMN_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null);
        List<Mutator> accessor = new ArrayList<>();
        Mutator mutator;
        if (cursor.getCount() > 0){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                mutator = new Mutator();
                mutator.setID(cursor.getInt(0));
                mutator.setTitle(cursor.getString(1));
                mutator.setLyrics(cursor.getString(2));
                accessor.add(mutator);
            }
        }
        cursor.close();
        db.close();
        return accessor;
    }
    
    public int getLyricsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
