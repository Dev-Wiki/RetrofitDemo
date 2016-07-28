package net.devwiki.retrofitdemo.load;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.devwiki.log.DevLog;
import net.devwiki.retrofitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class DownloadActivity extends AppCompatActivity {

    @BindView(R.id.tv_file_url)
    TextView tvFileUrl;
    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.pb_download)
    ProgressBar pbDownload;
    @BindView(R.id.btn_load)
    Button btnLoad;

    private String filePath;

    private Subscriber<Boolean> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/devwiki.apk";
    }

    @OnClick({R.id.btn_get, R.id.btn_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getFileUrl();
                break;
            case R.id.btn_load:
                downloadFile();
                break;
        }
    }

    private void getFileUrl() {
        FileHttp.getInstance().getFileUrl(new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                tvFileUrl.setText(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                tvFileUrl.setText(s);
            }
        });
    }

    private void downloadFile() {
        if (!tvFileUrl.getText().toString().startsWith("http")) {
            Toast.makeText(this, "Url error!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "http://113.107.238.18/dd.myapp.com/16891/616ABA7D8A0635725567FB229BC922E5" +
                ".apk?mkey=579a20d96dc14f3f&f=8a5d&c=0&fsname=com.daimajia.gold_3.4.2_90.apk&p=.apk";
        DownloadListener listener = new DownloadListener() {
            @Override
            public void onProgress(long totalLength, long downloadLength, boolean isComplete) {
                int progress = (int) (downloadLength*100 /totalLength);
                DevLog.i("download:" + downloadLength);
                DevLog.i("total:" + totalLength);
                pbDownload.setProgress(progress);
                DevLog.i("已下载:" + progress);
                if (isComplete) {
                    Toast.makeText(DownloadActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                }
            }
        };
        subscriber = new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        };
        DownloadHttp downloadHttp = new DownloadHttp(listener, url);
        downloadHttp.downloadFile(subscriber, url, filePath);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
