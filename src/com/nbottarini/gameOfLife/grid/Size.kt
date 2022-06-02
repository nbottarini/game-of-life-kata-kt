package com.nbottarini.gameOfLife.grid

class Size(val width: Int, val height: Int) {
    override fun equals(other: Any?) = other is Size && other.width == width && other.height == height

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        return result
    }

    override fun toString() = "Size(width=$width, height=$height)"
}
