# EasySwitcher

[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()

[![API](https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11)

[![](https://jitpack.io/v/xyz5359502/EasySwitcherMaster.svg)](https://jitpack.io/#xyz5359502/EasySwitcherMaster)

Switcher library for Android platform ,write in Kotlin. It's easy to use, with background gradient, much like the switcher on iOS, 
contains only one class.

![image](https://github.com/xyz5359502/EasySwitcher/blob/master/sample/src/main/res/mipmap-xxhdpi/git_effect.gif)

## Requirements

- Android SDK 11+

## Usage

Add it in your root build.gradle at the end of repositories

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency

```groovy
dependencies {
    implementation 'com.github.xyz5359502:EasySwitcherMaster:0.1.0'
}
```

## HOW TO USE

Add `EasySwitcher` to your xml layout

```xml
<com.zilch.easyswitcherlib.widgets.EasySwitcher
        android:id="@+id/mEasySwitcher"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:sbAnimSpeed="300"
        app:sbCloseBgColor="#d9d9d9"
        app:sbOpenBgColor="#008cff"
        app:sbSwitchStatus="close"
        app:sbSwitcherColor="@android:color/white" />
```

## Available attributes

|      Name       |  Type   |             Description              |
| :-------------: | :-----: | :----------------------------------: |
|  sbOpenBgColor  |  color  |     round rect color when opend      |
| sbCloseBgColor  |  color  |     round rect color when closed     |
|   sbAnimSpeed   | integer |          anim running time           |
| sbSwitchStatus  |  enum   | switcher default state,open or close |
| sbSwitcherColor |  color  |        the color of switcher         |

## Status changed callback

EasySwitcher has two state (open and close)

If you has settle the SwitchStateChangedListener, onStateChanged method called when the switcher state cheanged.

Kotlin Code Example

```Kotlin
// Set State Change Listener
val mEasySwitcher = findViewById<EasySwitcher>(R.id.mEasySwitcher)
mEasySwitcher.setOnStateChangedListener(object : EasySwitcher.SwitchStateChangedListener {
	override fun onStateChanged(isOpen: Boolean) {
		Log.i("MainActivity", isOpen.toString())       
	}
})
```

## Direction

### Methods

```kotlin
/**
  * get the current state of switcher
  * @return whether this switcher is open or not
  */
fun isOpened() = mSwitcherOpened

/**
 * Register a callback to be invoked when the switcher state changed.
 * @param stateChangedLis the callback that will run.
 */
fun setOnStateChangedListener(stateChangedLis: SwitchStateChangedListener) {
	this.mStateChangedLis = stateChangedLis
}
```



# License

```
MIT License

Copyright (c) 2018 xyz5359502

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
