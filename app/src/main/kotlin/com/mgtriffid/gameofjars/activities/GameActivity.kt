package com.mgtriffid.gameofjars.activities

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.TableLayout.LayoutParams
import android.widget.TextView
import android.widget.TextView.BufferType.SPANNABLE
import com.google.gson.Gson
import com.mgtriffid.gameofjars.R
import com.mgtriffid.gameofjars.game.*
import org.apache.commons.io.IOUtils.toString

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val levelNumber = intent.extras.getInt("levelNumber")
        val levelSettingsResources = resources.getIdentifier(
                "level$levelNumber", "raw", packageName)
        val levelSettingsString = toString(resources.openRawResource(levelSettingsResources), "UTF-8")
        val levelSettings = Gson().fromJson(levelSettingsString, LevelSettings::class.java)
        val bucketsRow = findViewById(R.id.buckets) as LinearLayout
        levelSettings.buckets.forEach {
            bucketsRow.addView(drawBucket(it))
        }
        createSupplyDnd()
        createSinkListener()
    }

    private fun createSinkListener() {
        val sink = findViewById(R.id.sink)
        sink.setOnDragListener(dragDropListener({ }, Sink))
    }

    private fun drawBucket(it: Bucket) : View {
        val bucketTextField = TextView(this)
        bucketTextField.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
        val callback = { bucketTextField.setText("${it.occupied} / ${it.capacity}", SPANNABLE) }
        bucketTextField.setOnDragListener(dragDropListener(callback, it))
        bucketTextField.setOnTouchListener(onTouchListener(it, callback))
        callback()
        return bucketTextField
    }

    private fun dragDropListener(dropCallback: () -> Unit, consumer: Consumer): (View, DragEvent) -> Boolean {
        return { view, dragEvent ->
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> true
                DragEvent.ACTION_DROP -> {
                    val dragFlow = dragEvent.localState as DragFlow
                    transfuse(dragFlow.source, consumer)
                    dragFlow.callback()
                    dropCallback()
                    true
                }
                else -> false
            }
        }
    }

    private fun onTouchListener(it: Producer, renderCallback: () -> Unit): (View, MotionEvent) -> Boolean = { view, motionEvent ->
        view.startDrag(
                ClipData("Drop", arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), ClipData.Item("")),
                object : View.DragShadowBuilder(view) {
                    private val shadow: Drawable = view.resources.getDrawable(R.drawable.drop)
                    override fun onDrawShadow(canvas: Canvas) = shadow.draw(canvas)
                    override fun onProvideShadowMetrics(outShadowSize: Point, outShadowTouchPoint: Point) {
                        outShadowSize.set(128, 128)
                        shadow.setBounds(0, 0, 128, 128)
                        outShadowTouchPoint.set(64, 64)
                    }
                },
                DragFlow(it, renderCallback),
                0
        )
    }

    private fun createSupplyDnd() {
        val supply = findViewById(R.id.supply)
        supply.setOnTouchListener(onTouchListener(Tap, {}))
    }

    private fun transfuse(source: Producer, target: Consumer) {
        val volume = Math.min(source.canProduce(), target.canConsume())
        source.produce(volume)
        target.consume(volume)
    }
}
