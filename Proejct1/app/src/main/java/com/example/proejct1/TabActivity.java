package com.example.proejct1;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.proejct1.fragment.Fragment1;
import com.example.proejct1.fragment.Fragment2;
import com.example.proejct1.fragment.Fragment3;
import com.example.proejct1.fragment.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout layout;
    private ViewPager2 viewPager2;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewByIds();

        setTab();


    }

    private void findViewByIds() {
        layout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager);

    }

    private void setTab() {
        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFragment(new Fragment1());
        adapter.addFragment(new Fragment2());
        adapter.addFragment(new Fragment3());

        viewPager2.setAdapter(adapter);

        viewPager2.setUserInputEnabled(true);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                layout.selectTab(layout.getTabAt(position));
            }
        });

        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
