package com.example.app_test.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app_test.App;
import com.example.app_test.di.component.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(App.getInstance().getAppComponent());
    }
}
