package net.devwiki.retrofitdemo.load;

import net.devwiki.retrofitdemo.base.HttpResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 文件的API
 * Created by DevWiki on 2016/7/28.
 */

public interface FileApi {

    @GET("/fileUrl")
    Observable<HttpResult<String>> getFileUrl();
}
