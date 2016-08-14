package com.mgtriffid.gameofjars.game

/**
 * Created by mgtriffid on 14.08.16.
 */
interface Producer {
    fun produce(volume: Int)

    fun canProduce(): Int
}