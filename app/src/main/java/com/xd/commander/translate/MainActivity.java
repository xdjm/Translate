/*
 * Copyright 2019 xdjm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xd.commander.translate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xd.commander.translatetool.BaiduTrans;
import com.xd.commander.translatetool.GoogleTrans;
import com.xd.commander.translatetool.OnTransSuccess;
import com.xd.commander.translatetool.YoudaoTrans;

public class MainActivity extends AppCompatActivity {

    private EditText mFrom;
    private TextView mToBaidu,mToYoudao,mToGoogle;

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
                    mToYoudao.setText("有道翻译："+s);
                }
            });
            GoogleTrans.build().with(mFrom.getText().toString()).into(new OnTransSuccess() {
                @Override
                public void out(String s) {
                    mToGoogle.setText("谷歌翻译："+s);
                }
            });
            BaiduTrans.build().with(mFrom.getText().toString()).into(new OnTransSuccess() {
                @Override
                public void out(String s) {
                    mToBaidu.setText("百度翻译："+s);
                }
            });
        }
        else {
            Toast.makeText(this, "请输入需要翻译的内容", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mFrom = findViewById(R.id.from);
        mToBaidu = findViewById(R.id.baiduresult);
        mToYoudao = findViewById(R.id.youdaoresult);
        mToGoogle = findViewById(R.id.googleresult);
    }
}

