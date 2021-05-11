package com.YaroslavGorbach.delusionalgenerator.workflow.navworkflow;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManager;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManager;

import java.util.Calendar;

public class NavWorkflowVm extends ViewModel {
    private final Repo mRepo;
    public final BillingManager billingManager;
    private final MyNotificationManager mMyNotificationManager;

    public NavWorkflowVm(Repo repo, BillingManager billingManager, MyNotificationManager myNotificationManager) {
        mRepo = repo;
        this.billingManager = billingManager;
        mMyNotificationManager = myNotificationManager;
    }

    public void showNotificationDialog(FragmentManager fragmentManager) {
        NotificationDialog dialog =  new NotificationDialog();
        dialog.setArguments(NotificationDialog.argsOf(
                mRepo.getNotificationCalendar().get(Calendar.HOUR_OF_DAY),
                mRepo.getNotificationCalendar().get(Calendar.MINUTE),
                mRepo.getNotificationText(),
                mRepo.getNotificationIsAllow()));
        dialog.show(fragmentManager, null);
    }
    public void changeNightMod(Activity activity){
        mRepo.setNightMod(!mRepo.getNightMod());
        if (mRepo.getNightMod()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public void disallowAd() {
        mRepo.setAdIsAllow(false);
    }

    public boolean getAdIsAllow() {
        return mRepo.getAdIsAllow();
    }

    public void setNotification(boolean isAllow, String text, Calendar calendar, Context context) {
        mRepo.setNotificationIsAllow(isAllow);
        mRepo.setNotificationText(text);
        mRepo.setNotificationCalendar(calendar);
        NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);
        if (isAllow){
            mMyNotificationManager.sendNotificationOnTime(notificationManager, context, calendar.getTimeInMillis(), text);
        }else {
            if (notificationManager != null) {
                notificationManager.cancelAll();
            }
        }
    }

    public static class NavWorkflowVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final BillingManager billingManager;
        private final MyNotificationManager myNotificationManager;

        public NavWorkflowVmFactory(Repo repo, BillingManager billingManager, MyNotificationManager myNotificationManager) {
            super();
            this.repo = repo;
            this.billingManager = billingManager;
            this.myNotificationManager = myNotificationManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(NavWorkflowVm.class)) {
                return (T) new NavWorkflowVm(repo, billingManager, myNotificationManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
