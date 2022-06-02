package com.nbottarini.gameOfLife

import com.nbottarini.gameOfLife.grid.Grid
import com.nbottarini.gameOfLife.grid.InvalidGridError
import com.nbottarini.gameOfLife.grid.Position
import com.nbottarini.gameOfLife.grid.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GridTests {
    @Test
    fun `creation fails if it's not rectangular`() {
        assertThrows<InvalidGridError> {
            Grid(
                arrayOf(1, 0),
                arrayOf(0, 1, 0),
                arrayOf(0, 1),
            )
        }
    }

    @ParameterizedTest
    @CsvSource(
        "0, 0, 1",
        "0, 1, 0",
        "1, 0, 0",
        "1, 1, 1",
    )
    fun `at returns value at given position`(row: Int, column: Int, expectedValue: Int) {
        val grid = Grid(
            arrayOf(1, 0),
            arrayOf(0, 1),
        )

        assertThat(grid.at(Position(row, column))).isEqualTo(expectedValue)
    }

    @Test
    fun `positions return positions`() {
        val grid = Grid(
            arrayOf(1, 0),
            arrayOf(0, 1),
        )

        assertThat(grid.positions).containsExactly(
            Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)
        )
    }

    @Test
    fun `size returns grid size`() {
        val grid = Grid(
            arrayOf(1, 0),
            arrayOf(0, 1),
            arrayOf(0, 1),
        )

        assertThat(grid.size).isEqualTo(Size(2, 3))
    }

    @Test
    fun `empty grid has 0 size`() {
        val grid = Grid<Int>()

        assertThat(grid.size).isEqualTo(Size(0, 0))
    }

    @Test
    fun `map transforms each grid value with the given transform function`() {
        val grid = Grid(
            arrayOf(1, 0),
            arrayOf(0, 1),
        )

        val mappedGrid = grid.map { position, value -> value.toString() }

        assertThat(mappedGrid.size).isEqualTo(grid.size)
        assertThat(mappedGrid.positions).isEqualTo(grid.positions)
        assertThat(mappedGrid.at(Position(0, 0))).isEqualTo("1")
        assertThat(mappedGrid.at(Position(0, 1))).isEqualTo("0")
        assertThat(mappedGrid.at(Position(1, 0))).isEqualTo("0")
        assertThat(mappedGrid.at(Position(1, 1))).isEqualTo("1")
    }
}
