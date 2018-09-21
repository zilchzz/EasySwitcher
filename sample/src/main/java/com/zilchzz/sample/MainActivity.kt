package com.zilchzz.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.zilchzz.library.widgets.EasySwitcher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val mGetStateBtn = findViewById<Button>(R.id.mGetStateBtn)
        val easySwitcher = findViewById<EasySwitcher>(R.id.mEasySwitcher)
        easySwitcher.setOnStateChangedListener(object : EasySwitcher.SwitchStateChangedListener {
            override fun onStateChanged(isOpen: Boolean) {
                Toast.makeText(this@MainActivity, isOpen.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        mGetStateBtn.setOnClickListener {
            Toast.makeText(this, easySwitcher.isOpened().toString(), Toast.LENGTH_SHORT).show()
        }

    }
}
