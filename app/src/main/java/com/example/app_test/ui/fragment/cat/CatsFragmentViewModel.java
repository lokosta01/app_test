package com.example.app_test.ui.fragment.cat;

import android.util.Log;
import com.example.app_test.network.api.ApiService;
import com.example.app_test.network.model.BaseResponse;
import com.example.app_test.network.model.Data;
import com.example.app_test.ui.viewmodel.DataViewModel;
import com.example.app_test.ui.viewmodel.IListFragmentViewModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class CatsFragmentViewModel extends DataViewModel<List<Data>> implements IListFragmentViewModel {

    private final ApiService api;

    @Inject
    public CatsFragmentViewModel(ApiService api) {
        this.api = api;
    }

    @Override
    public void getList() {
      api.getData("cat")
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
     .subscribe(new DisposableObserver<BaseResponse<List<Data>>>() {
         @Override
         public void onNext(BaseResponse<List<Data>> listBaseResponse) {
             liveData.setValue(listBaseResponse.getData());
         }

         @Override
         public void onError(Throwable e) {
             Log.d(TAG, "[onError] ");
         }

         @Override
         public void onComplete() {

         }
     });
    }

    @Override
    protected void loadData() {
        getList();
    }
}

