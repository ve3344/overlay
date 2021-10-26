package me.lwb.overlay

interface OverlayProperty<V> {
    var value: V

    class SimpleProperty<V>(override var value: V) : OverlayProperty<V>
}