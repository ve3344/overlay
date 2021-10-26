package me.lwb.overlay_demo

import me.lwb.overlay.OverlayProperty
import me.lwb.overlay.overlay
import me.lwb.overlay.overlaySelf
import me.lwb.overlay.revocableOverlaySelf
import org.junit.Test

class Demo {
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
        val newFunCallSuperAfter =
            ::funOverlayImmutable.overlay { "b${it()}" }.overlay { "c${it()}" }
        newFunCallSuperAfter().assertIs("cba")

    }

    var funOverlay = { "a" }

    @Test
    fun demoOverlaySelf() {
        //直接覆写原函数overlaySelf,修改其返回值使再原来结果加上[]
        ::funOverlay.overlaySelf { "[${it()}]" }
        funOverlay().assertIs("[a]")
    }

    @Test
    fun demoRevocableOverlaySelf() {
        //直接覆写原函数overlaySelf,修改其返回值使再原来结果加上[]
        val revocable = ::funOverlay.revocableOverlaySelf { "[${it()}]" }
        funOverlay().assertIs("[a]")

        revocable.revoke()//撤销

        funOverlay().assertIs("a")
    }


    var fakeHttpRequest = { url: String, header: MutableMap<String, String>, body: String ->
        "FAKE RESPONSE[$url]:status=200,header=$header,body=$body"
    }

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
}