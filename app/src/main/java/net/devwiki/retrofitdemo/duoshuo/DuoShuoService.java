package net.devwiki.retrofitdemo.duoshuo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zyz on 2016/3/19.
 */
public interface DuoShuoService {

    //short_name=official&author_email=jp.chenyang%40gmail.com&author_name=Perchouli
    // &thread_id=1152923703638301959&author_url=http%3A%2F%2Fduoshuo.com&message=匿名发表新评论

    /**
     *
     * @param shortName
     * @param authorEmail
     * @param authorName
     * @param threadKey
     * @param author_url
     * @param message
     * @return
     */
    @FormUrlEncoded
    @POST("/posts/create.json")
    Call<CommitResult> createCommit(@Field("secret") String secret,
                                    @Field("short_name") String shortName,
                                    @Field("author_email") String authorEmail,
                                    @Field("author_name") String authorName,
                                    @Field("thread_key") String threadKey,
                                    @Field("author_url") String author_url,
                                    @Field("message") String message);

    @FormUrlEncoded
    @POST("/posts/create.json")
    Call<CommitResult> createCommit(@FieldMap Map<String, Object> map);
}
