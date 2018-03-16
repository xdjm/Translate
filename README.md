# BaiduTranslate
# 百度翻译Sdk
## 简单用法
```
BaiduTrans.build().with("android").into(new BaiduTrans.OnSuccess2Trans() {
        @Override
                public void out(String s) {
                        v.setText(s);
                }
});
```
## 复杂用法
```
BaiduTrans.build().with("android").from("en").to("zh").into(new BaiduTrans.OnSuccess2Trans() {
        @Override
                public void out(String s) {
                        v.setText(s);
                }
});
```
- with("要翻译的内容")
- from("来源语言")
- to("译文语言")
- into("设置控件")
