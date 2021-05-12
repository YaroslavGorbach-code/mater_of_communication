package com.YaroslavGorbach.delusionalgenerator.screen.nav;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerNavComponent;
import com.YaroslavGorbach.delusionalgenerator.di.NavComponent;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManager;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManager;

import java.util.Calendar;

public class NavVm extends AndroidViewModel {
    private NavComponent navComponent;

    public NavVm(@NonNull Application app) {
        super(app);
    }

   public NavComponent getNavComponent(Activity activity){
        if (navComponent == null){
            navComponent = DaggerNavComponent.factory().create(activity, ((App)getApplication()).appComponent);
        }
        return navComponent;
    }
}
