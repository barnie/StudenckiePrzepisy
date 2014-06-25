package com.example.studenckieprzepisy.AddRecipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Kategoria;
import com.example.studenckieprzepisy.Database.Factory.Database;
import com.example.studenckieprzepisy.R;

import java.io.*;
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
    private int PRZEPIS_SIZE = 0;


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
        DateBridge db = new SqlBridge(new Database(getApplicationContext(), null, null, 1));
        PRZEPIS_SIZE = db.getPrzepisy().get(db.getPrzepisy().size() - 1).getId() + 1;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(AddPrzepis.this);
                builder.setMessage("Zrobic zdjecie czy wziac z galerii?")
                        .setCancelable(false)
                        .setPositiveButton("Z galerii", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(
                                        Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                startActivityForResult(i, RESULT_LOAD_IMAGE);
                            }
                        })
                        .setNegativeButton("Zrob zdjecie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, 11);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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

    public static final String CAMERA_IMAGE_BUCKET_NAME =
            Environment.getExternalStorageDirectory().toString()
                    + "/DCIM/Camera";
    public static final String CAMERA_IMAGE_BUCKET_ID =
            getBucketId(CAMERA_IMAGE_BUCKET_NAME);

    /**
     * Matches code in MediaProvider.computeBucketValues. Should be a common
     * function.
     */
    public static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ALERT", "" + resultCode);

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

        } else if (requestCode == 11 && resultCode == RESULT_OK) {
            Bitmap x = (Bitmap) data.getExtras().get("data");
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "title");
            values.put(MediaStore.Images.Media.BUCKET_ID, "test");
            values.put(MediaStore.Images.Media.DESCRIPTION, "test Image taken");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            OutputStream outstream;
            try {
                outstream = getContentResolver().openOutputStream(uri);
                x.compress(Bitmap.CompressFormat.JPEG, 70, outstream);
                outstream.close();
            } catch (FileNotFoundException e) {
                //
            } catch (IOException e) {
                //
            }
            final String[] projection = { MediaStore.Images.Media.DATA };
            final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
            final String[] selectionArgs = { CAMERA_IMAGE_BUCKET_ID };
            final Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    null);
            ArrayList<String> result = new ArrayList<String>(cursor.getCount());
            if (cursor.moveToFirst()) {
                final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                do {
                    final String data1 = cursor.getString(dataColumn);
                    result.add(data1);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (result.size() > 0) {
                image_path = result.get(result.size()-1);
                add_image.setBackground(Drawable.createFromPath(image_path));
            }
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

