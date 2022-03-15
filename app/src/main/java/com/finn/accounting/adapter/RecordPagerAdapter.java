package com.finn.accounting.adapter;

import android.icu.util.LocaleData;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/*
 * @description: Record Pager Adapter
 * @author: Finn
 * @create: 2022-03-10-19-55
 */
public class RecordPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] pageTitle = {"Consumption"};

    public RecordPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public CharSequence getPageTitle(int i){
        return pageTitle[i];
    }
}
