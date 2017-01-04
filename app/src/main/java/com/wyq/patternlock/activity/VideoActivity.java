package com.wyq.patternlock.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;
import com.wyq.patternlock.R;
import com.wyq.patternlock.base.BaseActivity;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joe.Wang on 2017/1/4.
 */
public class VideoActivity extends BaseActivity {
    private String url;
    private String title;

    @BindView(R.id.videoView)
    UniversalVideoView videoView;
    @BindView(R.id.mediaController)
    UniversalMediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        initData();
        initView();
        setFullScreen();
    }

    private void initData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }

    private void initView() {
        mediaController.setTitle(title);
        videoView.setMediaController(mediaController);
        videoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            @Override
            public void onScaleChange(boolean isFullscreen) {}
            @Override
            public void onPause(MediaPlayer mediaPlayer) {}
            @Override
            public void onStart(MediaPlayer mediaPlayer) {}
            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {}
            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {}
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                VideoActivity.this.finish();
            }
        });
        videoView.setVideoPath(url);
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher,
        // but as a general rule, you should design your app to hide the status bar whenever you hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void setFullScreen() {
        try {
            Field mIsFullScreen = mediaController.getClass().getDeclaredField("mIsFullScreen");
            mIsFullScreen.setAccessible(true);
            mIsFullScreen.set(mediaController, true);

            Field mBackListener = mediaController.getClass().getDeclaredField("mBackListener");
            mBackListener.setAccessible(true);
            mBackListener.set(mediaController, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VideoActivity.this.finish();
                }
            });
        } catch (Exception e) {}
    }
}