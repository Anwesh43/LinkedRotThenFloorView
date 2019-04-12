package com.anwesh.uiprojects.linkedrotthenfloorview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.rotthenfloorview.RotThenFloorView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RotThenFloorView.create(this)
    }
}
