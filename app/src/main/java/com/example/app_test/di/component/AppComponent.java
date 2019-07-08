package com.example.app_test.di.component;

import com.example.app_test.di.module.ApiModule;
import com.example.app_test.di.module.AppModule;
import com.example.app_test.di.module.ViewModelModule;
import com.example.app_test.ui.fragment.cat.CatFragmentInfo;
import com.example.app_test.ui.fragment.cat.CatsFragment;
import com.example.app_test.ui.fragment.dog.DogFragmentInfo;
import com.example.app_test.ui.fragment.dog.DogsFragment;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, ViewModelModule.class})
public interface AppComponent {

    void inject(CatsFragment fragment);
    void inject(CatFragmentInfo fragment);
    void inject(DogsFragment fragment);
    void inject(DogFragmentInfo fragment);
}
