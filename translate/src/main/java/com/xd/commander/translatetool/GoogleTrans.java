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

package com.xd.commander.translatetool;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Executors;

public class GoogleTrans {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    private String from = "en";
    private String to = "zh";
    private String context = null;
    private static GoogleTrans googleTrans = null;

    /**
     * Google trans.
     *
     * @return the google trans
     */
    public static  GoogleTrans build(){
        if(googleTrans == null) {
            synchronized (BaiduTrans.class){
                if(googleTrans == null) {
                    googleTrans = new GoogleTrans();
                }
            }
        }
        return googleTrans;
    }

    public GoogleTrans setFrom(String from) {
        this.from = from;
        return this;
    }

    public GoogleTrans setTo(String to) {
        this.to = to;
        return this;
    }

    public GoogleTrans with(String context) {
        this.context = context;
        return this;
    }


    /**
     * 翻译，包含http请求，需要异步，返回""则为翻译失败
     * @return ""为翻译失败，其余成功
     */
    public void into(final OnTransSuccess onTransSuccess) {
        TranslateTask task = new TranslateTask(onTransSuccess);
        task.executeOnExecutor(Executors.newSingleThreadExecutor());
    }

    /**
     * 使用异步任务来翻译，翻译完成后回调callback
     */
    @SuppressLint("StaticFieldLeak")
    class TranslateTask extends AsyncTask<Void, Integer, String> {
        OnTransSuccess onTransSuccess;

        TranslateTask(OnTransSuccess onTransSuccess) {
            this.onTransSuccess = onTransSuccess;
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder result = new StringBuilder();
            if (context == null || context.equals("")) {
                return result.toString();
            }
            try {
                String googleResult = "";
                URL url = new URL(getTranslateUrl(from, to, context));
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setConnectTimeout(5 * 1000);
                urlConn.setReadTimeout(5 * 1000);
                urlConn.setUseCaches(false);
                urlConn.setRequestMethod("GET");
                urlConn.setRequestProperty("User-Agent", USER_AGENT);
                urlConn.connect();
                int statusCode = urlConn.getResponseCode();
                if (statusCode == 200) {
                    googleResult = streamToString(urlConn.getInputStream());
                }
                urlConn.disconnect();
                JSONArray jsonArray = new JSONArray(googleResult).getJSONArray(0);
                for (int i = 0; i < jsonArray.length(); i++) {
                    result.append(jsonArray.getJSONArray(i).getString(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new StringBuilder();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if (onTransSuccess != null) {
                onTransSuccess.out(result);
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return 字符串
     */
    private static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            is.close();
            byte[] byteArray = out.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            return null;
        }
    }
    private static String getTranslateUrl(String sourceLan, String targetLan, String content) {
        String TRANSLATE_BASE_URL = "https://translate.google.cn/";
        try {
            return TRANSLATE_BASE_URL + "translate_a/single?client=gtx&sl=" + sourceLan + "&tl=" + targetLan + "&dt=t&q=" + URLEncoder.encode(content, "UTF-8");
        } catch (Exception e) {
            return TRANSLATE_BASE_URL + "translate_a/single?client=gtx&sl=" + sourceLan + "&tl=" + targetLan + "&dt=t&q=" + content;
        }
    }
}
