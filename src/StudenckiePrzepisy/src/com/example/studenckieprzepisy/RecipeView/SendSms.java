package com.example.studenckieprzepisy.RecipeView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;

/**
 * Created by piotr on 07.06.14.
 */
public class SendSms implements Send {

    @Override
    public void send(Context c, Przepis p, String skladniki) {
        Intent smsIntent = new Intent(android.content.Intent.ACTION_SEND);
        smsIntent.setType("vnd.android-dir/mms-sms");
        if (p.getZdjecie() != null && p.getZdjecie().compareTo("") != 0 && p.getZdjecie().length() > 9) {
            smsIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + p.getZdjecie()));
            smsIntent.setType("image/png");
        } else if (p.getZdjecie() != null) {
            if (p.getZdjecie().compareTo("") != 0)
                Toast.makeText(c, "Wysylam bez zdjecia/Prawa Autorskie", Toast.LENGTH_LONG).show();
            Intent intentt = new Intent(Intent.ACTION_VIEW);
            intentt.setData(Uri.parse("sms:"));
            intentt.setType("vnd.android-dir/mms-sms");
            intentt.putExtra("sms_body", p.getNazwa() + "\n" + skladniki + "\n" + p.getOpis());
            c.startActivity(intentt);
            return;
        }
        smsIntent.putExtra("sms_body", p.getNazwa() + "\n" + skladniki + "\n" + p.getOpis());
        c.startActivity(Intent.createChooser(smsIntent, "Wybierz swojego klienta sms :"));
    }
}
