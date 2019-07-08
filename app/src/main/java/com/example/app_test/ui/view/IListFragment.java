package com.example.app_test.ui.view;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public interface IListFragment extends SwipeRefreshLayout.OnRefreshListener {
    void setRefreshing(boolean refreshing);
}
