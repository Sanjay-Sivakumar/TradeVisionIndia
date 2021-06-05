package com.example.notificationappmsg;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totaltabs;
    public LoginAdapter(FragmentManager fm,Context context,int totaltabs)
    {
        super(fm);
        this.context=context;
        this.totaltabs=totaltabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                LoginTabFragment tablogin=new LoginTabFragment();
                return tablogin;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
