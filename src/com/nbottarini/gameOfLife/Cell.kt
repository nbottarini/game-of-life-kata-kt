package com.nbottarini.gameOfLife

import com.nbottarini.gameOfLife.grid.Position

class Cell(val position: Position, isAlive: Boolean) {
    var isAlive = isAlive
        private set

    fun resurrect() {
        isAlive = true
    }

    fun die() {
        isAlive = false
    }
}