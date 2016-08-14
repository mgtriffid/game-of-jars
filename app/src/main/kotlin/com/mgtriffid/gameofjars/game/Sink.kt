package com.mgtriffid.gameofjars.game

/**
 * Created by mgtriffid on 14.08.16.
 */
object Sink : Consumer {
    override fun consume(volume: Int) {
        //nothing to do here
    }

    override fun canConsume() = Int.MAX_VALUE
}