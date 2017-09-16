# Video player

[![Build Status](https://travis-ci.org/appunite/video-player-android.svg?branch=master)](https://travis-ci.org/appunite/video-player-android)
[![Release](https://jitpack.io/v/appunite/video-player-android.svg)](https://jitpack.io/#appunite/video-player-android)

Easy to use video player

# How to integrate with your project

Add library to project dependencies.

```groovy
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {

    // snapshot version
    compile 'com.github.appunite:video-player-android:master-SNAPSHOT'

    // or use specific version
    compile 'com.github.appunite:video-player-android:1.0.1'
}
```

Start video

```java
startActivity(PlayerActivity.getVideoPlayerIntent(context,
    "https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8",
    "Video title"));
```


# License

    Copyright [2015] [Jacek Marchwicki <jacek.marchwicki@gmail.com>]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    	http://www.apache.org/licenses/LICENSE-2.0


    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
