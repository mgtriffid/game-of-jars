package com.mgtriffid.gameofjars.activities

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.*
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import com.google.gson.Gson
import com.mgtriffid.gameofjars.R
import com.mgtriffid.gameofjars.game.SavedState
import org.apache.commons.io.IOUtils
import java.io.FileNotFoundException
import java.util.*

class LevelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        val savedState = try {
            val savedStateString = IOUtils.toString(openFileInput("SavedState"), "UTF-8")
            Gson().fromJson(savedStateString, SavedState::class.java)
        } catch (fnfe: FileNotFoundException) {
            val newState = SavedState(ArrayList(Array(25, { 0 }).asList()))
            IOUtils.write(Gson().toJson(newState), openFileOutput("SavedState", Context.MODE_PRIVATE), "UTF-8")
            newState
        }
        val grid = LinearLayout(this)
        grid.orientation = VERTICAL
        grid.layoutParams = TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
        for (i in 0..4) {
            val row = LinearLayout(this)
            row.orientation = HORIZONTAL
            row.layoutParams = TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
            for (j in 1..5) {
                val bm = BitmapFactory.decodeResource(resources, R.drawable.bounds).copy(Bitmap.Config.ARGB_8888, true)
                val paint = Paint()
                paint.style = Paint.Style.FILL_AND_STROKE
                paint.color = Color.BLUE
//                val levelButton = Button(this)
                paint.textSize = 100f
                val levelNumber = 5 * i + j
                Canvas(bm).drawText("$levelNumber", 200f, 200f, paint)
                val levelImage = ImageView(this)
                levelImage.setImageDrawable(BitmapDrawable(resources, bm))
                Log.v("LevelsActivity", "level $levelNumber, stars ${savedState.levelsCompletion[levelNumber - 1]}")
//                levelButton.text = "$levelNumber" + Array(savedState.levelsCompletion[levelNumber - 1], {"*"}).joinToString("")
                levelImage.layoutParams = TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
                levelImage.setOnClickListener {
                    val intent = Intent(this, GameActivity::class.java)
                    intent.putExtra("levelNumber", levelNumber)
                    startActivity(intent)
                }
                row.addView(levelImage)
            }
            grid.addView(row)
        }
        (findViewById(R.id.view) as FrameLayout).addView(grid)
    }
}
