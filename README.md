# Translate [中文文档](https://github.com/xdjm/Translate/blob/master/README_CN.md) [![Release](https://jitpack.io/v/xdjm/Translate.svg)](https://jitpack.io/#xdjm/Translate) 
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Translate-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7575)
A lightweight translation tool.

Include BaiduTrans  YoudaoTrans  GoogleTrans

by xdjm
## How to
Step 1. Add the JitPack repository to your build file.
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.xdjm:Translate:1.2'
	}
```
Step 3. Add the internet permission
```
 <uses-permission android:name="android.permission.INTERNET" />
```

## Simple usage
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
## Complex usage
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

## Notice

If the appkey fails, you can go to the official website to apply,and like this.
```
BaiduTrans.build().setAppId("新申请的AppId").with("android").from("en").to("zh").into(new OnTransSuccess() {
        @Override
                public void out(String s) {
                        tv.setText(s);
                }
});
```

So do YoudaoTrans. 

You can go to the official website to see the supported languages or reapply for Appkey.

## Official website 

[BaiduTrans](http://api.fanyi.baidu.com/api/trans/product/apidoc)

[YoudaoTrans](http://ai.youdao.com/docs/doc-trans-api.s)

GoogleTrans ignored.

Maybe give me some stars~ Thx.
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
