# GStyleButton介绍

## 1. 真机截图

![Screenshot](https://img-blog.csdnimg.cn/20210329123251630.png)
![Screenshot_2](https://img-blog.csdnimg.cn/20210329124016305.png?)

## 2. 版本及语言

![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-30-%230984e3)  ![minSdkVersion](https://img.shields.io/badge/minSdkVersion-23-%23079992)  ![Programming language](https://img.shields.io/badge/Programming%20language-kotlin-%23eb3b5a)

## 3. 添加引用

### 3.1 在项目根目录下的build.gradle添加

  ```gradle
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
  ```

### 3.2 添加依赖

  ```gradle
    dependencies {
      implementation 'com.github.SakurajimaMaii:GStyleButton:beta-#2.0'
    }
  ```

## 4. 目前支持的按钮类型

- **圆形/椭圆形按钮**
- **矩形按钮**
- **圆角矩形按钮**
- **任意圆角矩形按钮**

## 5. 目前支持的四种点击状态

- **默认(normal)**
- **点击或者触摸(pressed)**
- **获取焦点(focused)**
- **不可用(unable)**

## 6. 属性设置

### 6.1 布局文件声明

```xml
<com.gcode.widget.ShapeButton
    android:id="@+id/button"
    android:layout_margin="10dp"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

### 6.2 属性设定

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

**注意,最后一定要调用create()方法,否则无法生效.**

## 相关属性说明

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
|button_gradient_type| dimension | 渐变方向 |linear_gradient<br>radial_gradient<br>sweep_shape|
|button_stroke_width|dimension|按钮边框宽度||
|button_normal_bg_color|color| 默认状态背景色||
|button_pressed_bg_color|color| 指当用户点击或者触摸该控件的背景色 ||
|button_focused_bg_color|color|指当前控件获得焦点时的背景色||
|button_unable_bg_color|color|指当前窗口获得焦点时的背景色||
|button_normal_stroke_color|color|默认状态边框色||
|button_pressed_stroke_color|color| 指当用户点击或者触摸该控件的边框色 ||
|button_focused_stroke_color|color|指当前控件获得焦点时的边框色||
|button_unable_stroke_color|color|指当前窗口获得焦点时的边框色||