package com.YaroslavGorbach.delusionalgenerator.component.navmenu;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public interface NavMenu {
    void showNotificationDialog(FragmentManager childFragmentManager);
    void changeNightMod(FragmentActivity activity);
    void showPurchasesDialog(FragmentActivity activity);
    void onNotificationApply(boolean isAllow, String text, Calendar calendar, Context requireContext);
    void queryPurchases(FragmentActivity activity);
    boolean getAdMenuItemAllow();
}
