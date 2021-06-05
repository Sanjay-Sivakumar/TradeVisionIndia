package com.example.notificationappmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewParent;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView logo,appName,splashImg;

    private static final int numpages=3;
    private ViewPager viewPager;
    private ScreenSliderPagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

     //   logo=findViewById(R.id.logo);
      //  appName=findViewById(R.id.appname);
       // splashImg=findViewById(R.id.imgbg);
        //lottieAnimationView=findViewById(R.id.lottie);

        viewPager =findViewById(R.id.cuberto);
        pagerAdapter=new ScreenSliderPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

      //  splashImg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
       // logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
       // appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
       // lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);


    }

    private class ScreenSliderPagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSliderPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    onBoardingfragment1 tab1=new onBoardingfragment1();
                    return tab1;
                case 1:
                    onBoardingfragment2 tab2=new onBoardingfragment2();
                    return tab2;
                case 2:
                    onBoardingfragment3 tab3=new onBoardingfragment3();
                    return tab3;
            }
            return  null;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return numpages;
        }
    }
}