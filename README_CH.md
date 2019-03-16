[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
[![API](https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11)
[![](https://jitpack.io/v/zilchzz/EasySwitcher.svg)](https://jitpack.io/#zilchzz/EasySwitcher)

# EasySwitcher
一个模仿 iOS 系统中的开关控件，在动画中带有背景颜色渐变。使用 Kotlin 语言编写，轻量易集成，只含有一个类及一个 attrs.xml 文件。

# 文档
- [ENGLISH](https://github.com/zilchzz/EasySwitcher/blob/master/README.md) 
- [中文](https://github.com/zilchzz/EasySwitcher/blob/master/README_CH.md)

# 效果
![image](https://github.com/zilchzz/EasySwitcher/blob/master/sample/src/main/res/mipmap-xxhdpi/switcher.gif)

## 要求

- Android SDK 11+

## 集成方式

将 jitpack 添加到你的顶级 gradle 文件中：

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

在你的项目 gradle 文件中引入库：

```groovy
dependencies {
    implementation 'com.github.zilchzz:EasySwitcher:0.1.5'
}
```

## 如何使用？

将 `EasySwitcher` 添加到你的布局 xml 文件中：

```xml
<com.zilchzz.library.widgets.EasySwitcher
        android:id="@+id/mEasySwitcher"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:sbAnimTime="300"
        app:sbCloseBgColor="#d9d9d9"
        app:sbOpenBgColor="#008cff"
        app:sbDefaultOpened="false"
        app:sbToggleColor="@android:color/white" />
```
>**如果你希望统一的将默认属性更改掉，你可以在 Application 的 onCreate 方法中调用 EasySwitcher.setDefault...() 系列方法。当然，在 XML 中设定的属性总是优先的。**

## 可用属性

|      名字        |  类型   |             Description               |
| :-------------: | :-----: | :----------------------------------: |
|  sbOpenBgColor  |  color  |     round rect color when opend      |
| sbCloseBgColor  |  color  |     round rect color when closed     |
|   sbAnimTime    | integer |          anim running time           |
| sbSwitchStatus  |  enum   | switcher default state,open or close |
| sbSwitcherColor |  color  |        the color of switcher         |

## 状态改变时的回调

EasySwitcher 有开跟关两种状态

如果你设置了 SwitchStateChangedListener, onStateChanged 方法将会在开关状态改变时被回调。

Kotlin 示例代码：

```Kotlin
// Set State Change Listener
val mEasySwitcher = findViewById<EasySwitcher>(R.id.mEasySwitcher)
mEasySwitcher.setOnStateChangedListener(object : EasySwitcher.SwitchStateChangedListener {
	override fun onStateChanged(isOpen: Boolean) {
		Log.i("MainActivity", isOpen.toString())       
	}
})
```

## 包含方法

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

/**
 * change default animation time
 */
fun setDefaultAnimTime(animTime: Int) {
	if (animTime < 0)
                throw IllegalArgumentException("Anim time can't be less than zero")
            mDefaultAnimTime = animTime
        }
}

/**
 * change default toggle color
 */
fun setDefaultSwitcherColor(color: String) {
	mDefaultSwitcherToggleColor = color
}

/**
 * change default close bg color
 */
fun setDefaultCloseBgColor(color: String) {
	mDefaultCloseColor = color
}

/**
 * change default open bg color
 */
fun setDefaultOpenBgColor(color: String) {
	mDefaultOpenColor = color
}
```



# License

```
MIT License

Copyright (c) 2018 zilchzz

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
