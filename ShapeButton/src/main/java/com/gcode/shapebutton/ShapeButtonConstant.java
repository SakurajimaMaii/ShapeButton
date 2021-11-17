package com.gcode.shapebutton;

import android.graphics.drawable.GradientDrawable;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2021/11/12
 */
public class ShapeButtonConstant {

    public static final int OVAL_SHAPE = 1;
    public static final int RECT_SHAPE = 2;
    public static final int ROUNDED_RECT_SHAPE = 3;
    public static final int ANY_ROUNDED_RECT_SHAPE = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            OVAL_SHAPE,RECT_SHAPE,ROUNDED_RECT_SHAPE,ANY_ROUNDED_RECT_SHAPE
    })
    public @interface ShapeType {}

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            GradientDrawable.LINEAR_GRADIENT,
            GradientDrawable.RADIAL_GRADIENT,
            GradientDrawable.SWEEP_GRADIENT
    })
    public @interface GradientType {}
}
