package com.example.gdcp.liveapp;

import java.io.Serializable;

/**
 * Created by asus- on 2017/11/25.
 */

public class Live implements Serializable{
    private String url;
    private String title;

    public Live(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
