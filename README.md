# ShapeButton介绍
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
   	implementation 'com.github.SakurajimaMaii:GStyleButton:beta-1.0.0'
   }
	```
## 目前支持的按钮类型
- **圆形/椭圆形按钮** 
- **矩形按钮**
- **圆角矩形按钮**
- **任意圆角矩形按钮**
## 相关属性说明
```xml
<declare-styleable name="StyleButton">
        <!--按钮的基本形状-->
        <attr name="button_shape" format="dimension">
            <!--椭圆按钮-->
            <enum name="oval_shape" value="1"/>
            <!--矩形按钮-->
            <enum name="rect_shape" value="2"/>
            <!--圆角矩形按钮-->
            <enum name="corner_rect_shape" value="3"/>
            <!--任意圆角矩形按钮-->
            <enum name="any_corner_rect_shape" value="4"/>
        </attr>
        <!--圆形按钮的半径-->
        <attr name="circle_radius" format="dimension"/>
        <!--圆角矩形按钮的圆角半径-->
        <attr name="corner_radius" format="dimension"/>
        <!--任意圆角矩形按钮的四个圆角半径-->
        <attr name="left_top_corner_radius" format="dimension"/>
        <attr name="left_bottom_corner_radius" format="dimension"/>
        <attr name="right_top_corner_radius" format="dimension"/>
        <attr name="right_bottom_corner_radius" format="dimension"/>
        <!--是否填充-->
        <attr name="is_filling" format="boolean"/>
        <!--边框属性-->
        <attr name="is_stroke" format="boolean"/>
        <attr name="stroke_width" format="dimension"/>
        <attr name="stroke_color" format="color"/>
        <!--填充色是否采用渐变以及渐变色-->
        <attr name="is_solid_color_gradient" format="boolean"/>
        <attr name="start_solid_color" format="color"/>
        <attr name="center_solid_color" format="color"/>
        <attr name="end_solid_color" format="color"/>
        <!--渐变方向-->
        <attr name="gradient_type" format="dimension">
            <enum name="linear_gradient" value="0"/>
            <enum name="radial_gradient" value="1"/>
            <enum name="sweep_shape" value="2"/>
        </attr>
        <!--默认背景色-->
        <attr name="background_color" format="color"/>
</declare-styleable>
```