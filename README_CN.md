# 翻译 [![](https://jitpack.io/v/xdjm/Translate.svg)](https://jitpack.io/#xdjm/Translate)

一个轻量级的翻译工具

包括百度翻译、有道翻译和谷歌翻译

作者：xdjm
## 怎么用
第一步， 在build.gradle里添加JitPack
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
第二步，在app/build.gradle里添加库地址
```
dependencies {
	        compile 'com.github.xdjm:Translate:1.2'
	}
```
第三步，添加网络权限
```
 <uses-permission android:name="android.permission.INTERNET" />
```

## 简单用法
```
 BaiduTrans.build().with("translate").into(new OnTransSuccess() {
                @Override
                public void out(String s) {
                    tv.setText(s);
                }
            });
 
 YoudaoTrans.build().with("translate").into(new OnTransSuccess() {
                @Override
                public void out(String s) {
                     tv.setText(s);
                }
            });
 GoogleTrans.build().with("translate").into(new OnTransSuccess() {
                  @Override
                  public void out(String s) {
                       tv.setText(s);
                  }
              });
```
## 复杂用法
```
BaiduTrans.build().with("android").from("en").to("zh").into(new OnTransSuccess() {
        @Override
                public void out(String s) {
                        tv.setText(s);
                }
});
```
- with("要翻译的内容")
- from("来源语言")
- to("译文语言")
- into("设置控件")

## 注意

如果appkey失效了，你可以去官方网站申请，然后这样设置
```
BaiduTrans.build().setAppId("新申请的AppId").with("android").from("en").to("zh").into(new OnTransSuccess() {
        @Override
                public void out(String s) {
                        tv.setText(s);
                }
});
```

YoudaoTrans也一样
## 官网

您可以前往官方网站查看支持的语言或重新申请Appkey。

当然，你也可以使用库中默认的appkey。

[百度翻译](http://api.fanyi.baidu.com/api/trans/product/apidoc)

[有道翻译](http://ai.youdao.com/docs/doc-trans-api.s)

然而，谷歌翻译不用理会这些，它一直有效。

或许给我一颗star比一杯咖啡更提神~ 谢谢
```
Copyright 2019 xdjm

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

