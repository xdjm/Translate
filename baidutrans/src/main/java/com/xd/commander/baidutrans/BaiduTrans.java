package com.xd.commander.baidutrans;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import java.io.IOException;
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
    private String to = "zh";
    private String from = "en";
    private String context = null;
    private String appId = "20160424000019521";
    private String securityKey = "Hv8XkkCeQTn5xGBPDYgj";
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

    public BaiduTrans setAppId(String APP_ID) {
        this.appId = APP_ID;
        return this;
    }

    public BaiduTrans setSecurityKey(String SECURITY_KEY) {
        this.securityKey = SECURITY_KEY;
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

   public void into(final OnSuccess2Trans onSuccess2Trans) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("q", context)
                .add("from", from)
                .add("to", to)
                .add("appid", appId)
                .add("salt", "1435660288")
                .add("sign", Md5.md5(appId + context + 1435660288 + securityKey))
                .build();
        Request request = new Request.Builder()
                .url("http://api.fanyi.baidu.com/api/trans/vip/translate")
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
                            onSuccess2Trans.out(new Gson().fromJson(response.body().string(), Trans.class).getTrans_result().get(0).getDst());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
     public interface OnSuccess2Trans{
        void out(String s);
    }
}
