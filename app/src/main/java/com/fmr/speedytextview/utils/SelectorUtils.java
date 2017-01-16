package com.fmr.speedytextview.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by Administrator on 2016/10/8.
 */
public class SelectorUtils {
    private static volatile SelectorUtils selectorUtils = null;
    private Context mContext;


    public static SelectorUtils getInstance(Context context) {
        SelectorUtils inst = selectorUtils;// <<< 在这里创建临时变量
        if(selectorUtils ==null) {
            synchronized (SelectorUtils.class) {
                inst = selectorUtils;
                if(inst ==null) {
                    inst =new SelectorUtils(context);
                    selectorUtils = inst;
                }
            }
        }
        return inst;// <<< 注意这里返回的是临时变量
    }


    public SelectorUtils(Context paramContext) {
        this.mContext = paramContext;
    }

    /**
     * 获取Selector
     * @param normalDraw
     * @param pressedDraw
     * @return
     */
    public static StateListDrawable getSelector(Drawable normalDraw, Drawable pressedDraw) {
        StateListDrawable stateListDrawable  = new StateListDrawable();
        stateListDrawable.addState(new int[]{ android.R.attr.state_pressed }, pressedDraw);
        stateListDrawable.addState(new int[]{ }, normalDraw);
        return stateListDrawable ;
    }

    public GradientDrawable getDrawable(float topLeftCA, float topRigthCA, float buttomLeftCA,
                                        float buttomRightCA, int bgColor, int storkeWidth, int strokeColor) {
        //把边框值设置成dp对应的px
        storkeWidth = dp2px(this.mContext, storkeWidth);

        float[] circleAngleArr = {topLeftCA, topLeftCA, topRigthCA, topRigthCA,
                buttomLeftCA, buttomLeftCA, buttomRightCA, buttomRightCA};
        //把圆角设置成dp对应的px
        for (int i = 0; i < circleAngleArr.length; i++){
            circleAngleArr[i] = dp2px(this.mContext, circleAngleArr[i]);
        }

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(circleAngleArr);//圆角
        gradientDrawable.setColor(bgColor); //背景色
        gradientDrawable.setStroke(storkeWidth, strokeColor); //边框宽度，边框颜色

        return gradientDrawable;
    }

    /**
     * 设置shape
     *
     * @param bgCircleAngle
     * @param bgColor
     * @param width
     * @param strokeColor
     * @return
     */
    public GradientDrawable getDrawable(int bgCircleAngle, int bgColor, int width, int strokeColor) {
        bgCircleAngle = dp2px(mContext,bgCircleAngle);
        width = dp2px(mContext,width);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(bgCircleAngle);
        gradientDrawable.setColor(bgColor);
        gradientDrawable.setStroke(width, strokeColor);
        return gradientDrawable;
    }

    public static int dp2px(Context context, float dp) {
        return (int) (0.5F + dp * context.getResources().getDisplayMetrics().density);
    }
}
