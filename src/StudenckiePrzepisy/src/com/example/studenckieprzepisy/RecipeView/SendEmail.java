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
public class SendEmail implements Send{

    @Override
    public void send( Context c,Przepis p, String output) {
        final Intent result = new Intent(Intent.ACTION_SEND);
        result.putExtra(android.content.Intent.EXTRA_SUBJECT, "Przepis :" + p.getNazwa());
        result.putExtra(android.content.Intent.EXTRA_TEXT, p.getNazwa() + "\n" + output + "\n" + p.getOpis());
        if (p.getZdjecie() != null && p.getZdjecie().compareTo("") != 0 && p.getZdjecie().length() > 9) {
            result.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + p.getZdjecie()));
            result.setType("image/png");
        } else if (p.getZdjecie() != null && p.getZdjecie().compareTo("") != 0) {
            Toast.makeText(c, "Wysylam bez zdjecia/Prawa Autorskie", Toast.LENGTH_LONG).show();
        }
        result.setType("message/rfc822");
        c.startActivity(Intent.createChooser(result, "Wybierz swojego klienta email :"));

    }
}