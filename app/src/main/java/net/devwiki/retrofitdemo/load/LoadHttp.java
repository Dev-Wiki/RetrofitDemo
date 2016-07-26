package net.devwiki.retrofitdemo.load;

import net.devwiki.retrofitdemo.base.BaseResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by DevWiki on 2016/7/26.
 */

public interface LoadHttp {

    @GET("/fileUrl")
    Observable<BaseResult<String>> getFileUrl();
}
