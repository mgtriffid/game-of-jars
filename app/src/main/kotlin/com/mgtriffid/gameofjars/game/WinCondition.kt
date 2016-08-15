package com.mgtriffid.gameofjars.game

enum class WinCondition {
    SPECIFIC_VOLUME_IN_BUCKET {
        override fun satisfied(buckets: List<Bucket>, parameter: Int) = buckets.any { it.occupied == parameter }
    };

    abstract fun satisfied(buckets: List<Bucket>, parameter: Int) : Boolean
}