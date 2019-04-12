package com.anwesh.uiprojects.rotthenfloorview

/**
 * Created by anweshmishra on 12/04/19.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val foreColor : Int = Color.parseColor("#673AB7")
val backColor : Int = Color.parseColor("#BDBDBD")
val angleDeg : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap
fun Float.updateTo(dx : Float, sc : Float) : Float = this + (dx - this) * sc

fun Canvas.drawRotThenFloorLine(i : Int, sc1 : Float, sc2 : Float, size : Float, paint : Paint) {
    val yGap : Float = (2 * size) / lines
    val y : Float = (yGap * i).updateTo(2 * size, sc2)
    save()
    translate(0f, -size + y + yGap)
    rotate(angleDeg * sc1 * -1)
    drawLine(0f, 0f, 0f, -yGap, paint)
    restore()
}

fun Canvas.drawRTFNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, lines)
    val sc2 : Float = scale.divideScale(1, lines)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND 
    save()
    translate(w / 2, gap * (i + 1))
    for (j in 0..(lines - 1)) {
        drawRotThenFloorLine(j, sc1, sc2, size, paint)
    }
    restore()
}
