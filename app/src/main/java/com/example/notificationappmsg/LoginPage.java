package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.function.LongFunction;

public class LoginPage extends AppCompatActivity {

    TabLayout tablelayoutlogin;
    ViewPager viewPagerlogin;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        tablelayoutlogin=findViewById(R.id.tablelayoutLogin);
        viewPagerlogin=findViewById(R.id.viewpagerlogin);

        tablelayoutlogin.addTab(tablelayoutlogin.newTab().setText("LOGIN!"));
        //tablelayoutlogin.addTab(tablelayoutlogin.newTab().setText("SignUp!"));
        tablelayoutlogin.setTabGravity(tablelayoutlogin.GRAVITY_FILL);

        final LoginAdapter adapter=new LoginAdapter(getSupportFragmentManager(),LoginPage.this,tablelayoutlogin.getTabCount());
        viewPagerlogin.setAdapter(adapter);

        viewPagerlogin.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablelayoutlogin));

        tablelayoutlogin.setTranslationY(300);

        tablelayoutlogin.setAlpha(v);

        tablelayoutlogin.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }
}