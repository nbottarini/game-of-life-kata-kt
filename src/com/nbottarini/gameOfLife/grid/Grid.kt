package com.nbottarini.gameOfLife.grid

class Grid<T>(private vararg val data: Array<T>) {
    val size = calculateSize()
    val positions = createPositions()
    val values get() = positions.map { at(it) }

    init {
        failIfNotRectangular()
    }

    private fun calculateSize(): Size {
        if (data.isEmpty()) return Size(0, 0)
        return Size(data[0].size, data.size)
    }

    private fun createPositions(): List<Position> {
        return data.flatMapIndexed { row, rowData ->
            rowData.mapIndexed { column, _ -> Position(row, column) }
        }
    }

    private fun failIfNotRectangular() {
        if (data.any { it.size != size.width }) throw InvalidGridError()
    }

    fun at(position: Position) = data[position.row][position.column]

    fun contains(position: Position) =
        position.row in 0 until size.height && position.column in 0 until size.width

    inline fun <reified R> map(transform: (Position, T) -> R): Grid<R> {
        val data = Array(size.height) { row ->
            Array(size.width) { column ->
                val position = Position(row, column)
                transform(position, at(position))
            }
        }
        return Grid(*data)
    }
}
