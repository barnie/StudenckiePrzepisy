package com.example.studenckieprzepisy.DodaniePrzepisu;

import Database.Database;
import Database.Kategoria;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.studenckieprzepisy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 27.05.14.
 */
public class AddPrzepis extends Activity {

    private Button add_przepis;
    private Button add_image;
    private EditText nazwa;
    private EditText opis;
    private Spinner kategoria;
    private String image_path;
    private static int RESULT_LOAD_IMAGE = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprzepis);
        add_przepis = (Button) findViewById(R.id.button2);
        add_image = (Button) findViewById(R.id.add_image);
        nazwa = (EditText) findViewById(R.id.nazwa);
        opis = (EditText) findViewById(R.id.opis);
        kategoria = (Spinner) findViewById(R.id.kategoria);
        SharedPreferences shared = AddPrzepis.this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE);
        image_path = shared.getString("zdjecie", ""); //Ustawienie zdjecie
        nazwa.setText(shared.getString("nazwa", ""));
        opis.setText(shared.getString("opis", ""));
        Database db = new Database(getApplicationContext(), null, null, 1);
        ArrayList<String> spinner_miary = new ArrayList<String>();
        List<Kategoria> kat = db.getKategorie();
        for (Kategoria k : kat) {
            spinner_miary.add(k.getName());
        }
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, spinner_miary);
        kategoria.setAdapter(adapter_state);
        kategoria.setSelection(shared.getInt("kategoria", 0));
        if (image_path.length() > 3) {
            add_image.setBackground(Drawable.createFromPath(image_path));
        }
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        add_przepis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nazwa.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getApplicationContext(), "Przepis musi miec nazwe", Toast.LENGTH_LONG).show();
                }
                if (opis.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getApplicationContext(), "Przepis musi miec opis", Toast.LENGTH_LONG).show();
                }
                if (opis.getText().toString().compareTo("") != 0 && nazwa.getText().toString().compareTo("") != 0) {
                    Intent intent = new Intent(getApplicationContext(), AddSkladnik.class);
                    intent.putExtra("nazwa", nazwa.getText().toString());
                    intent.putExtra("opis", opis.getText().toString());
                    intent.putExtra("zdjecie", image_path);
                    intent.putExtra("kategoria", kategoria.getSelectedItemPosition() + 1);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            image_path = cursor.getString(columnIndex);
            cursor.close();
            add_image.setBackground(Drawable.createFromPath(image_path));
            Log.d("IMAGE:", image_path);

        }


    }


    public void saveState() {
        SharedPreferences.Editor pref = this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE).edit();
        pref.putString("nazwa", nazwa.getText().toString());
        pref.putString("opis", opis.getText().toString());
        pref.putInt("kategoria", kategoria.getSelectedItemPosition());
        pref.putString("zdjecie", image_path);
        pref.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
    }
}

