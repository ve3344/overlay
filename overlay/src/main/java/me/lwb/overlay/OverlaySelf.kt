package me.lwb.overlay


fun <R> OverlayProperty<Func0<R>>.overlaySelf(overlayOut: Hand0<R>) {
    value = (value.overlay(overlayOut))
}

fun <P1, R> OverlayProperty<Func1<P1, R>>.overlaySelf(overlayOut: Hand1<P1, R>) {
    value = (value.overlay(overlayOut))
}

fun <P1, P2, R> OverlayProperty<Func2<P1, P2, R>>.overlaySelf(overlayOut: Hand2<P1, P2, R>) {
    value = (value.overlay(overlayOut))
}

fun <P1, P2, P3, R> OverlayProperty<Func3<P1, P2, P3, R>>.overlaySelf(overlayOut: Hand3<P1, P2, P3, R>) {
    value = (value.overlay(overlayOut))
}

fun <P1, P2, P3, P4, R> OverlayProperty<Func4<P1, P2, P3, P4, R>>.overlaySelf(overlayOut: Hand4<P1, P2, P3, P4, R>) {
    value = (value.overlay(overlayOut))
}

fun <P1, P2, P3, P4, P5, R> OverlayProperty<Func5<P1, P2, P3, P4, P5, R>>.overlaySelf(overlayOut: Hand5<P1, P2, P3, P4, P5, R>) {
    value = (value.overlay(overlayOut))
}

fun <P1, P2, P3, P4, P5, P6, R> OverlayProperty<Func6<P1, P2, P3, P4, P5, P6, R>>.overlaySelf(
    overlayOut: Hand6<P1, P2, P3, P4, P5, P6, R>
) {
    value = (value.overlay(overlayOut))
}

fun <P1, P2, P3, P4, P5, P6, P7, R> OverlayProperty<Func7<P1, P2, P3, P4, P5, P6, P7, R>>.overlaySelf(
    overlayOut: Hand7<P1, P2, P3, P4, P5, P6, P7, R>
) {
    value = (value.overlay(overlayOut))
}


