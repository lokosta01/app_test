package com.example.app_test.ui.viewmodel;

import com.example.app_test.network.model.Data;
import java.util.List;

public interface IListFragmentViewModel extends IDataViewModel<List<Data>> {
    void getList();
}
