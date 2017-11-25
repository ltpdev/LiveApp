package com.example.gdcp.liveapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private LiveListAdater liveListAdater;
    private List<Live>liveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        liveList=new ArrayList<>();
        liveListAdater=new LiveListAdater(MainActivity.this,liveList);
        liveList.add(new Live("http://183.251.61.207/PLTV/88888888/224/3221225900/index.m3u8","凤凰卫视"));
        liveList.add(new Live("http://182.128.24.189/wh7f454c46tw171664314_1087335995/PLTV/88888896/224/3221227951/10000100000000060000000003709052_0.smil?icpid=88888888&RTS=1502294046&from=68&hms_devid=24404","峨嵋电影"));
        liveList.add(new Live("rtmp://live.hkstv.hk.lxdns.com/live/hks","香港卫视"));
        liveList.add(new Live("http://117.144.248.49/HDhnws.m3u8?authCode=07110409322147352675&amp;stbId=006001FF0018120000060019F0D49A1&amp;Contentid=6837496099179515295&amp;mos=jbjhhzstsl&amp;livemode=1&amp;channel-id=wasusyt","湖南卫视"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-1/1.m3u8","CCTV-1"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-2/1.m3u8","CCTV-2"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-3/1.m3u8","CCTV-3"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-4/1.m3u8","CCTV-4"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-5/1.m3u8","CCTV-5"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-6/1.m3u8","CCTV-6"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-7/1.m3u8","CCTV-7"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-8/1.m3u8","CCTV-8"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-news/1.m3u8","CCTV-9"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-10/1.m3u8","CCTV-10"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-11/1.m3u8","CCTV-11"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-12/1.m3u8","CCTV-12"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-13/1.m3u8","CCTV-13"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-14/1.m3u8","CCTV-14"));
        liveList.add(new Live("http://223.82.250.72/ysten-business/live/cctv-15/1.m3u8","CCTV-15"));

       /* CCTV-3,http://223.82.250.72/ysten-business/live/cctv-3/1.m3u8
        CCTV-4,http://223.82.250.72/ysten-business/live/cctv-4/1.m3u8
        CCTV-5,http://223.82.250.72/ysten-business/live/cctv-5/1.m3u8
        CCTV-6,http://223.82.250.72/ysten-business/live/cctv-6/1.m3u8
        CCTV-7,http://223.82.250.72/ysten-business/live/cctv-7/1.m3u8
        CCTV-8,http://223.82.250.72/ysten-business/live/cctv-8/1.m3u8
        CCTV-9,http://223.82.250.72/ysten-business/live/cctv-news/1.m3u8
        CCTV-10,http://223.82.250.72/ysten-business/live/cctv-10/1.m3u8
        CCTV-11,http://223.82.250.72/ysten-business/live/cctv-11/1.m3u8
        CCTV-12,http://223.82.250.72/ysten-business/live/cctv-12/1.m3u8
        CCTV-13,http://223.82.250.72/ysten-business/live/cctv-13/1.m3u8
        CCTV-14,http://223.82.250.72/ysten-business/live/cctv-14/1.m3u8
        CCTV-15,http://223.82.250.72/ysten-business/live/cctv-15/1.m3u8*/
        listView.setAdapter(liveListAdater);
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.listview);
    }
}
