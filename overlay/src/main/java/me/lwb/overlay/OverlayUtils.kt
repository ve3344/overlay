package me.lwb.overlay

@Suppress("UNCHECKED_CAST")
object OverlayUtils {
    fun <T> overlaySequence(source: T): Sequence<IOverlayNode<T>> {
        return sequence {
            var node: Any? = source
            while (node is IOverlayNode<*>) {
                yield(node as IOverlayNode<T>)
                node = node.last
            }
        }
    }
}