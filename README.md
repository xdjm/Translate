# BaiduTranslate
# 百度翻译Sdk
## 用法
```BaiduTrans.build().with("android").from("en").to("zh").into(new BaiduTrans.OnSuccess2Trans() {
            @Override
            public void out(String s) {
                v.setText(s);
            }
        });
        ```
- with("要翻译的内容")
- from("来源语言")
- to("译文语言")
- into("TextView的实例")
