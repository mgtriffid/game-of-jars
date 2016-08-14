package com.mgtriffid.gameofjars.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mgtriffid.gameofjars.R
import kotlin.jvm.javaClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openSecond(view: View) {
        startActivity(Intent(this, LevelsActivity::class.java))
    }
}
