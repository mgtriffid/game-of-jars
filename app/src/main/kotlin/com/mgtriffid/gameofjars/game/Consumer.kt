package com.mgtriffid.gameofjars.game

/**
 * Created by mgtriffid on 14.08.16.
 */
interface Consumer {
    fun consume(volume: Int)
    fun canConsume(): Int
}