package net.devwiki.retrofitdemo.load;

/**
 * Created by Asia on 2016/7/27.
 */

public interface DownloadListener {

    void onProgress(long totalLength, long downloadLength, boolean isComplete);
}
