package com.example.app_test.ui.fragment.cat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.app_test.R;
import com.example.app_test.di.component.AppComponent;
import com.example.app_test.network.model.Data;
import com.example.app_test.ui.adapter.BaseRecyclerAdapter;
import com.example.app_test.ui.fragment.base.BaseFragment;
import com.example.app_test.ui.view.IListFragment;
import com.example.app_test.ui.viewmodel.IListFragmentViewModel;
import com.example.app_test.ui.viewmodel.ViewModelFactory;
import com.example.app_test.utils.ViewUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CatsFragment extends BaseFragment implements IListFragment {

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Inject
    ViewModelFactory factory;
    private IListFragmentViewModel vm;

    private BaseRecyclerAdapter<Data> adapter = new BaseRecyclerAdapter<Data>() {
        @Override
        protected int getLayoutId(int position, Data obj) {
            return R.layout.item_view;
        }

        @Override
        public RecyclerView.ViewHolder getViewHolder(View view, int viewType) {
            return new ViewHolder(view);
        }
    };

    @Override
    public void onRefresh() {
        vm.getList();
        setRefreshing(false);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
        vm = ViewModelProviders.of(this, factory).get(CatsFragmentViewModel.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        init();
        refresh.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        list.setLayoutManager(new LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false));
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
    }

    private void init() {

        vm.getData().observe(this, data -> {
                    List<Data> items = new ArrayList<>();
                    for (Data coin : data) {
                        if (coin != null) {
                            items.add(coin);
                        }
                    }
                    adapter.setItems(items);
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            BaseRecyclerAdapter.Binder<Data> {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        private View view;

        @BindView(R.id.text)
        TextView text;

        @BindView(R.id.image)
        ImageView image;

        @Override
        public void bind(Data item) {

            text.setText(item.getTitle());

            Picasso.get()
                    .load(item.getUrl())
                    .resize(1200, 1200)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .priority(Picasso.Priority.HIGH)
                    .into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewUtils.navigate(itemView,
                            CatsFragmentDirections.actionCatsGraphToCatFragmentInfo(
                                    item.getTitle(), item.getUrl()));
                }
            });
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        refresh.setRefreshing(refreshing);
    }
}
