package net.devwiki.retrofitdemo.load;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.devwiki.log.DevLog;
import net.devwiki.retrofitdemo.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class DownloadActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 0x001;

    private static final String BASE_URL = "http://7xjhi6.com1.z0.glb.clouddn.com/";

    @BindView(R.id.tv_file_url)
    TextView tvFileUrl;
    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.pb_download)
    ProgressBar pbDownload;
    @BindView(R.id.btn_load)
    Button btnLoad;
    @BindView(R.id.play_btn)
    Button playBtn;
    @BindView(R.id.stop_btn)
    Button stopBtn;

    private String filePath;

    private Subscriber<Boolean> subscriber;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/fade.mp3";

        checkPermission();
    }

    private void checkPermission() {
        if (!isHaveStorePermission()) {
            String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permission, REQUEST_PERMISSION_CODE);
        }
    }

    private boolean isHaveStorePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @OnClick({R.id.btn_get, R.id.btn_load, R.id.play_btn, R.id.stop_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getFileUrl();
                break;
            case R.id.btn_load:
                downloadFile();
                break;
            case R.id.play_btn:
                playMusic();
                break;
            case R.id.stop_btn:
                stopMusic();
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
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                DevLog.e(e.getMessage());
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    DevLog.i("下载成功！！！");
                }
            }
        };
        DownloadHttp downloadHttp = new DownloadHttp(listener, BASE_URL);
        downloadHttp.downloadFile(subscriber, tvFileUrl.getText().toString(), filePath);
    }

    private void playMusic() {
        if (player == null) {
            player = new MediaPlayer();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            DevLog.w("文件不存在");
            return;
        }
        if (player.isPlaying()) {
            player.stop();
        }
        try {
            player.reset();
            player.setDataSource(new FileInputStream(file).getFD());
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                }
            });
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopMusic();
                }
            });
        } catch (IOException e) {
            DevLog.e(e.getMessage());
            e.printStackTrace();
        }
    }

    private void stopMusic() {
        if (player != null) {
            player.stop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                DevLog.i("获取存储权限成功！！！");
            } else {
                Toast.makeText(DownloadActivity.this, "请授予存储权限！", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null && !player.isPlaying()) {
            player.release();
        }
    }
}
