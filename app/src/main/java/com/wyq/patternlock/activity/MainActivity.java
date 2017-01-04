package com.wyq.patternlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.wyq.patternlock.R;
import com.wyq.patternlock.base.BaseActivity;
import com.wyq.patternlockview.MaterialLockView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private String correctPattern = "74269";

    @BindView(R.id.activity_main)
    View main;

    @BindView(R.id.pattern)
    MaterialLockView materialLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                if (!SimplePattern.equals(correctPattern)) {
                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Animate);
                        }
                    }, 1000);
                } else {
                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    intent.putExtra("title", "纸飞机");
                    intent.putExtra("url", "http://60.205.212.156:8080/static/p1.mp4");
                    startActivity(intent);
                }
                super.onPatternDetected(pattern, SimplePattern);
            }
        });
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setTransparent(this);
    }
}