package net.devwiki.retrofitdemo.duoshuo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DevWiki on 2016/3/19.
 */
public class CommitParam {

    private String secret;
    private String short_name;
    private String author_email;
    private String author_name;
    private String thread_key;
    private String author_url;
    private String message;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThread_key() {
        return thread_key;
    }

    public void setThread_key(String thread_key) {
        this.thread_key = thread_key;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommitParam{" +
                "secret='" + secret + '\'' +
                ", short_name='" + short_name + '\'' +
                ", author_email='" + author_email + '\'' +
                ", author_name='" + author_name + '\'' +
                ", thread_key='" + thread_key + '\'' +
                ", author_url='" + author_url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("secret", secret);
        params.put("short_name", short_name);
        params.put("author_email", author_email);
        params.put("author_name", author_name);
        params.put("thread_key", thread_key);
        params.put("author_url", author_url);
        params.put("message", message);
        return params;
    }
}
