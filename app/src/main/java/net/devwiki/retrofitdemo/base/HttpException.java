package net.devwiki.retrofitdemo.base;

import android.support.annotation.NonNull;

/**
 * 网络访问异常
 * Created by DevWiki on 2016/7/26.
 */

public class HttpException extends RuntimeException {

    public HttpException(String msg) {
        super(msg);
    }

    public HttpException(@NonNull HttpResult result) {
        super(result.getDesc() + ", code=" + result.getCode());
    }
}
