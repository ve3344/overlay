package me.lwb.overlay

import kotlin.reflect.KMutableProperty0

internal fun <T> KMutableProperty0<T>.asOverlayProperty() = object : OverlayProperty<T> {
    override var value: T by this@asOverlayProperty
}

fun <R> KMutableProperty0<Func0<R>>.revocableOverlaySelf(overlayOut: Hand0<R>) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, R> KMutableProperty0<Func1<P1, R>>.revocableOverlaySelf(overlayOut: Hand1<P1, R>) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, P2, R> KMutableProperty0<Func2<P1, P2, R>>.revocableOverlaySelf(overlayOut: Hand2<P1, P2, R>) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, P2, P3, R> KMutableProperty0<Func3<P1, P2, P3, R>>.revocableOverlaySelf(overlayOut: Hand3<P1, P2, P3, R>) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, P2, P3, P4, R> KMutableProperty0<Func4<P1, P2, P3, P4, R>>.revocableOverlaySelf(overlayOut: Hand4<P1, P2, P3, P4, R>) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, P2, P3, P4, P5, R> KMutableProperty0<Func5<P1, P2, P3, P4, P5, R>>.revocableOverlaySelf(
    overlayOut: Hand5<P1, P2, P3, P4, P5, R>
) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, P2, P3, P4, P5, P6, R> KMutableProperty0<Func6<P1, P2, P3, P4, P5, P6, R>>.revocableOverlaySelf(
    overlayOut: Hand6<P1, P2, P3, P4, P5, P6, R>
) =
    asOverlayProperty().revocableOverlaySelf(overlayOut)


fun <P1, P2, P3, P4, P5, P6, P7, R> KMutableProperty0<Func7<P1, P2, P3, P4, P5, P6, P7, R>>.revocableOverlaySelf(
    overlayOut: Hand7<P1, P2, P3, P4, P5, P6, P7, R>
) = asOverlayProperty().revocableOverlaySelf(overlayOut)




