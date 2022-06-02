package com.nbottarini.gameOfLife.grid

class Position(val row: Int, val column: Int) {
    val adjacents: List<Position> by lazy {
        val adjacentRows = (row - 1)..(row + 1)
        val adjacentColumns = (column - 1)..(column + 1)
        val neighbours = adjacentRows.flatMap { row -> adjacentColumns.map { column -> Position(row, column) } }
        neighbours.filter { it != this }
    }

    override fun equals(other: Any?) = other is Position && other.row == row && other.column == column

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + column
        return result
    }

    override fun toString() = "Position($row, $column)"
}
