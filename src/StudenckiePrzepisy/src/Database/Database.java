package Database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.studenckieprzepisy.PrzepisSkladnikWybor;

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
                COLUMN_NAZWA + " TEXT, " + COLUMN_ILE + " INT DEFAULT 1 )";
        String CREATE_PRZEPIS_SKLADNIK = "CREATE TABLE " + TABLE_PRZEPIS_SKLADNIK + " ( " +
                COLUMN_IDPRZEPIS + " integer REFERENCES Przepis(id), " + COLUMN_IDSKLADNIK +
                " integer REFERENCES Skladnik(id)," + COLUMN_MIARA + " TEXT, " + COLUMN_ILE +
                " TEXT)";
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

    public void addManyPrzepisSkladnik(List<PrzepisSkladnik> ps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values[] = new ContentValues[ps.size()];
        for (int i = 0; i < ps.size(); i++) {
            values[i] = new ContentValues();
        }
        for (int i = 0; i < ps.size(); i++) {
            values[i].put(COLUMN_IDPRZEPIS, ps.get(i).getIdprzepis());
            values[i].put(COLUMN_IDSKLADNIK, ps.get(i).getIdskladnik());
            values[i].put(COLUMN_MIARA, ps.get(i).getMiara());
            values[i].put(COLUMN_ILE, ps.get(i).getIle());
            db.insert(TABLE_PRZEPIS_SKLADNIK, null, values[i]);
        }
        db.close();
    }

    public void addPrzepisSkladnik(PrzepisSkladnik ps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDPRZEPIS, ps.getIdprzepis());
        values.put(COLUMN_IDSKLADNIK, ps.getIdskladnik());
        values.put(COLUMN_MIARA, ps.getMiara());
        values.put(COLUMN_ILE, ps.getIle());
        db.insert(TABLE_PRZEPIS_SKLADNIK, null, values);
        db.close();
    }

    public void addManySkladnik(List<Skladnik> s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values[] = new ContentValues[s.size()];
        for (int i = 0; i < s.size(); values[i] = new ContentValues(), i++) ;
        for (int i = 0; i < s.size(); i++) {
            values[i].put(COLUMN_NAZWA, s.get(i).getNazwa());
            values[i].put(COLUMN_ILE, s.get(i).getIle());
            db.insert(TABLE_SKLADNIK, null, values[i]);
        }
        db.close();
    }

    public void addSkladnik(Skladnik s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAZWA, s.getNazwa());
        values.put(COLUMN_ILE, s.getIle());
        db.insert(TABLE_SKLADNIK, null, values);
        db.close();
    }

    public void insertManyPrzepis(List<Przepis> p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values[] = new ContentValues[p.size()];
        for (int i = 0; i < p.size(); values[i] = new ContentValues(), i++) ;
        for (int i = 0; i < p.size(); i++) {
            values[i].put(COLUMN_IDKATEGORII, p.get(i).getId_kategori());
            values[i].put(COLUMN_NAZWA, p.get(i).getNazwa());
            values[i].put(COLUMN_OPIS, p.get(i).getOpis());
            values[i].put(COLUMN_ZDJECIE, p.get(i).getZdjecie());
            db.insert(TABLE_PRZEPIS, null, values[i]);
        }
        db.close();
    }

    public void insertPrzepis(Przepis p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDKATEGORII, p.getId_kategori());
        values.put(COLUMN_NAZWA, p.getNazwa());
        values.put(COLUMN_OPIS, p.getOpis());
        values.put(COLUMN_ZDJECIE, p.getZdjecie());
        db.insert(TABLE_PRZEPIS, null, values);
        db.close();
    }

    public void insertManyKategorie(List<Kategoria> k) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values[] = new ContentValues[k.size()];
        for (int i = 0; i < k.size(); values[i] = new ContentValues(), ++i) ;
        for (int i = 0; i < k.size(); i++) {
            values[i].put(COLUMN_RODZAJ, k.get(i).getName());
            values[i].put(COLUMN_ZDJECIE, k.get(i).getImage());
            db.insert(TABLE_KATEGORIE, null, values[i]);
        }
        db.close();
    }

    public void insertKategoria(Kategoria k) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RODZAJ, k.getName());
        values.put(COLUMN_ZDJECIE, k.getImage());
        db.insert(TABLE_KATEGORIE, null, values);
        db.close();
    }

    public void init_kategorie() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues[] values = new ContentValues[6];
        for (int i = 0; i < values.length; i++) {
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
        for (int i = 0; i < values.length; i++) {
            values[i].put(COLUMN_RODZAJ, tt[i][0]);
            values[i].put(COLUMN_ZDJECIE, tt[i][1]);
            db.insert(TABLE_KATEGORIE, null, values[i]);
        }
        db.close();
    }

    public List<Kategoria> getKategorie() {
        List<Kategoria> categories = new ArrayList<Kategoria>();
        String selectQuery = "Select * from " + TABLE_KATEGORIE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                categories.add(new Kategoria(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return categories;
    }

    public List<Przepis> getPrzepisy() {
        List<Przepis> przepisy = new ArrayList<Przepis>();
        String selectQuery = "Select * from " + TABLE_PRZEPIS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                przepisy.add(new Przepis(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return przepisy;
    }

    public Przepis getPrzepis(String nazwa) {
        Przepis przepis = null;
        String query = "Select * FROM " + TABLE_PRZEPIS + " WHERE " + COLUMN_NAZWA + " = \"" + nazwa + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            przepis = new Przepis(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        db.close();
        return przepis;
    }

    public List<Przepis> searchPrzepis(String nazwa){
        String query = "SELECT * FROM " + TABLE_PRZEPIS + " WHERE " + COLUMN_NAZWA + " like \'" + "%" + nazwa + "%" + "\'";
        List<Przepis> przepisy = new ArrayList<Przepis>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                przepisy.add(new Przepis(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return przepisy;
    }

    public List<Przepis> getPrzepisByKategoriaId(int id) {
        List<Przepis> przepisy = new ArrayList<Przepis>();
        String query = "Select * FROM " + TABLE_PRZEPIS + " WHERE " + COLUMN_IDKATEGORII + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                przepisy.add(new Przepis(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        db.close();
        return przepisy;
    }

    public List<String> getMiara() {
        List<String> miary = new ArrayList<String>();
        String query = "Select DISTINCT " + COLUMN_MIARA + " FROM " + TABLE_PRZEPIS_SKLADNIK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                miary.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
        return miary;
    }

    public List<Skladnik> getSkladniki() {
        List<Skladnik> skladniki = new ArrayList<Skladnik>();
        String selectQuery = "Select * from " + TABLE_SKLADNIK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                skladniki.add(new Skladnik(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        return skladniki;
    }

    public List<PrzepisSkladnik> getPrzepisSkladnik(int idPrzepis) {
        List<PrzepisSkladnik> ps = new ArrayList<PrzepisSkladnik>();
        String selectQuery = "Select * FROM " + TABLE_PRZEPIS_SKLADNIK + " WHERE " + COLUMN_IDPRZEPIS + " = \"" + idPrzepis + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    ps.add(new PrzepisSkladnik(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void addPrzepis(int id_Kategorii, String nazwa, String opis, String zdjecie, ArrayList<PrzepisSkladnikWybor> ps) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select max(id) from " + TABLE_PRZEPIS + " ;";
        Cursor cursor = db.rawQuery(query, null);
        int id = 0;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0) + 1;
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_IDKATEGORII, id_Kategorii);
        values.put(COLUMN_NAZWA, nazwa);
        values.put(COLUMN_OPIS, opis);
        values.put(COLUMN_ZDJECIE, zdjecie);
        db.insert(TABLE_PRZEPIS, null, values);
        for (PrzepisSkladnikWybor iterator : ps) {
            ContentValues values1 = new ContentValues();
            values1.put(COLUMN_IDPRZEPIS, id);
            values1.put(COLUMN_IDSKLADNIK, iterator.getSkladnik().getId());
            values1.put(COLUMN_MIARA, iterator.getMiara());
            values1.put(COLUMN_ILE, iterator.getIle());
            db.insert(TABLE_PRZEPIS_SKLADNIK, null, values1);
            values1.clear();
        }
        db.close();
    }

}
