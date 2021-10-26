package me.lwb.overlay_demo

import me.lwb.overlay.overlay
import me.lwb.overlay.overlaySelf
import me.lwb.overlay.revocableOverlaySelf
import org.junit.Assert
import org.junit.Test
import kotlin.system.measureNanoTime

internal inline fun <reified T> T?.assertIs(expected: T) {
    Assert.assertEquals(expected, this)
}

class OverlayExtKtTest {
    var a = { "a" }

    @Test
    fun testOverlay1() {
        a.overlay { "b" }().assertIs("b")
        a.overlay { "b" }.overlay { "c" }().assertIs("c")
    }

    @Test
    fun testOverlaySelf1() {
        a = { "a" }
        this::a.overlaySelf { "${it()}b" }
        a().assertIs("ab")
        this::a.overlaySelf { "${it()}c" }
        a().assertIs("abc")
        this::a.overlaySelf { "${it()}d" }
        a().assertIs("abcd")
        this::a.overlaySelf { "${it()}e" }
        a().assertIs("abcde")

    }

    @Test
    fun testRevocableOverlaySelf() {
        a = { "a" }
        repeat(4) {
            val revocableB = this::a.revocableOverlaySelf { "${it()}b" }
            a().assertIs("ab")
            val revocableC = this::a.revocableOverlaySelf { "${it()}c" }
            a().assertIs("abc")
            val revocableD = this::a.revocableOverlaySelf { "${it()}d" }
            a().assertIs("abcd")
            val revocableE = this::a.revocableOverlaySelf { "${it()}e" }
            a().assertIs("abcde")

            revocableE.revoke()
            a().assertIs("abcd")
            revocableD.revoke()
            a().assertIs("abc")
            val revocableD2 = this::a.revocableOverlaySelf { "${it()}d" }
            val revocableE2 = this::a.revocableOverlaySelf { "${it()}e" }


            revocableB.revoke()
            a().assertIs("acde")
            revocableE.revoke()
            a().assertIs("acd")
            revocableD.revoke()
            a().assertIs("ac")
            revocableC.revoke()
            a().assertIs("a")
        }
    }

    @Test
    fun testOverlayCost() {
        val TIMES = 10000000

        a = { "a" }

        val cost1 = measureNanoTime { repeat(TIMES) { a() } }
        println("Raw cost $cost1")

        a = { "a" }
        repeat(100) { ::a.overlaySelf { it() } }
        val cost2 = measureNanoTime { repeat(TIMES) { a() } }
        println("Overlay cost $cost2 ,${cost2 * 1.0 / cost1}")


        a = { "a" }
        repeat(100) { ::a.revocableOverlaySelf { it() } }
        val cost3 = measureNanoTime { repeat(TIMES) { a() } }
        println("Revocable overlay cost $cost3 ,${cost3 * 1.0 / cost1}")

    }

    @Test
    fun testOverlayCost2() {
        val TIMES = 10000000

        a = { "a".repeat(1000) }

        val cost1 = measureNanoTime { repeat(TIMES) { a() } }
        println("Raw cost $cost1")

        a = { "a".repeat(1000) }
        repeat(100) { ::a.overlaySelf { it() } }
        val cost2 = measureNanoTime { repeat(TIMES) { a() } }
        println("Overlay cost $cost2 ,${cost2 * 1.0 / cost1}")


        a = { "a".repeat(1000) }
        repeat(100) { ::a.revocableOverlaySelf { it() } }
        val cost3 = measureNanoTime { repeat(TIMES) { a() } }
        println("Revocable overlay cost $cost3 ,${cost3 * 1.0 / cost1}")

    }
}