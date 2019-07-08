package com.example.app_test.network.api;

import com.example.app_test.network.model.BaseResponse;
import com.example.app_test.network.model.Data;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//http://kot3.com/xim/api.php?query=cat

    @GET("xim/api.php?")
    Observable<BaseResponse<List<Data>>> getData(@Query("query") String query);
}
