@file:Suppress("UNCHECKED_CAST")

package me.lwb.overlay

abstract class IOverlayNode<T> {
    abstract var last: T
    open var next: (T)? = null
}

private inline fun <reified T> OverlayProperty<T>.makeRevocableOverlaySelf(createNode: (T) -> T): Revocable {
    val last = value
    val current = createNode(last).also { value = (it) }
    if (last is IOverlayNode<*>) {
        last as IOverlayNode<T>
        last.next = current
    }
    current as IOverlayNode<T>
    return OverlayRevokeImpl(this, current)
}


@Suppress("UNCHECKED_CAST")
internal class OverlayRevokeImpl<T>(
    private val property: OverlayProperty<T>,
    private val current: IOverlayNode<T>,
) : Revocable {
    override fun revoke() {
        val nextNode = current.next
        val lastNode = current.last

        if (nextNode is IOverlayNode<*>) {
            nextNode as IOverlayNode<T>
            nextNode.last = lastNode
        } else {
            //head
            property.value = (lastNode)
        }

        if (lastNode is IOverlayNode<*>) {
            lastNode as IOverlayNode<T>
            lastNode.next = current.next
        }//tail

    }
}


fun <R> OverlayProperty<Func0<R>>.revocableOverlaySelf(overlayOut: Hand0<R>): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func0<R>>(), Func0<R> {
            override fun invoke() = overlayOut(last)
            override var last: Func0<R> = it
        }
    }
}

fun <P1, R> OverlayProperty<Func1<P1, R>>.revocableOverlaySelf(overlayOut: Hand1<P1, R>): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func1<P1, R>>(), Func1<P1, R> {
            override fun invoke(p1: P1) = overlayOut(last, p1)
            override var last: Func1<P1, R> = it
        }
    }
}

fun <P1, P2, R> OverlayProperty<Func2<P1, P2, R>>.revocableOverlaySelf(overlayOut: Hand2<P1, P2, R>): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func2<P1, P2, R>>(), Func2<P1, P2, R> {
            override fun invoke(p1: P1, p2: P2) = overlayOut(last, p1, p2)
            override var last: Func2<P1, P2, R> = it
        }
    }
}

fun <P1, P2, P3, R> OverlayProperty<Func3<P1, P2, P3, R>>.revocableOverlaySelf(overlayOut: Hand3<P1, P2, P3, R>): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func3<P1, P2, P3, R>>(), Func3<P1, P2, P3, R> {
            override fun invoke(p1: P1, p2: P2, p3: P3) = overlayOut(last, p1, p2, p3)
            override var last: Func3<P1, P2, P3, R> = it
        }
    }
}


fun <P1, P2, P3, P4, R> OverlayProperty<Func4<P1, P2, P3, P4, R>>.revocableOverlaySelf(overlayOut: Hand4<P1, P2, P3, P4, R>): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func4<P1, P2, P3, P4, R>>(), Func4<P1, P2, P3, P4, R> {
            override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4) = overlayOut(last, p1, p2, p3, p4)
            override var last: Func4<P1, P2, P3, P4, R> = it
        }
    }
}

fun <P1, P2, P3, P4, P5, R> OverlayProperty<Func5<P1, P2, P3, P4, P5, R>>.revocableOverlaySelf(
    overlayOut: Hand5<P1, P2, P3, P4, P5, R>
): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func5<P1, P2, P3, P4, P5, R>>(), Func5<P1, P2, P3, P4, P5, R> {
            override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) =
                overlayOut(last, p1, p2, p3, p4, p5)

            override var last: Func5<P1, P2, P3, P4, P5, R> = it
        }
    }
}

fun <P1, P2, P3, P4, P5, P6, R> OverlayProperty<Func6<P1, P2, P3, P4, P5, P6, R>>.revocableOverlaySelf(
    overlayOut: Hand6<P1, P2, P3, P4, P5, P6, R>
): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func6<P1, P2, P3, P4, P5, P6, R>>(),
            Func6<P1, P2, P3, P4, P5, P6, R> {
            override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) =
                overlayOut(last, p1, p2, p3, p4, p5, p6)

            override var last: Func6<P1, P2, P3, P4, P5, P6, R> = it
        }
    }
}

fun <P1, P2, P3, P4, P5, P6, P7, R> OverlayProperty<Func7<P1, P2, P3, P4, P5, P6, P7, R>>.revocableOverlaySelf(
    overlayOut: Hand7<P1, P2, P3, P4, P5, P6, P7, R>
): Revocable {
    return makeRevocableOverlaySelf {
        object : IOverlayNode<Func7<P1, P2, P3, P4, P5, P6, P7, R>>(),
            Func7<P1, P2, P3, P4, P5, P6, P7, R> {
            override fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) =
                overlayOut(last, p1, p2, p3, p4, p5, p6, p7)

            override var last: Func7<P1, P2, P3, P4, P5, P6, P7, R> = it
        }
    }
}