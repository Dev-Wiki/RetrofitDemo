package net.devwiki.retrofitdemo.base;

import android.support.annotation.NonNull;

/**
 * 网络访问异常
 * Created by DevWiki on 2016/7/26.
 */

public class BaseHttpException extends RuntimeException {

    public BaseHttpException(String msg) {
        super(msg);
    }

    public BaseHttpException(@NonNull BaseResult result) {
        super(result.getDesc() + ", code=" + result.getCode());
    }
}
