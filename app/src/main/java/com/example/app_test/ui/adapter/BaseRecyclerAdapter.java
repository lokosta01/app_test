package com.example.app_test.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<T> listItems;

    public BaseRecyclerAdapter() {
        listItems = new ArrayList<>();
    }

    public void setItems(List<T> data) {
        listItems.clear();
        listItems = data;
        notifyDataSetChanged();
    }

    public  void clear() {
        listItems.clear();
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        listItems.add(item);
        notifyItemInserted(listItems.lastIndexOf(item));
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Binder<T>)holder).bind(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position, listItems.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract int getLayoutId(int position, T obj);

    public abstract RecyclerView.ViewHolder getViewHolder(View view, int viewType);

    public interface Binder<T> {
        void bind(T item);
    }

}
