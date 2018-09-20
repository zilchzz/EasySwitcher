package com.zilch.easyswitchermaster

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.zilch.easyswitcherlib.widgets.EasySwitcher

class MainActivity : AppCompatActivity(), EasySwitcher.SwitchStateChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val easySwitcher = findViewById<EasySwitcher>(R.id.mEasySwitcher)
        val mGetStateBtn = findViewById<Button>(R.id.mGetStateBtn)
        mGetStateBtn.setOnClickListener {
            Toast.makeText(this, easySwitcher.isOpened().toString(), Toast.LENGTH_SHORT).show()
        }
        easySwitcher.setOnStateChangedListener(this)
    }

    override fun onStateChanged(isOpen: Boolean) {
        Toast.makeText(this, isOpen.toString(), Toast.LENGTH_SHORT).show()
    }
}
