package com.YaroslavGorbach.delusionalgenerator.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import com.YaroslavGorbach.delusionalgenerator.Fragments.ExercisesFragment_v_2;
//import com.YaroslavGorbach.delusionalgenerator.Fragments.TongueTwisterFragment;
//
//public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
//    final int PAGE_COUNT = 2;
//    private String[] tabTitles = new String[] { "Упражнения","Скороговорки"};
//    private Context context;
//
//    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
//        super(fm);
//        this.context = context;
//    }
//
//    @Override public int getCount() {
//        return PAGE_COUNT;
//    }
//
//    @Override public Fragment getItem(int position) {
//        Fragment fragment = new Fragment();
//        switch (position){
//            case 0:
//                fragment = ExercisesFragment_v_2.newInstance();
//                break;
//            case 1:
//                fragment = TongueTwisterFragment.newInstance();
//                break;
//        }
//        return fragment;
//    }
//
//    @Override public CharSequence getPageTitle(int position) {
//        // генерируем заголовок в зависимости от позиции
//        return tabTitles[position];
//    }
//}
