package net.devwiki.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.devwiki.retrofitdemo.net.GsonConverterFactory;
import net.devwiki.retrofitdemo.net.PhoneResult;
import net.devwiki.retrofitdemo.net.PhoneService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";

    @Bind(R.id.phone_view)
    EditText phoneView;
    @Bind(R.id.result_view)
    TextView resultView;
    @Bind(R.id.query_view)
    Button queryView;
    @Bind(R.id.query_rxjava_view)
    Button queryRxjavaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private Observable<Response<PhoneResult>> getPhoneResult() {
        return Observable.create(new Observable.OnSubscribe<Response<PhoneResult>>() {
            @Override
            public void call(Subscriber<? super Response<PhoneResult>> subscriber) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                PhoneService service = retrofit.create(PhoneService.class);
                Call<PhoneResult> call = service.getResult(API_KEY, phoneView.getText().toString());
                try {
                    subscriber.onNext(call.execute());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void queryByRxJava() {
        resultView.setText("");
        if (phoneView.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        getPhoneResult().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<PhoneResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<PhoneResult> response) {
                        if (response.isSuccess()) {
                            PhoneResult result = response.body();
                            if (result != null && result.getErrNum() == 0) {
                                PhoneResult.RetDataEntity entity = result.getRetData();
                                resultView.append("地址：" + entity.getCity());
                            }
                        }
                    }
                });
    }

    private void query() {
        resultView.setText("");
        if (phoneView.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getResult(API_KEY, phoneView.getText().toString());
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccess()) {
                    PhoneResult result = response.body();
                    if (result != null && result.getErrNum() == 0) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        resultView.append("地址：" + entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.query_view, R.id.query_rxjava_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query_view:
                query();
                break;
            case R.id.query_rxjava_view:
                queryByRxJava();
                break;
        }
    }
}
