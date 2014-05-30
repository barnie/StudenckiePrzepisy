package com.example.studenckieprzepisy;

import Database.Database;
import Database.Skladnik;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by piotr on 27.05.14.
 */
public class AddPrzepis extends Activity {

    private Button add_przepis;
    private Button add_image;
    private EditText nazwa;
    private EditText opis;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprzepis);
        add_przepis = (Button) findViewById(R.id.button2);
        add_image = (Button) findViewById(R.id.add_image);
        nazwa = (EditText) findViewById(R.id.nazwa);
        opis = (EditText) findViewById(R.id.opis);
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
                    intent.putExtra("opis", nazwa.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }


}