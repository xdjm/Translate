package com.xd.commander.baidutranslate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xd.commander.baidutrans.BaiduTrans;
import com.xd.commander.baidutrans.OnTransSuccess;
import com.xd.commander.baidutrans.YoudaoTrans;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText mFrom;
    private TextView mToBaidu;
    private TextView mToYoudao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void trans(View view) {
        if(!TextUtils.isEmpty(mFrom.getText().toString())) {
            YoudaoTrans.build().with(mFrom.getText().toString()).into(new OnTransSuccess() {
                @Override
                public void out(String s) {
                    mToYoudao.setText("YoudaoTrans:"+s);
                }
            });
            BaiduTrans.build().with(mFrom.getText().toString()).into(new OnTransSuccess() {
                @Override
                public void out(String s) {
                    mToBaidu.setText("BaiduTrans:"+s);
                }
            });
        }
        else {
            Toast.makeText(this, "请输入需要翻译的内容", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mFrom = findViewById(R.id.from);
        mToBaidu = findViewById(R.id.toBaidu);
        mToYoudao = findViewById(R.id.toYoudao);
    }
}

