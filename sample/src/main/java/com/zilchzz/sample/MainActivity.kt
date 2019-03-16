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
        val mTrunOnSwitcherBtn = findViewById<Button>(R.id.mTrunOnSwitcherBtn)
        val mTrunOffSwitcherBtn = findViewById<Button>(R.id.mTrunOffSwitcherBtn)

        val mChangeCloseColorBtn = findViewById<Button>(R.id.mChangeCloseColorBtn)
        val mChangeOpenColorBtn = findViewById<Button>(R.id.mChangeOpenColorBtn)

        val mResetOpenColorBtn = findViewById<Button>(R.id.mResetOpenColorBtn)
        val mResetCloseColorBtn = findViewById<Button>(R.id.mResetCloseColorBtn)

        val easySwitcher = findViewById<EasySwitcher>(R.id.mEasySwitcher)

        easySwitcher.setOnStateChangedListener(object : EasySwitcher.SwitchStateChangedListener {
            override fun onStateChanged(isOpen: Boolean) {
                Toast.makeText(this@MainActivity, isOpen.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        mGetStateBtn.setOnClickListener {
            Toast.makeText(this, easySwitcher.isOpened().toString(), Toast.LENGTH_SHORT).show()
        }
        mTrunOffSwitcherBtn.setOnClickListener {
            easySwitcher.closeSwitcher()
        }
        mTrunOnSwitcherBtn.setOnClickListener {
            easySwitcher.openSwitcher()
        }

        mChangeCloseColorBtn.setOnClickListener {
            easySwitcher.setSwitcherCloseColor("#FFF1F1F1")
        }

        mChangeOpenColorBtn.setOnClickListener {
            easySwitcher.setSwitcherOpenColor("#FF4CD964")
        }

        mResetOpenColorBtn.setOnClickListener {
            easySwitcher.setSwitcherOpenColor("#FF008CFF")
        }

        mResetCloseColorBtn.setOnClickListener {
            easySwitcher.setSwitcherCloseColor("#FFD9D9D9")
        }
    }
}
