package com.example.lihuanhuan20190120.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lihuanhuan20190120.R;
import com.example.lihuanhuan20190120.fragment.CartFragment;
import com.example.lihuanhuan20190120.fragment.WdFragment;

public class Main2Activity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cart:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_wd:
                    viewPager.setCurrentItem(1);
                    return true;

            }
            return false;
        }
    };
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = findViewById(R.id.viewPager);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new CartFragment();
                    case 1:
                        return new WdFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_cart);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_wd);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
