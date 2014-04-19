package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String COLUMN_IDKATEGORII="id_kategorii";
    private static final String COLUMN_NAZWA = "nazwa";
    private static final String COLUMN_OPIS = "opis";
    private static final String COLUMN_ILE = "ile";
    private static final String COLUMN_IDPRZEPIS="id_przepis";
    private static final String COLUMN_IDSKLADNIK="id_skladnik";
    private static final String COLUMN_MIARA = "miara";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KATEGORIE = "CREATE TABLE  " + TABLE_KATEGORIE + " ( "+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RODZAJ +
                 " TEXT, " + COLUMN_ZDJECIE + " TEXT ) ";
        String CREATE_PRZEPIS = "CREATE TABLE " + TABLE_PRZEPIS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDKATEGORII + " integer REFERENCES Kategorie(id), " +
                COLUMN_NAZWA + " TEXT, " + COLUMN_OPIS + " TEXT, " + COLUMN_ZDJECIE + " TEXT )";
        String CREATE_SKLADNIK = "CREATE TABLE " + TABLE_SKLADNIK + " ( "+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAZWA + " TEXT, " + COLUMN_ILE + "INT DEFAULT 1 )";
        String CREATE_PRZEPIS_SKLADNIK = "CREATE TABLE " + TABLE_PRZEPIS_SKLADNIK + " ( "+
                COLUMN_IDPRZEPIS + " integer REFERENCES Przepis(id), " + COLUMN_IDSKLADNIK +
                " integer REFERENCES Skladnik(id)," + COLUMN_MIARA + " TEXT, " + COLUMN_ILE +
                " TEXT, PRIMARY KEY(id_przepis,id_skladnik))";
        Log.d("DB","DATA1");
        db.execSQL(CREATE_KATEGORIE);
        Log.d("DB","DATA1");
        db.execSQL(CREATE_PRZEPIS);
        Log.d("DB","DATA1");
        db.execSQL(CREATE_SKLADNIK);
        Log.d("DB","DATA1");
        db.execSQL(CREATE_PRZEPIS_SKLADNIK);
        Log.d("DB","DATA1");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRZEPIS_SKLADNIK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKLADNIK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRZEPIS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_KATEGORIE);
        onCreate(db);
    }
    public void test(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,1);
        values.put(COLUMN_RODZAJ,"A");
        values.put(COLUMN_ZDJECIE,"B");
        db.insert(TABLE_KATEGORIE,null,values);
        db.close();
    }
}
