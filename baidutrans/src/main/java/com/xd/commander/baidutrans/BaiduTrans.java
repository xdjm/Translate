package com.xd.commander.baidutrans;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Administrator 19:39 2018/2/20
 */

public class BaiduTrans {

    private static final String TAG = "BaiduTrans";

    private String salt = String.valueOf(System.currentTimeMillis());
    private String to = "zh";
    private String from = "en";
    private String appId = "20160424000019521";
    private String securityKey = "Hv8XkkCeQTn5xGBPDYgj";
    private String context = null;
    private static BaiduTrans baiduTrans = null;

    private BaiduTrans(){}
    public static  BaiduTrans build(){
        if(baiduTrans == null) {
            synchronized (BaiduTrans.class){
                if(baiduTrans == null) {
                    baiduTrans = new BaiduTrans();
                }
            }
        }
        return baiduTrans;
    }

    public BaiduTrans setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public BaiduTrans setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
        return this;
    }

    public BaiduTrans to(String to) {
        this.to = to;
        return this;
    }
    public BaiduTrans from(String from) {
        this.from = from;
        return this;
    }

    public BaiduTrans with(String string) {
        this.context = string;
        return this;
    }

   public void into( final OnTransSuccess onTransSuccess) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("q", context)
                .add("from", from)
                .add("to", to)
                .add("appid", appId)
                .add("salt", salt)
                .add("sign", Md5.md5(appId + context + salt + securityKey))
                .build();
       String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";
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
                            onTransSuccess.out(new Gson().fromJson(response.body().string(), Trans.class).getTrans_result().get(0).getDst());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    class Trans {

        /**
         * from : en
         * to : zh
         * trans_result : [{"src":"fuck you","dst":"你他妈的"}]
         */

        private String from;
        private String to;
        private List<TransResultBean> trans_result;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }
        public List<TransResultBean> getTrans_result() {
            return trans_result;
        }
        public  class TransResultBean {

            private String src;
            private String dst;

            public String getDst() {
                return dst;
            }

        }
    }
}
