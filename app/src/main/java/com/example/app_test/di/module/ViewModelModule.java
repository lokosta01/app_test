package com.example.app_test.di.module;

import androidx.lifecycle.ViewModel;
import com.example.app_test.di.ViewModelKey;
import com.example.app_test.ui.fragment.cat.CatsFragmentViewModel;
import com.example.app_test.ui.fragment.dog.DogsFragmentViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatsFragmentViewModel.class)
    public abstract ViewModel catsviewmodel(CatsFragmentViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DogsFragmentViewModel.class)
    public abstract ViewModel dogsviewmodel(DogsFragmentViewModel viewModel);
}
