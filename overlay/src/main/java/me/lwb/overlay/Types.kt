package me.lwb.overlay

typealias Func0<R> = () -> R
typealias Hand0<R> = (Func0<R>) -> R
typealias Func1<P1, R> = (P1) -> R
typealias Hand1<P1, R> = (Func1<P1, R>, P1) -> R
typealias Func2<P1, P2, R> = (P1, P2) -> R
typealias Hand2<P1, P2, R> = (Func2<P1, P2, R>, P1, P2) -> R
typealias Func3<P1, P2, P3, R> = (P1, P2, P3) -> R
typealias Hand3<P1, P2, P3, R> = (Func3<P1, P2, P3, R>, P1, P2, P3) -> R
typealias Func4<P1, P2, P3, P4, R> = (P1, P2, P3, P4) -> R
typealias Hand4<P1, P2, P3, P4, R> = (Func4<P1, P2, P3, P4, R>, P1, P2, P3, P4) -> R
typealias Func5<P1, P2, P3, P4, P5, R> = (P1, P2, P3, P4, P5) -> R
typealias Hand5<P1, P2, P3, P4, P5, R> = (Func5<P1, P2, P3, P4, P5, R>, P1, P2, P3, P4, P5) -> R
typealias Func6<P1, P2, P3, P4, P5, P6, R> = (P1, P2, P3, P4, P5, P6) -> R
typealias Hand6<P1, P2, P3, P4, P5, P6, R> = (Func6<P1, P2, P3, P4, P5, P6, R>, P1, P2, P3, P4, P5, P6) -> R
typealias Func7<P1, P2, P3, P4, P5, P6, P7, R> = (P1, P2, P3, P4, P5, P6, P7) -> R
typealias Hand7<P1, P2, P3, P4, P5, P6, P7, R> = (Func7<P1, P2, P3, P4, P5, P6, P7, R>, P1, P2, P3, P4, P5, P6, P7) -> R
