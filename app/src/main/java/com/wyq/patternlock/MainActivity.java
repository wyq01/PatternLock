package com.wyq.patternlock;

import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.activity_main)
    View main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }

}