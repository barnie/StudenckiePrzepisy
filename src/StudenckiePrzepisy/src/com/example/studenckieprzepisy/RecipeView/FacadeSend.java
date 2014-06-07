package com.example.studenckieprzepisy.RecipeView;

import android.content.Context;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;

/**
 * Created by piotr on 07.06.14.
 */
public class FacadeSend {

    private Send send;
    private Context context;

    private FacadeSend() {
    }

    public FacadeSend(Context c) {
        this.context = c;
    }

    public void sendSms(Przepis p, String skladniki) {
        send = new SendSms();
        send.send(context, p, skladniki);
    }

    public void sendEmail(Przepis p, String skladniki) {
        send = new SendEmail();
        send.send(context, p, skladniki);
    }

}