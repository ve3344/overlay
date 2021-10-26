package me.lwb.overlay

fun <R> (Func0<R>).overlay(overlay: Hand0<R>): Func0<R> {
    return { overlay(this) }
}


fun <P1, R> (Func1<P1, R>).overlay(overlay: Hand1<P1, R>): Func1<P1, R> {
    return { overlay(this, it) }
}


fun <P1, P2, R> (Func2<P1, P2, R>).overlay(overlay: Hand2<P1, P2, R>): Func2<P1, P2, R> {
    return { p1, p2 -> overlay(this, p1, p2) }
}

fun <P1, P2, P3, R> (Func3<P1, P2, P3, R>).overlay(overlay: Hand3<P1, P2, P3, R>): Func3<P1, P2, P3, R> {
    return { p1, p2, p3 -> overlay(this, p1, p2, p3) }
}


fun <P1, P2, P3, P4, R> (Func4<P1, P2, P3, P4, R>).overlay(overlay: Hand4<P1, P2, P3, P4, R>): Func4<P1, P2, P3, P4, R> {
    return { p1, p2, p3, p4 -> overlay(this, p1, p2, p3, p4) }
}


fun <P1, P2, P3, P4, P5, R> (Func5<P1, P2, P3, P4, P5, R>).overlay(overlay: Hand5<P1, P2, P3, P4, P5, R>): Func5<P1, P2, P3, P4, P5, R> {
    return { p1, p2, p3, p4, p5 -> overlay(this, p1, p2, p3, p4, p5) }
}


fun <P1, P2, P3, P4, P5, P6, R> (Func6<P1, P2, P3, P4, P5, P6, R>).overlay(overlay: Hand6<P1, P2, P3, P4, P5, P6, R>): Func6<P1, P2, P3, P4, P5, P6, R> {
    return { p1, p2, p3, p4, p5, p6 -> overlay(this, p1, p2, p3, p4, p5, p6) }
}

fun <P1, P2, P3, P4, P5, P6, P7, R> (Func7<P1, P2, P3, P4, P5, P6, P7, R>).overlay(overlay: Hand7<P1, P2, P3, P4, P5, P6, P7, R>): Func7<P1, P2, P3, P4, P5, P6, P7, R> {
    return { p1, p2, p3, p4, p5, p6, p7 -> overlay(this, p1, p2, p3, p4, p5, p6, p7) }
}



