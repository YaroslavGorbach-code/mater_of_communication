package com.YaroslavGorbach.delusionalgenerator.component.navmenu;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManager;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManager;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.NotificationDialog;

import java.util.Calendar;

public class NavMenuImp implements NavMenu {
    private final BillingManager mBillingManager;
    private final Repo mRepo;
    private final MyNotificationManager mNotificationManager;

    public NavMenuImp(BillingManager billingManager, Repo repo, MyNotificationManager notificationManager) {
        mBillingManager = billingManager;
        mRepo = repo;
        mNotificationManager = notificationManager;
    }

    @Override
    public void showNotificationDialog(FragmentManager childFragmentManager) {
        NotificationDialog dialog = new NotificationDialog();
        dialog.setArguments(NotificationDialog.argsOf(
                mRepo.getNotificationCalendar().get(Calendar.HOUR_OF_DAY),
                mRepo.getNotificationCalendar().get(Calendar.MINUTE),
                mRepo.getNotificationText(),
                mRepo.getNotificationIsAllow()));
        dialog.show(childFragmentManager, null);
    }

    @Override
    public void changeNightMod(FragmentActivity activity) {
        mRepo.setNightMod(!mRepo.getNightMod());
        if (mRepo.getNightMod()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void showPurchasesDialog(FragmentActivity activity) {
        mBillingManager.showPurchasesDialog(activity);
    }

    @Override
    public void onNotificationApply(boolean isAllow, String text, Calendar calendar, Context context) {
        mRepo.setNotificationIsAllow(isAllow);
        mRepo.setNotificationText(text);
        mRepo.setNotificationCalendar(calendar);
        NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);
        if (isAllow) {
            mNotificationManager.sendNotificationOnTime(notificationManager, context, calendar.getTimeInMillis(), text);
        } else {
            if (notificationManager != null) {
                notificationManager.cancelAll();
            }
        }
    }

    @Override
    public void queryPurchases(FragmentActivity activity) {
        mBillingManager.queryPurchases(isAdRemoved -> {
            if (isAdRemoved && mRepo.getAdIsAllow()) {
                mRepo.setAdIsAllow(false);
                activity.finish();
                activity.startActivity(new Intent(activity, activity.getClass()));
            }
        });
    }

    @Override
    public boolean getAdMenuItemAllow() {
        return !mRepo.getAdIsAllow();
    }
}
