package net.devwiki.retrofitdemo.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by zyz on 2016/3/2.
 */
public interface PhoneService {

    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getPhoneREsult(@Header("apikey") String apikey, @Query("phone") String phone);
}
