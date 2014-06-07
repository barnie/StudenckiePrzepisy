package com.example.studenckieprzepisy;

import com.example.studenckieprzepisy.AdvancedSearch.AdvancedSearch;
import com.example.studenckieprzepisy.Database.Factory.Database;
import com.example.studenckieprzepisy.Database.DatabaseInit;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.AddRecipe.AddPrzepis;
import com.example.studenckieprzepisy.RecipeView.Przeepis;

import java.util.List;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {
    private ViewPager viewPager;
    private TabsPager mAdapter;
    private ActionBar actionBar;

    private String[] tabs = {"Kategorie", "Przepisy"};
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        mAdapter = new TabsPager(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (String tab_name : tabs)
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        prefs = getSharedPreferences("com.example.studenckieprzepisy", MODE_PRIVATE);
        boolean firstRun = prefs.getBoolean("INSTALL", false);
        if (!firstRun) {
            prefs.edit().putBoolean("INSTALL", true).commit();
            DatabaseInit dinit = new DatabaseInit(getApplicationContext());
            dinit.initDB();
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, R.id.add_przepis, 0, "Dodaj Przepis");
        menu.add(1, R.id.search, 0, "Szukanie po nazwie");
        menu.add(2, R.id.advanced_search, 0, "Szukanie po skladnikach");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_przepis:
                Intent intent = new Intent(this, AddPrzepis.class);
                startActivity(intent);
                break;
            case R.id.search:
                final EditText input = new EditText(this);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Wyszukaj Przepis")
                        .setMessage("Wpisz nazwe przepisu")
                        .setView(input)
                        .setPositiveButton("Szukaj", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String value = input.getText().toString();
                                Database db = new Database(getApplicationContext(), null, null, 1);
                                List<Przepis> przepisy = db.searchPrzepis(value.trim());
                                buildListViewDialog(przepisy);
                            }
                        }).setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
                break;
            case R.id.advanced_search:
                Intent intent1 = new Intent(MainActivity.this, AdvancedSearch.class);
                startActivity(intent1);
                break;
        }
        return true;
    }

    public void buildListViewDialog(List<Przepis> przepisy){
        if (przepisy.size() == 0){
            Toast.makeText(getApplicationContext(), "Nie znaleziono przepisow pasujacych do wzorca", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                MainActivity.this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Wyszukane Przepisy");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_singlechoice);
        for (Przepis p: przepisy){
            arrayAdapter.add(p.getNazwa());
        }
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        Intent intent = new Intent(MainActivity.this, Przeepis.class);
                        intent.putExtra("KEY", strName);
                        startActivity(intent);
                    }
                });
        builderSingle.show();
    }

}