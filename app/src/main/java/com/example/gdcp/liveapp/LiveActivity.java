package com.example.gdcp.liveapp;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity {
    private static final String TAG = "LiveActivity";
    private ImageView back;
 private ImageView stop;
 private TextView title;
 private TextView time;
 private MyVideoView videoView;
    private Live live;
    private RelativeLayout rlLoading;
    private int count=0;
    private RelativeLayout rootView;
    private LinearLayout topLayout;
    private LinearLayout bottomLayout;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(this);
        //设置视频解码监听
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        setContentView(R.layout.activity_live);
        initView();
    }

    private void initView() {
        back= (ImageView) findViewById(R.id.iv_back);
        stop= (ImageView) findViewById(R.id.iv_stop);
        title=(TextView) findViewById(R.id.tv_title);
        rlLoading= (RelativeLayout) findViewById(R.id.rl_loading);
        rootView= (RelativeLayout) findViewById(R.id.activity_live);
        topLayout= (LinearLayout) findViewById(R.id.ll_top);
        bottomLayout= (LinearLayout) findViewById(R.id.ll_bottom);
         live= (Live) getIntent().getSerializableExtra("live");
        Log.i(TAG, "initView: "+live.getUrl());
        title.setText(live.getTitle());
        time= (TextView) findViewById(R.id.tv_time);
        time.setText(getCurrentTime());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (videoView.isPlaying()){
                  videoView.pause();
                  stop.setImageResource(R.mipmap.play);
              }else {
                  videoView.start();
                  stop.setImageResource(R.mipmap.stop);
              }
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (topLayout.getVisibility()==View.VISIBLE||bottomLayout.getVisibility()==View.VISIBLE){
                     topLayout.setVisibility(View.GONE);
                     bottomLayout.setVisibility(View.GONE);
                     return;
                 }
                topLayout.setVisibility(View.VISIBLE);
                bottomLayout.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        topLayout.setVisibility(View.GONE);
                        bottomLayout.setVisibility(View.GONE);
                    }
                },5000);
            }
        });
       /* if (Vitamio.isInitialized(this)) {
            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"no",Toast.LENGTH_SHORT).show();
        }*/
        videoView= (MyVideoView) findViewById(R.id.videoView);
        //setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        videoView.setVideoURI(Uri.parse(live.getUrl()));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (count>5){
                   new AlertDialog.Builder(LiveActivity.this).setMessage("播放失败").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                               finish();
                       }
                   }).setCancelable(false).show();
                }else {
                    videoView.stopPlayback();
                    videoView.setVideoURI(Uri.parse(live.getUrl()));
                }
                count++;
                return false;
            }
        });

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        rlLoading.setVisibility(View.VISIBLE);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        rlLoading.setVisibility(View.GONE);
                        break;
                }
                return false;
            }
        });
    }

    private void setVideoViewScale(int width, int height) {
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        videoView.setLayoutParams(layoutParams);
    }

    private String getCurrentTime() {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
        return simpleDateFormat.format(calendar.getTime());
    }


    @Override
    protected void onStop() {
        super.onStop();
        count=0;
        if (videoView!=null){
            videoView.stopPlayback();
        }
    }
}
