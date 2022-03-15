package com.finn.accounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.finn.accounting.adapter.RecordPagerAdapter;
import com.finn.accounting.fragment.ConsumptionFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_view_page);

        this.initPager();
    }

    private void initPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        ConsumptionFragment consumptionFragment = new ConsumptionFragment();
        fragmentList.add(consumptionFragment);
        RecordPagerAdapter pagerAdapter = new RecordPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /* click */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_close_btn:
                finish();
                break;
        }
    }
}