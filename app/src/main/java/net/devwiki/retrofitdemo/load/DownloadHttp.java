package net.devwiki.retrofitdemo.load;

import net.devwiki.retrofitdemo.base.HttpResultFunc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 下载的API单例
 * Created by DevWiki on 2016/7/26.
 */

public class DownloadHttp {

    private DownloadApi getApi;
    private DownloadApi loadApi;

    public static DownloadHttp getHttp() {
        return DownloadHolder.downloadHttp;
    }

    private DownloadHttp() {
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
        getApi = retrofit.create(DownloadApi.class);
    }

    public void initLoadApi(DownloadListener listener) {
        DownloadInterceptor interceptor = new DownloadInterceptor(listener);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        loadApi = retrofit.create(DownloadApi.class);
    }

    private static class DownloadHolder {
        private static DownloadHttp downloadHttp = new DownloadHttp();
    }

    public void getFileUrl(Subscriber<String> subscriber) {
        getApi.getFileUrl()
                .map(new HttpResultFunc<String>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void downloadFile(Subscriber<String> subscriber, String fileUrl, String filePath) {
        Observable observable = loadApi.downloadFile(fileUrl)
                .map(new Func1<ResponseBody, Boolean>() {
                    @Override
                    public Boolean call(ResponseBody responseBody) {
                        return null;
                    }
                });
        toSubscriber(observable, subscriber);
    }

    private boolean saveToLocal(ResponseBody responseBody, String filePath) {
        InputStream inputStream = responseBody.byteStream();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filePath));
            byte[] bytes = new byte[4096];
            long fileLength = responseBody.contentLength();
            long downloadLength = 0;
            while (true) {
                int readLength = inputStream.read(bytes);
                if (readLength < 0) {
                    break;
                }
                outputStream.write(bytes, 0, readLength);
                downloadLength = downloadLength + readLength;
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void toSubscriber(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }
}
