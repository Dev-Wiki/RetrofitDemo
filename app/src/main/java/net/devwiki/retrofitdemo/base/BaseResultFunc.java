package net.devwiki.retrofitdemo.base;

import rx.functions.Func1;

/**
 * 网络访问的基础拦截器
 * Created by DevWiki on 2016/7/26.
 */

public class BaseResultFunc<T> implements Func1<BaseResult<T>, T> {
    @Override
    public T call(BaseResult<T> result) {
        if (result.getCode() != 10001) {
            throw new BaseHttpException(result.toString());
        }
        return result.getData();
    }
}
