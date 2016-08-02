package net.devwiki.retrofitdemo.load;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 下载HTTP
 * Created by DevWiki on 2016/7/26.
 */

public interface DownloadApi {

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
