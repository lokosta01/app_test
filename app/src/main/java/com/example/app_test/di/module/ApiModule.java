package com.example.app_test.di.module;

import com.example.app_test.BuildConfig;
import com.example.app_test.network.api.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final Long TIMEOUT_SEC = 30L;
    private static final String BASE_URL = "http://kot3.com/";

    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG){
            final HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggin);
        }

        builder.connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    final Request original = chain.request();

                    final Request.Builder request = original.newBuilder()
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());

                    return chain.proceed(request.build());
                        });
        return builder.build();

    }

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(final OkHttpClient okHttpClient, final Gson gson){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(final Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
