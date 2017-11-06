package com.zyl_android.tenderinfo.project.application;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.zyl_android.tenderinfo.R;

import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getChildlayout());
        initStateBarTransparent();
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.tr_entry,R.anim.tr_void);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.tr_void,R.anim.tr_exit);
    }
    void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    public void initStateBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    protected abstract int getChildlayout();

    protected abstract void initView();

    protected abstract void loadData();
}
