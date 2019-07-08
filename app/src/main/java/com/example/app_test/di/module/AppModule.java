package com.example.app_test.di.module;

import android.app.NotificationManager;
import android.content.Context;
import com.example.app_test.App;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public App provideApp(){
        return app;
    }

    @Provides
    NotificationManager provideNotificationManager(App app){
        return (NotificationManager) app.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
