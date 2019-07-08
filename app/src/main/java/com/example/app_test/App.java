package com.example.app_test;

import android.app.Application;
import com.example.app_test.di.component.AppComponent;
import com.example.app_test.di.component.DaggerAppComponent;
import com.example.app_test.di.module.AppModule;

public class App extends Application {
    private AppComponent appComponent;
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
