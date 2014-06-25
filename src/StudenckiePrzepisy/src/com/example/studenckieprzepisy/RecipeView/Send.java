package com.example.studenckieprzepisy.RecipeView;

import android.content.Context;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;

/**
 * Created by piotr on 07.06.14.
 */
public interface Send {
    public void send(Context c, Przepis p, String output);
}
