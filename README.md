# GStyleButton介绍
## 真机截图
![Screenshot](https://img-blog.csdnimg.cn/20210329123251630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzY5OTcxNg==,size_16,color_FFFFFF,t_70)
![Screenshot_2](https://img-blog.csdnimg.cn/20210329124016305.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzY5OTcxNg==,size_16,color_FFFFFF,t_70)
## 版本及语言
![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-30-%230984e3)  ![minSdkVersion](https://img.shields.io/badge/minSdkVersion-23-%23079992)  ![Programming language](https://img.shields.io/badge/Programming%20language-kotlin-%23eb3b5a)
## 添加引用

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
   		implementation 'com.github.SakurajimaMaii:GStyleButton:beta-1.2.0'
   	}
	```
## 目前支持的按钮类型
- **圆形/椭圆形按钮** 
- **矩形按钮**
- **圆角矩形按钮**
- **任意圆角矩形按钮**
## 目前支持的四种点击状态
- **默认(normal)** 
- **点击或者触摸(pressed)**
- **获取焦点(focused)**
- **不可用(unable)**
## 属性设置
### 直接设定
你可以直接设定其中的属性,例如
```kt
button.setButtonShapeType(GStyleButtonShapeType.ROUNDED_RECT_SHAPE).create()
```
你也可以使用链式方式进行设定,例如
```kotlin
styleButton.setStyleButtonShapeType(GStyleButtonShapeType.ANY_ROUNDED_RECT_SHAPE)
            .setAnyRoundedRectCornerRadius(10f,0f,0f,10f)
            .setIsSolidColorGradient(true)
            .setSolidColorGradient(
                ContextCompat.getColor(this, R.color.design_default_color_primary),
                ContextCompat.getColor(this, R.color.design_default_color_primary_dark),
                ContextCompat.getColor(this, R.color.design_default_color_primary_variant)
            )
            .setIsStroke(true)
            .setStrokeColor(ContextCompat.getColor(this,R.color.teal_200))
            .setStrokeWidth(10f)
            .setText(resources.getString(R.string.app_name))
            .create()
```
**但注意,无论哪种方法最后一定要调用create()方法,否则无法生效.**

## 相关属性说明

|           属性名           | 属性类型  |            属性说明            |                            属性值                            |
| :------------------------: | :-------: | :----------------------------: | :----------------------------------------------------------: |
|        button_shape        | dimension |           按钮的形状           | oval_shape<br>rect_shape<br>rounded_rect_shape<br>any_rounded_rect_shape |
|        oval_radius         | dimension |         圆形按钮的半径         |                                                              |
| rounded_rect_corner_radius | dimension |       矩形按钮的圆角半径       |                                                              |
|   left_top_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   left_bottom_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   right_top_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   right_bottom_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   is_solid   | boolean | 按钮是否填充 | true<br>false |
|   is_solid_color_gradient   | boolean | 按钮是否采用渐变填充色 | true<br>false |
|   start_solid_color   | color | 渐变开始色 |  |
|   center_solid_color   | color | 渐变中间色 |  |
|   end_solid_color   | color | 渐变结束色 |  |
|   gradient_type   | dimension | 渐变方向 | linear_gradient<br>radial_gradient<br>sweep_shape |
|   stroke_width   | dimension |            按钮边框宽度            |  |
|   normal_bg_color   | color | 默认状态背景色 | |
|      pressed_bg_color      |   color   | 指当用户点击或者触摸该控件的背景色 |                                                              |
|      focused_bg_color      |   color   |    指当前控件获得焦点时的背景色    | |
|      unable_bg_color       |   color   |    指当前窗口获得焦点时的背景色    | |
|    normal_stroke_color     |   color   |           默认状态边框色           | |
|    pressed_stroke_color    |   color   | 指当用户点击或者触摸该控件的边框色 | |
|    focused_stroke_color    |   color   |    指当前控件获得焦点时的边框色    | |
|    unable_stroke_color     |   color   |    指当前窗口获得焦点时的边框色    | |

