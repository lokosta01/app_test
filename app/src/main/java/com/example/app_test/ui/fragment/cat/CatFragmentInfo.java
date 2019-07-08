package com.example.app_test.ui.fragment.cat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.app_test.R;
import com.example.app_test.di.component.AppComponent;
import com.example.app_test.ui.fragment.base.BaseFragment;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import butterknife.BindView;

public class CatFragmentInfo extends BaseFragment {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.title)
    TextView title;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.cat_fragment_info;
    }

    public String getTitle(){
      return CatFragmentInfoArgs.fromBundle(getArguments()).getTitle();
    }

    public String getImage(){
        return CatFragmentInfoArgs.fromBundle(getArguments()).getUrl();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       title.setText(getTitle());
         Picasso.get()
                .load(getImage())
//                .resize(1200, 1200)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(image);
    }
}
