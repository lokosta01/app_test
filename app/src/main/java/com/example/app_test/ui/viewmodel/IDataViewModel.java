package com.example.app_test.ui.viewmodel;

import androidx.lifecycle.LiveData;

public interface IDataViewModel<M> {
    LiveData<M> getData();
}
