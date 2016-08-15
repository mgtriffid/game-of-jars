package com.mgtriffid.gameofjars.game

/**
 * Created by mgtriffid on 12.08.16.
 */
class LevelSettings(
        val buckets: List<Bucket>,
        val winCondition: WinCondition,
        val winConditionParameter: Int,
        val threeStars: Int,
        val twoStars: Int
)

