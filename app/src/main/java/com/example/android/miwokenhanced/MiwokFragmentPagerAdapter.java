package com.example.android.miwokenhanced;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */

public class MiwokFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MiwokFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        }
        else if (position == 1){
            return new FamilyFragment();
        }
        else if (position == 2){
            return new ColoursFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4; //The swipe count will be the number specified here. In the case of "return 3", we will only be able to swipe from Numbers-Colours
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_numbers);
        } else if (position == 1) {
            return context.getString(R.string.category_family);
        } else if (position == 2) {
            return context.getString(R.string.category_colours);
        } else {
            return context.getString(R.string.category_phrases);
        }
    }

}
