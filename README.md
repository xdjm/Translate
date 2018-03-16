# BaiduTranslate
# 百度翻译
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

```
Copyright 2018 xdjm

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
