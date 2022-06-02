package com.nbottarini.gameOfLife

import com.nbottarini.gameOfLife.grid.Grid
import com.nbottarini.gameOfLife.grid.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UniverseTests {
    @Test
    fun `a universe created from a seed without live cells doesn't have any live cell`() {
        val seed = Grid(
            arrayOf(0, 0),
            arrayOf(0, 0),
        )

        val universe = Universe.fromSeed(seed)

        assertThat(universe.isAliveAt(Position(0, 0))).isFalse
        assertThat(universe.isAliveAt(Position(0, 1))).isFalse
        assertThat(universe.isAliveAt(Position(1, 0))).isFalse
        assertThat(universe.isAliveAt(Position(1, 1))).isFalse
    }

    @Test
    fun `a universe created from a seed with live cells have live cells in the corresponding positions`() {
        val seed = Grid(
            arrayOf(1, 0),
            arrayOf(0, 1),
        )

        val universe = Universe.fromSeed(seed)

        assertThat(universe.isAliveAt(Position(0, 0))).isTrue
        assertThat(universe.isAliveAt(Position(1, 1))).isTrue
    }

    @Test
    fun `a cell with no neighbours dies`() {
        val universe = Universe.fromSeed(Grid(
            arrayOf(0, 0, 0),
            arrayOf(0, 1, 0),
            arrayOf(0, 0, 0),
        ))

        universe.tick()

        assertThat(universe.isAliveAt(Position(1, 1))).isFalse
    }

    @Test
    fun `a cell with 1 neighbour dies`() {
        val universe = Universe.fromSeed(Grid(
            arrayOf(0, 0, 0),
            arrayOf(0, 1, 1),
            arrayOf(0, 0, 0),
        ))

        universe.tick()

        assertThat(universe.isAliveAt(Position(1, 1))).isFalse
    }

    @Test
    fun `a cell with 2 neighbours survives`() {
        val universe = Universe.fromSeed(Grid(
            arrayOf(1, 0, 0),
            arrayOf(0, 1, 0),
            arrayOf(1, 0, 0),
        ))

        universe.tick()

        assertThat(universe.isAliveAt(Position(1, 1))).isTrue
    }

    @Test
    fun `a dead cell with exactly 3 neighbours resurrects`() {
        val universe = Universe.fromSeed(Grid(
            arrayOf(0, 1, 0),
            arrayOf(1, 0, 1),
            arrayOf(0, 0, 0),
        ))

        universe.tick()

        assertThat(universe.isAliveAt(Position(1, 1))).isTrue
    }

    @Test
    fun `a cell with more than 3 neighbours dies`() {
        val universe = Universe.fromSeed(Grid(
            arrayOf(1, 0, 1),
            arrayOf(0, 1, 0),
            arrayOf(1, 0, 1),
        ))

        universe.tick()

        assertThat(universe.isAliveAt(Position(1, 1))).isFalse
    }
}