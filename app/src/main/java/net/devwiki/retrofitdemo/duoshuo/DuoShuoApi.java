package net.devwiki.retrofitdemo.duoshuo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zyz on 2016/3/19.
 */
public class DuoShuoApi {

    public static DuoShuoApi getApi(){
        return SingleHolder.duoShuoApi;
    }

    private static class SingleHolder{
        public static DuoShuoApi duoShuoApi = new DuoShuoApi();
    }

    private DuoShuoService service;

    private DuoShuoApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.duoshuo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(DuoShuoService.class);
    }

    public DuoShuoService getService(){
        return service;
    }
}
