package com.YaroslavGorbach.delusionalgenerator.di;

import android.app.Activity;

import com.YaroslavGorbach.delusionalgenerator.component.navmenu.NavMenu;
import com.YaroslavGorbach.delusionalgenerator.component.navmenu.NavMenuImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManager;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManager;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManagerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.NavFragment;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@ViewModelScope
@Component(dependencies = AppComponent.class, modules = {NavComponent.NavModule.class})
public interface NavComponent {

    void inject(NavFragment navFragment);

    @Component.Factory
    interface Factory{
        NavComponent create(@BindsInstance Activity activity, AppComponent appComponent);
    }

    @Module
    class NavModule{

        @ViewModelScope
        @Provides
        BillingManager provideBillingManager(Activity activity){
            return new BillingManagerImp(activity);
        }

        @ViewModelScope
        @Provides
        MyNotificationManager provideMyNotificationManager(){
            return new MyNotificationManagerImp();
        }

        @ViewModelScope
        @Provides
        NavMenu provideNavMenu(BillingManager billingManager, Repo repo, MyNotificationManager notificationManager){
            return new NavMenuImp(billingManager, repo, notificationManager);
        }
    }
}
