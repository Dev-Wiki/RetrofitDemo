package net.devwiki.retrofitdemo.load;

import net.devwiki.retrofitdemo.base.BaseResultFunc;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DevWiki on 2016/7/26.
 */

public class LoadApi {

    private LoadHttp loadHttp;

    public static LoadApi getApi() {
        return ApiHolder.loadApi;
    }

    private LoadApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://retrofit.devwiki.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        loadHttp = retrofit.create(LoadHttp.class);
    }

    private static class ApiHolder {
        private static LoadApi loadApi = new LoadApi();
    }

    public void getFileUrl(Subscriber<String> subscriber) {
        loadHttp.getFileUrl()
                .map(new BaseResultFunc<String>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void downloadFile(String url) {

    }
}
