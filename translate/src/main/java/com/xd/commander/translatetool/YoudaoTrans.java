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

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class YoudaoTrans {

    private String salt = String.valueOf(System.currentTimeMillis());
    private String to = "zh-CHS";
    private String from = "EN";
    private String appId = "3cd79f923c8f5077";
    private String securityKey = "nL7dSKMo3lNyUOyAzeCwz9Za74RgkI0y";
    private String context = null;
    private static YoudaoTrans youdaoTrans = null;

    public static  YoudaoTrans build(){
        if(youdaoTrans == null) {
            synchronized (YoudaoTrans.class){
                if(youdaoTrans == null) {
                    youdaoTrans = new YoudaoTrans();
                }
            }
        }
        return youdaoTrans;
    }

    public YoudaoTrans setTo(String to) {
        this.to = to;
        return this;
    }

    public YoudaoTrans setFrom(String from) {
        this.from = from;
        return this;
    }

    public YoudaoTrans with(String context) {
        this.context = context;
        return this;
    }

    public YoudaoTrans setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public YoudaoTrans setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
        return this;
    }

    public void into( final OnTransSuccess onTransSuccess) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("q", context)
                .add("from", from)
                .add("to", to)
                .add("appKey", appId)
                .add("salt", salt)
                .add("sign", Md5.md5(appId + context + salt + securityKey))
                .build();
        String url = "http://openapi.youdao.com/api";
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, final Response response){
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            onTransSuccess.out(new Gson().fromJson(response.body().string(), Trans.class).getTranslation().get(0));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    class Trans {

        private String tSpeakUrl;
        private String query;
        private String errorCode;
        private DictBean dict;
        private WebdictBean webdict;
        private BasicBean basic;
        private String l;
        private String speakUrl;
        private List<WebBean> web;
        private List<String> translation;

        public String getTSpeakUrl() {
            return tSpeakUrl;
        }

        public void setTSpeakUrl(String tSpeakUrl) {
            this.tSpeakUrl = tSpeakUrl;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public DictBean getDict() {
            return dict;
        }

        public void setDict(DictBean dict) {
            this.dict = dict;
        }

        public WebdictBean getWebdict() {
            return webdict;
        }

        public void setWebdict(WebdictBean webdict) {
            this.webdict = webdict;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getL() {
            return l;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getSpeakUrl() {
            return speakUrl;
        }

        public void setSpeakUrl(String speakUrl) {
            this.speakUrl = speakUrl;
        }

        public List<WebBean> getWeb() {
            return web;
        }

        public void setWeb(List<WebBean> web) {
            this.web = web;
        }

        public List<String> getTranslation() {
            return translation;
        }

        public void setTranslation(List<String> translation) {
            this.translation = translation;
        }

        public  class DictBean {

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public  class WebdictBean {

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public  class BasicBean {
            @SerializedName("us-phonetic")
            private String usphonetic;
            private String phonetic;
            @SerializedName("uk-phonetic")
            private String ukphonetic;
            @SerializedName("uk-speech")
            private String ukspeech;
            @SerializedName("us-speech")
            private String usspeech;
            private List<String> explains;

            public String getUsphonetic() {
                return usphonetic;
            }

            public void setUsphonetic(String usphonetic) {
                this.usphonetic = usphonetic;
            }

            public String getPhonetic() {
                return phonetic;
            }

            public void setPhonetic(String phonetic) {
                this.phonetic = phonetic;
            }

            public String getUkphonetic() {
                return ukphonetic;
            }

            public void setUkphonetic(String ukphonetic) {
                this.ukphonetic = ukphonetic;
            }

            public String getUkspeech() {
                return ukspeech;
            }

            public void setUkspeech(String ukspeech) {
                this.ukspeech = ukspeech;
            }

            public String getUsspeech() {
                return usspeech;
            }

            public void setUsspeech(String usspeech) {
                this.usspeech = usspeech;
            }

            public List<String> getExplains() {
                return explains;
            }

            public void setExplains(List<String> explains) {
                this.explains = explains;
            }
        }

        public  class WebBean {

            private String key;
            private List<String> value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public List<String> getValue() {
                return value;
            }

            public void setValue(List<String> value) {
                this.value = value;
            }
        }
    }
}
