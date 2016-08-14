package com.mgtriffid.gameofjars.game

/**
 * Created by mgtriffid on 14.08.16.
 */
object Tap : Producer {
    override fun produce(volume: Int) {
        //nothing to do here
    }

    override fun canProduce() = Int.MAX_VALUE
}