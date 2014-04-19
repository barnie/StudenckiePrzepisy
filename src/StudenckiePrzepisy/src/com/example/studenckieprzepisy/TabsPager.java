package com.example.studenckieprzepisy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class TabsPager extends FragmentPagerAdapter {

    public TabsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                Log.d("Adapter", "ADAPTER");
                return new TabPierwszy();
            case 1:
                return new tabDrugi();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
