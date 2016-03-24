package net.devwiki.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.devwiki.retrofitdemo.duoshuo.DuoShuoActivity;
import net.devwiki.retrofitdemo.phone.PhoneApi;
import net.devwiki.retrofitdemo.phone.PhoneResult;
import net.devwiki.retrofitdemo.phone.PhoneService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.phone_view)
    EditText phoneView;
    @Bind(R.id.result_view)
    TextView resultView;

    private PhoneApi phoneApi;
    private PhoneService phoneService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        phoneApi = PhoneApi.getApi();
        phoneService = phoneApi.getService();
    }

    @OnClick({R.id.query_view, R.id.query_rxjava_view, R.id.duo_shuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query_view:
                query();
                break;
            case R.id.query_rxjava_view:
                queryByRxJava();
                break;
            case R.id.duo_shuo:
                startActivity(new Intent(MainActivity.this, DuoShuoActivity.class));
        }
    }

    private void queryByRxJava() {
        resultView.setText("");
        String number = phoneView.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        phoneService.getPhoneResult(PhoneApi.API_KEY, number)
                .subscribeOn(Schedulers.newThread())    //子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())  //回调到主线程
                .subscribe(new Observer<PhoneResult>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(PhoneResult result) {
                        if (result != null && result.getErrNum() == 0) {
                            PhoneResult.RetDataEntity entity = result.getRetData();
                            resultView.append("地址：" + entity.getCity());
                        }
                    }
                });
    }

    private void query() {
        resultView.setText("");
        String number = phoneView.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<PhoneResult> call = phoneService.getResult(PhoneApi.API_KEY, number);
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()) {
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
}
