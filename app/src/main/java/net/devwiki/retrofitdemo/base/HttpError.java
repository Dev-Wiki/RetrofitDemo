package net.devwiki.retrofitdemo.base;

/**
 * 错误码
 * Created by DevWiki on 2016/7/26.
 */

public interface HttpError {

    interface Code {
        int SUCCESS = 10001;
        int PARAM_INVALID = 10002;
    }

    interface Desc {
        String SUCCESS = "success!";
        String PARAM_INVALID = "param is invalid!";
    }
}
