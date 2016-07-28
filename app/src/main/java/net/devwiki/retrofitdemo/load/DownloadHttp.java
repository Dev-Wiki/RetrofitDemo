package net.devwiki.retrofitdemo.load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 下载的API单例
 * Created by DevWiki on 2016/7/26.
 */

public class DownloadHttp {

    private DownloadApi loadApi;

    public DownloadHttp(DownloadListener listener, String url) {
        DownloadInterceptor interceptor = new DownloadInterceptor(listener);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://7xjhi6.com1.z0.glb.clouddn.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        loadApi = retrofit.create(DownloadApi.class);
    }

    public void downloadFile(Subscriber<Boolean> subscriber, String fileUrl, final String filePath) {
        loadApi.downloadFile(fileUrl)
                .map(new Func1<ResponseBody, Boolean>() {
                    @Override
                    public Boolean call(ResponseBody responseBody) {
                        return saveToLocal(responseBody, filePath);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
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
}
