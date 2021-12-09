<h1 align="center">ShapeButton</h1>

<p align="center">一款支持自定义的安卓按钮控件</p>

<p align="center">
<img src="https://img.shields.io/badge/compile%20sdk%20version-31-blue"/>
<img src="https://img.shields.io/badge/min%20sdk%20version-23-yellowgreen"/>
<img src="https://img.shields.io/badge/target%20sdk%20version-31-orange"/>
<img src="https://img.shields.io/badge/jdk%20version-11-%2300b894"/>
<img src="https://jitpack.io/v/SakurajimaMaii/ShapeButton.svg">
</p>

<div align="center"><a href="https://github.com/SakurajimaMaii/ShapeButton/blob/master/README.md">English</a> | 简体中文 </div>

## 💫 特性

- 😀 支持四种按钮类型 `圆形/椭圆形按钮` `矩形按钮` `圆角矩形按钮` `任意圆角矩形按钮`
- 😁 支持自定义按钮尺寸
- 😂 支持自定义按钮边框宽度以及颜色
- 🤣 支持自定义按钮圆角半径
- 😃 支持自定义按钮填充色 `是否填充` `是否采用渐变填充`
- 😄 支持自定义渐变填充色以及角度
- 😆 支持自定义不同按钮状态下的背景色以及边框颜色


<img src="https://img-blog.csdnimg.cn/20210329123251630.png" width="40%">
<img src="https://img-blog.csdnimg.cn/20210329124016305.png" width="40%">

## 😮 添加引用

1. 在项目根目录下的build.gradle添加
	```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	```

2. 添加依赖

   ```gradle
   dependencies {
   	implementation 'com.github.SakurajimaMaii:ShapeButton:0.0.5'
   }
	```

## 🤓 属性设置

```kotlin
button4.apply {
  setButtonShapeType(ShapeButtonShapeType.ANY_ROUNDED_RECT_SHAPE)
  setAnyRoundedRectCornerRadius(10f,0f,20f,0f)
  setIsSolid(true)
  setIsSolidColorGradient(true)
  setGradientDirectionType(ShapeButtonGradientType.LINEAR_GRADIENT)
  setSolidColorGradient(
      ContextCompat.getColor(context, R.color.red),
      ContextCompat.getColor(context, R.color.design_default_color_primary_dark),
      ContextCompat.getColor(context, R.color.design_default_color_primary_variant)
  )
  setStrokeWidth(10f)
  setRectButtonWidth(500f)
  setRectButtonHeight(300f)
  setBgColorStateList(
      ContextCompat.getColor(context, R.color.palevioletred),
      ContextCompat.getColor(context, R.color.blue),
      ContextCompat.getColor(context, R.color.mediumvioletred),
      ContextCompat.getColor(context, R.color.red),
  )
  setStrokeColorStateList(
      ContextCompat.getColor(context, R.color.yellow),
      ContextCompat.getColor(context, R.color.greenyellow),
      ContextCompat.getColor(context, R.color.orange),
      ContextCompat.getColor(context, R.color.darkgrey),
  )
  create()
}
```

注意,==设定最后一定要调用**create()** 方法,否则无法生效==

## 😝 相关属性说明

|属性名| 属性类型  |属性说明|属性值|
| :-----------------: | :-------: | :-----------: | :----------------: |
|button_shape| dimension |按钮的形状| oval_shape<br>rect_shape<br>rounded_rect_shape<br>any_rounded_rect_shape |
|button_rect_width| dimension |矩形按钮宽度||
|button_rect_height| dimension |矩形按钮的高度||
|button_oval_radius| dimension |圆形按钮的半径||
| button_rounded_rect_corner_radius | dimension |       矩形按钮的圆角半径  ||
|button_left_top_corner_radius|dimension|任意圆角矩形按钮的四个圆角半径||
|button_left_bottom_corner_radius|dimension|任意圆角矩形按钮的四个圆角半径||
|button_right_top_corner_radius|dimension|任意圆角矩形按钮的四个圆角半径||
|button_right_bottom_corner_radius|dimension|任意圆角矩形按钮的四个圆角半径||
|button_is_solid| boolean | 按钮是否填充 | true<br>false |
|button_is_solid_color_gradient| boolean | 按钮是否采用渐变填充色 | true<br>false |
|button_start_solid_color| color | 渐变开始色||
|button_center_solid_color| color | 渐变中间色||
|button_end_solid_color| color | 渐变结束色 ||
|button_gradient_type| dimension | 渐变类型 |linear_gradient<br>radial_gradient<br>sweep_shape|
|button_gradient_orientation| dimension | 渐变方向 ||
|button_gradient_radius| dimension | 渐变角度||
|button_stroke_width|dimension|按钮边框宽度||
|button_normal_bg_color|color| 默认状态背景色||
|button_pressed_bg_color|color| 指当用户点击或者触摸该控件的背景色 ||
|button_focused_bg_color|color|指当前控件获得焦点时的背景色||
|button_unable_bg_color|color|指当前窗口获得焦点时的背景色||
|button_normal_stroke_color|color|默认状态边框色||
|button_pressed_stroke_color|color| 指当用户点击或者触摸该控件的边框色 ||
|button_focused_stroke_color|color|指当前控件获得焦点时的边框色||
|button_unable_stroke_color|color|指当前窗口获得焦点时的边框色||

