package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private String videopath = "android.resource://com.example.videoplayer/"+R.raw.video1;
    private VideoView mVideoPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoPlay = (VideoView) findViewById(R.id.movieview);
        mVideoPlay.setVideoPath(videopath);
        mVideoPlay.setMediaController(new MediaController(this));
        mVideoPlay.requestFocus();
        mVideoPlay.start();
    }
}