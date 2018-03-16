# BaiduTranslate
By xdjm
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
	        compile 'com.github.xdjm:BaiduTranslate:1.0'
	}
```
Step 3. Add the internet permission
```
 <uses-permission android:name="android.permission.INTERNET" />
```

## simple usage
```
BaiduTrans.build().with("android").into(new BaiduTrans.OnSuccess2Trans() {
        @Override
                public void out(String s) {
                        v.setText(s);
                }
});
```
## complex usage
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

Maybe give me some stars~ Thx.
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
