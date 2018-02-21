package com.xd.commander.baidutranslate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xd.commander.baidutrans.BaiduTrans;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView v =findViewById(R.id.tv);
        BaiduTrans.build().with("android").from("en").to("zh").into(v);
    }
}
