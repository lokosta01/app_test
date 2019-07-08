package com.example.app_test.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class DataViewModel<M> extends ViewModel implements IDataViewModel<M> {

    protected MutableLiveData<M> liveData;

    @Override
    public LiveData<M> getData() {
        if (liveData == null){
            liveData = new MutableLiveData<>();
            loadData();
        }
        return liveData;
    }

    protected void loadData(){}


    @Override
    protected void onCleared() {
        liveData = null;
        super.onCleared();
    }
}
