package com.mgtriffid.gameofjars.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import android.widget.TableRow
import com.mgtriffid.gameofjars.R

class LevelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        val grid = LinearLayout(this)
        grid.orientation = VERTICAL
        grid.layoutParams = TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
        for (i in 0..4) {
            val row = LinearLayout(this)
            row.orientation = HORIZONTAL
            row.layoutParams = TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
            for (j in 1..5) {
                val levelButton = Button(this)
                levelButton.text = "${5 * i + j}"
                levelButton.layoutParams = TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
                row.addView(levelButton)
            }
            grid.addView(row)
        }
        (findViewById(R.id.view) as FrameLayout).addView(grid)
    }
}