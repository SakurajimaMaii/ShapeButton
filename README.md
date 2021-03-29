# ShapeButton介绍
## 真机截图
![Screenshot](https://github.com/SakurajimaMaii/GStyleButton/blob/master/img/Screenshot.png)
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
   	implementation 'com.github.SakurajimaMaii:GStyleButton:beta-1.1.0'
   }
	```
## 目前支持的按钮类型
- **圆形/椭圆形按钮** 
- **矩形按钮**
- **圆角矩形按钮**
- **任意圆角矩形按钮**
## 属性设置
### 直接设定
你可以直接设定其中的属性,例如
```kt
styleButton.styleButtonShapeType = GStyleButtonShapeType.ANY_ROUNDED_RECT_SHAPE
```
你也可以使用链式方式进行设定,但注意,链式设定最后一定要调用**create()** 方法,否则无法生效.例如:
```kt
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
## 相关属性说明

|           属性名           | 属性类型  |            属性说明            |                            属性值                            |
| :------------------------: | :-------: | :----------------------------: | :----------------------------------------------------------: |
|        button_shape        | dimension |           按钮的形状           | oval_shape\|rect_shape\|corner_rect_shape\|any_corner_rect_shape |
|        oval_radius         | dimension |         圆形按钮的半径         |                                                              |
| rounded_rect_corner_radius | dimension |       矩形按钮的圆角半径       |                                                              |
|   left_top_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   left_bottom_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   right_top_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   right_bottom_corner_radius   | dimension | 任意圆角矩形按钮的四个圆角半径 |                                                              |
|   is_solid   | boolean | 按钮是否填充 | true\|false |
|   is_solid_color_gradient   | boolean | 按钮是否采用渐变填充色 | true\|false |
|   start_solid_color   | color | 渐变开始色 |  |
|   center_solid_color   | color | 渐变中间色 |  |
|   end_solid_color   | color | 渐变结束色 |  |
|   gradient_type   | dimension | 渐变方向 | linear_gradient\|radial_gradient\|sweep_shape |
|   is_stroke   | boolean | 按钮是否有边框 | true\|false |
|   stroke_width   | dimension | 按钮边框宽度 | |
|   stroke_color   | color | 按钮边框颜色 | |
|   style_background_color   | color | 默认背景色 | |