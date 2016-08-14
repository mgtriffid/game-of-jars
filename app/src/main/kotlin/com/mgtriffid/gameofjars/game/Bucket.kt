package com.mgtriffid.gameofjars.game

/**
 * Created by mgtriffid on 12.08.16.
 */
data class Bucket(val capacity: Int, var occupied: Int = 0) : Producer, Consumer {
    override fun produce(volume: Int) {
        occupied -= volume
    }

    override fun consume(volume: Int) {
        occupied += volume
    }

    override fun canProduce() = occupied

    override fun canConsume() = capacity - occupied
}