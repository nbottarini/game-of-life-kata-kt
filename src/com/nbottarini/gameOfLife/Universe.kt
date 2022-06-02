package com.nbottarini.gameOfLife

import com.nbottarini.gameOfLife.grid.Grid
import com.nbottarini.gameOfLife.grid.Position

class Universe private constructor(private val grid: Grid<Cell>) {
    private val cells get() = grid.values

    fun isAliveAt(position: Position) = grid.contains(position) && grid.at(position).isAlive

    fun tick() {
        val cellsToKill = cells.filter { it.liveNeighbours() < 2 || it.liveNeighbours() >= 4  }
        val cellsToResurrect = cells.filter { it.liveNeighbours() == 3 }
        cellsToKill.forEach { it.die() }
        cellsToResurrect.forEach { it.resurrect() }
    }

    private fun Cell.liveNeighbours() = position.adjacents.count { isAliveAt(it) }

    companion object {
        fun fromSeed(seed: Grid<Int>): Universe {
            val gridOfCells = seed.map { position, value -> Cell(position, isAlive = value == 1) }
            return Universe(gridOfCells)
        }
    }
}
