package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 19.04.14.
 */
public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food.db";
    //tables:
    private static final String TABLE_KATEGORIE = "Kategorie";
    private static final String TABLE_PRZEPIS = "Przepis";
    private static final String TABLE_SKLADNIK = "Skladnik";
    private static final String TABLE_PRZEPIS_SKLADNIK = "Przepis_Skladnik";
    //columns:
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RODZAJ = "rodzaj";
    private static final String COLUMN_ZDJECIE = "zdjecie";
    private static final String COLUMN_IDKATEGORII = "id_kategorii";
    private static final String COLUMN_NAZWA = "nazwa";
    private static final String COLUMN_OPIS = "opis";
    private static final String COLUMN_ILE = "ile";
    private static final String COLUMN_IDPRZEPIS = "id_przepis";
    private static final String COLUMN_IDSKLADNIK = "id_skladnik";
    private static final String COLUMN_MIARA = "miara";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KATEGORIE = "CREATE TABLE  " + TABLE_KATEGORIE + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RODZAJ +
                " TEXT, " + COLUMN_ZDJECIE + " TEXT ) ";
        String CREATE_PRZEPIS = "CREATE TABLE " + TABLE_PRZEPIS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDKATEGORII + " integer REFERENCES Kategorie(id), " +
                COLUMN_NAZWA + " TEXT, " + COLUMN_OPIS + " TEXT, " + COLUMN_ZDJECIE + " TEXT )";
        String CREATE_SKLADNIK = "CREATE TABLE " + TABLE_SKLADNIK + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAZWA + " TEXT, " + COLUMN_ILE + "INT DEFAULT 1 )";
        String CREATE_PRZEPIS_SKLADNIK = "CREATE TABLE " + TABLE_PRZEPIS_SKLADNIK + " ( " +
                COLUMN_IDPRZEPIS + " integer REFERENCES Przepis(id), " + COLUMN_IDSKLADNIK +
                " integer REFERENCES Skladnik(id)," + COLUMN_MIARA + " TEXT, " + COLUMN_ILE +
                " TEXT, PRIMARY KEY(id_przepis,id_skladnik))";
        db.execSQL(CREATE_KATEGORIE);
        db.execSQL(CREATE_PRZEPIS);
        db.execSQL(CREATE_SKLADNIK);
        db.execSQL(CREATE_PRZEPIS_SKLADNIK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRZEPIS_SKLADNIK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKLADNIK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRZEPIS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_KATEGORIE);
        onCreate(db);
    }

    public void test() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, 1);
        values.put(COLUMN_RODZAJ, "A");
        values.put(COLUMN_ZDJECIE, "B");
        db.insert(TABLE_KATEGORIE, null, values);
        db.close();
    }

    public void insertManyKategorie(List<Kategoria> k){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values[] = new ContentValues[k.size()];
        for (int i = 0; i < k.size(); values[i] = new ContentValues(), ++i);
        for (int i = 0; i < k.size(); i++){
            values[i].put(COLUMN_RODZAJ, k.get(i).getName());
            values[i].put(COLUMN_ZDJECIE, k.get(i).getImage());
            db.insert(TABLE_KATEGORIE, null, values[i]);
        }
        db.close();
    }

    public void insertKategoria(Kategoria k){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RODZAJ, k.getName());
        values.put(COLUMN_ZDJECIE, k.getImage());
        db.insert(TABLE_KATEGORIE, null,values);
        db.close();
    }

    public void init_kategorie() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues[] values = new ContentValues[6];
        for (int i = 0; i < values.length; i++){
            values[i] = new ContentValues();
        }
        String[][] tt;
        tt = new String[][]{
                new String[]{"Danie główne", "kat1"},
                new String[]{"Zupy", "kat2"},
                new String[]{"Sałatki i surówki", "kat3"},
                new String[]{"Desery", "kat4"},
                new String[]{"Studenckie", "kat5"},
                new String[]{"Inne", "kat6"}
        };
        for (int i = 0; i < values.length; i++){
            values[i].put(COLUMN_RODZAJ, tt[i][0]);
            values[i].put(COLUMN_ZDJECIE, tt[i][1]);
            db.insert(TABLE_KATEGORIE,null,values[i]);
        }
        db.close();
    }
    public List<Kategoria> getKategorie(){
        List<Kategoria> categories = new ArrayList<Kategoria>();
        String selectQuery = "Select * from " + TABLE_KATEGORIE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                categories.add(new Kategoria(cursor.getInt(0),cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return categories;
    }

}
