package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import Database.PrzepisSkladnik;
import Database.Skladnik;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by piotr on 18.05.14.
 */


public class Przeepis extends Activity {

    private ImageView zdjecie;
    private TextView skladniki;
    private TextView opis;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przepis);
        zdjecie = (ImageView) findViewById(R.id.zdjecie);
        skladniki = (TextView) findViewById(R.id.skladniki);
        opis = (TextView) findViewById(R.id.opis);

        String przepis = getIntent().getExtras().getString("KEY");
        getActionBar().setTitle(przepis);
        Database db = new Database(getApplicationContext(), null, null, 1);
        Przepis p = db.getPrzepis(przepis);
        opis.setText(p.getOpis());
        AssetManager assetManager = getAssets();
        try {
            Drawable d = null;
            if (p.getZdjecie().length() < 9) {
                InputStream ims = assetManager.open(p.getZdjecie());
                d = Drawable.createFromStream(ims, null);
            } else {
                d = Drawable.createFromPath(p.getZdjecie());
            }
            if (d != null)
                zdjecie.setImageDrawable(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<Skladnik> skladnik = db.getSkladniki();
        List<PrzepisSkladnik> ps = db.getPrzepisSkladnik(p.getId());
        for (Skladnik s : skladnik) {
            Log.d("###", s.toString());
        }
        String output = "";
        for (PrzepisSkladnik tmp : ps) {
            if (tmp.getIle().compareTo("") != 0)
                output += skladnik.get(tmp.getIdskladnik() - 1).getNazwa() + " : " + tmp.getIle() + "  " + tmp.getMiara() + "\n";
            else
                output += skladnik.get(tmp.getIdskladnik() - 1).getNazwa() + " : ile uznasz za stosowne \n";
        }
        skladniki.setText(output);
    }
}