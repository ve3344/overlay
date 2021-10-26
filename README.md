# 一个快速实现类似拦截器效果的库

[![](https://jitpack.io/v/ve3344/overlay.svg)](https://jitpack.io/#ve3344/overlay)

一种高效极简的拦截方案，1行代码快速实现拦截器的效果。

- 可选覆写原函数/生成新函数

- 对参数/返回值进行拦截

- 快速覆写kotlin变量

- 可以多次有序覆写，类似多个拦截器效果

- 可以对任意覆写的操作进行撤销

- 支持自定义覆写变量

- 无反射

# 安装

#### 添加仓库

```groovy
//in build.gradle(Project)
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

// 新版方式 settings.gradle
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### 添加依赖

```groovy
//in build.gradle(module)
dependencies {
    implementation "com.github.ve3344.overlay:overlay:1.0.0"
}
```

# 示例

更详细说明见[Demo.kt](app/src/test/java/me/lwb/overlay_demo/Demo.kt)

#### 使用要求

覆写只能对函数变量使用，

已经定义了的函数由于不可变，因此只能生成新函数

#### 对已有函数覆写生成新函数

```kotlin

fun funOverlayImmutable() = "a"

@Test
fun demoOverlay() {
    //对funOverlay覆写生成新函数，修改其返回值使其返回b
    val newFun = ::funOverlayImmutable.overlay { "b" }
    newFun().assertIs("b")

    //对funOverlay覆写生成新函数，修改其返回值使再原来结果后拼接b
    val newFunCallSuperBefore = ::funOverlayImmutable.overlay { "${it()}b" }
    newFunCallSuperBefore().assertIs("ab")

    //对funOverlay覆写生成新函数，修改其返回值使再原来结果前拼接b,再拼接c
    val newFunCallSuperAfter = ::funOverlayImmutable.overlay { "b${it()}" }.overlay { "c${it()}" }
    newFunCallSuperAfter().assertIs("cba")

}
```

#### 对已有函数进行直接覆写

注意：覆写只能对函数变量使用

`fun funOverlay() = "a"`   ❌不可以使用

`val funOverlay = { "a" }`  ❌不可以使用

`var funOverlay = { "a" }`  ✅可以使用

```kotlin
//定义一个可覆写的函数变量
var funOverlay = { "a" }

@Test
fun demoOverlaySelf() {
    //直接覆写原函数overlaySelf,修改其返回值使再原来结果加上[]
    ::funOverlay.overlaySelf { "[${it()}]" }
    funOverlay().assertIs("[a]")
}
```

#### 撤销覆写

```kotlin
@Test
fun demoRevocableOverlaySelf() {
    //直接覆写原函数overlaySelf,修改其返回值使再原来结果加上[]
    val revocable = ::funOverlay.revocableOverlaySelf { "[${it()}]" }
    funOverlay().assertIs("[a]")

    revocable.revoke()//撤销
    funOverlay().assertIs("a")
}
```

#### 实现类似拦截器的效果，对请求参数、返回值处理

```kotlin
@Test
fun demoLikeInterceptor() {
    //---------------
    //给 url 加上https头
    ::fakeHttpRequest.overlaySelf { function, url, header, body ->
        function("https://$url", header, body)
    }
    //添加 header
    val dealHeaderRevocable =
        ::fakeHttpRequest.revocableOverlaySelf { function, url, header, body ->
            header["test-header"] = "1"
            function(url, header, body)
        }
    //处理 response
    ::fakeHttpRequest.overlaySelf { function, url, header, body ->
        function(url, header, body).replace("FAKE ", "")
    }

    //---------------

    //发生请求
    val response = fakeHttpRequest("baidu.com", mutableMapOf(), "body")
    response.assertIs("RESPONSE[https://baidu.com]:status=200,header={test-header=1},body=body")


    //撤销header 处理
    dealHeaderRevocable.revoke()

    //再发生请求
    val responseWithoutDealHeader = fakeHttpRequest("baidu.com", mutableMapOf(), "body")
    responseWithoutDealHeader.assertIs("RESPONSE[https://baidu.com]:status=200,header={},body=body")

}
```

#### 自定义Property

```kotlin
//使用SimpleProperty 或者实现OverlayProperty接口
var fakeHttpRequest1 =
    OverlayProperty.SimpleProperty { url: String, header: MutableMap<String, String>, body: String ->
        "FAKE RESPONSE[$url]:status=200,header=$header,body=$body"
    }

@Test
fun demoLikeInterceptorWithCustomProperty() {
    //给 url 加上https头
    //注意使用.而不是::
    fakeHttpRequest1.revocableOverlaySelf { function, url, header, body ->
        function("https://$url", header, body)
    }
    //请求前后添加日志
    fakeHttpRequest1.revocableOverlaySelf { function, url, header, body ->
        println("before call $url")
        function(url, header, body).also {
            println("after call $url ->$it")
        }
    }
    //发生请求
    val response = fakeHttpRequest1.value("baidu.com", mutableMapOf(), "body")
    response.assertIs("FAKE RESPONSE[https://baidu.com]:status=200,header={},body=body")
}

```

# 待完成

[-] suspend fun 支持

# 版本变化

v1.0.0 初始版本

# License

``` license
 Copyright 2021, luowenbin 
  
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