package net.devwiki.retrofitdemo.base;

import rx.functions.Func1;

/**
 * 网络访问的基础拦截器
 * Created by DevWiki on 2016/7/26.
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> result) {
        if (result.getCode() != HttpError.Code.SUCCESS) {
            throw new HttpException(result);
        }
        return result.getData();
    }
}
