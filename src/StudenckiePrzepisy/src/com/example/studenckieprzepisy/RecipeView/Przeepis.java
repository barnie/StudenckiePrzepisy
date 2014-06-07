package com.example.studenckieprzepisy.RecipeView;

import com.example.studenckieprzepisy.Database.Factory.Database;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.PrzepisSkladnik;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studenckieprzepisy.R;

import java.io.InputStream;
import java.util.List;

/**
 * Created by piotr on 18.05.14.
 */


public class Przeepis extends Activity {

    private ImageView zdjecie;
    private TextView skladniki;
    private TextView opis;
    private Przepis p;
    private String output = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przepis);
        zdjecie = (ImageView) findViewById(R.id.zdjecie);
        skladniki = (TextView) findViewById(R.id.skladniki);
        opis = (TextView) findViewById(R.id.opis);

        String przepis = getIntent().getExtras().getString("KEY");
        getActionBar().setTitle(przepis);
        Database db = new Database(getApplicationContext(), null, null, 1);
        p = db.getPrzepis(przepis);
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
        for (PrzepisSkladnik tmp : ps) {
            if (tmp.getIle().compareTo("") != 0)
                output += skladnik.get(tmp.getIdskladnik() - 1).getNazwa() + " : " + tmp.getIle() + "  " + tmp.getMiara() + "\n";
            else
                output += skladnik.get(tmp.getIdskladnik() - 1).getNazwa() + " : ile uznasz za stosowne \n";
        }
        skladniki.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_przepis, menu);
        //menu.add(0, R.id.add_przepis, 0, "Wyslij przepis smsem");
        return true;
    }

    public void sendSms() {
        Intent smsIntent = new Intent(android.content.Intent.ACTION_SEND);
        smsIntent.setType("vnd.android-dir/mms-sms");
        if (p.getZdjecie() != null && p.getZdjecie().compareTo("") != 0 && p.getZdjecie().length() > 9) {
            smsIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + p.getZdjecie()));
            smsIntent.setType("image/png");
        } else if (p.getZdjecie() != null ){
            if (p.getZdjecie().compareTo("") != 0)
                Toast.makeText(getApplicationContext(), "Wysylam bez zdjecia/Prawa Autorskie", Toast.LENGTH_LONG).show();
            Intent intentt = new Intent(Intent.ACTION_VIEW);
            intentt.setData(Uri.parse("sms:"));
            intentt.setType("vnd.android-dir/mms-sms");
            intentt.putExtra("sms_body", p.getNazwa() + "\n" + output + "\n" + p.getOpis());
            startActivity(intentt);
            return;
        }
        smsIntent.putExtra("sms_body", p.getNazwa() + "\n" + output + "\n" + p.getOpis());
        startActivity(Intent.createChooser(smsIntent, "Wybierz swojego klienta sms :"));
    }

    public void sendEmail() {
        final Intent result = new Intent(Intent.ACTION_SEND);
        result.putExtra(android.content.Intent.EXTRA_SUBJECT, "Przepis :" + p.getNazwa());
        result.putExtra(android.content.Intent.EXTRA_TEXT, p.getNazwa() + "\n" + output + "\n" + p.getOpis());
        if (p.getZdjecie() != null && p.getZdjecie().compareTo("") != 0 && p.getZdjecie().length() > 9) {
            result.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + p.getZdjecie()));
            result.setType("image/png");
        } else if (p.getZdjecie() != null && p.getZdjecie().compareTo("") != 0){
            Toast.makeText(getApplicationContext(), "Wysylam bez zdjecia/Prawa Autorskie", Toast.LENGTH_LONG).show();
        }
        result.setType("message/rfc822");
        startActivity(Intent.createChooser(result, "Wybierz swojego klienta email :"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.wyslij_sms:
                sendSms();
                break;
            case R.id.wyslij_email:
                sendEmail();
                break;
        }
        return true;
    }
}