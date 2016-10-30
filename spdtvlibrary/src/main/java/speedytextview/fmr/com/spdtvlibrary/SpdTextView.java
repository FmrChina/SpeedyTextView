package speedytextview.fmr.com.spdtvlibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/8.
 */
public class SpdTextView extends TextView implements View.OnClickListener {
    private Context mContext;

    private int bgColor; //背景色

    private int bgCircleAngle; //背景圆角

    private int bgClickColor; //点击后的背景色

    private int normalStorkeWidth; //未点击的边框宽度

    private int normalStorkeColor; // 未点击的边框色

    private int pressStorkeWidth; //点击后的边框宽度

    private int pressStorkeColor; //点击后的边框颜色

    private int topLeftCA; //上左圆角

    private int topRigthCA; //上右圆角

    private int buttomLeftCA; //下左圆角

    private int buttomRightCA; //下右圆角

    private int textColor; //未点击时文字颜色

    private int pressTextColor; //点击时文字颜色

    public SpdTextView(Context context) {
        this(context, null);
    }

    public SpdTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpdTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        setOnClickListener(this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpeedyTextView);

        bgColor = a.getColor(R.styleable.SpeedyTextView_bgColor, Color.TRANSPARENT);
        bgClickColor = a.getColor(R.styleable.SpeedyTextView_bgClickColor, Color.TRANSPARENT);
        bgCircleAngle = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_bgCircleAngle, 0);

        normalStorkeWidth = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_normalStorkeWidth, 0);
        normalStorkeColor = a.getColor(R.styleable.SpeedyTextView_normalStorkeColor, Color.TRANSPARENT); //边框默认为透明色 0
        pressStorkeWidth = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_pressStorkeWidth, 0);
        pressStorkeColor = a.getColor(R.styleable.SpeedyTextView_pressStorkeColor, Color.TRANSPARENT); //边框默认为透明色

        topLeftCA = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_topLeftCA, 0);
        topRigthCA = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_topRightCA, 0);
        buttomLeftCA = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_buttomLeftCA, 0);
        buttomRightCA = a.getDimensionPixelOffset(R.styleable.SpeedyTextView_buttomLeftCA, 0);

        pressTextColor = a.getColor(R.styleable.SpeedyTextView_pressTextColor, Color.TRANSPARENT); //透明色值为 0

        a.recycle();

        setSelect(bgColor, bgCircleAngle, bgClickColor, normalStorkeWidth, normalStorkeColor, pressStorkeWidth,
                pressStorkeColor, topLeftCA, topRigthCA, buttomLeftCA, buttomRightCA);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setSelect(int bgColor, int bgCircleAngle, int bgClickColor, int normalStorkeWidth,
                           int normalStorkeColor, int pressStorkeWidth, int pressStorkeColor, int topLeftCA,
                           int topRigthCA, int buttomLeftCA, int buttomRightCA) {
        textColor = getCurrentTextColor();
        GradientDrawable normal = null;
        GradientDrawable press = null;

        if (bgCircleAngle != 0) {
            normal = getDrawable(bgCircleAngle, bgColor, normalStorkeWidth, normalStorkeColor);
            press = getDrawable(bgCircleAngle, bgClickColor, pressStorkeWidth, pressStorkeColor);
        } else {
            normal = getDrawable(topLeftCA, topRigthCA, buttomLeftCA, buttomRightCA,
                    bgColor, normalStorkeWidth, normalStorkeColor);
            press = getDrawable(topLeftCA, topRigthCA, buttomLeftCA, buttomRightCA,
                    bgClickColor, pressStorkeWidth, pressStorkeColor);
        }

        //没有设置点击背景色，表示不设置Selector 只设置Draw
        if (bgClickColor != 0) {
            StateListDrawable selector = getSelector(normal, press);
            setBackground(selector);
        } else {
            setBackground(normal);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (pressTextColor != 0) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    setTextColor(pressTextColor);
                    break;
                case MotionEvent.ACTION_UP:
                    setTextColor(textColor);
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置背景选择器
     * @param normalDraw
     * @param pressedDraw
     * @return stateListDrawable
     */
    private StateListDrawable getSelector(Drawable normalDraw, Drawable pressedDraw) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDraw);
        stateListDrawable.addState(new int[]{}, normalDraw);
        return stateListDrawable;
    }


    /**
     * 设置shape
     *
     * @param bgCircleAngle
     * @param bgColor
     * @param storkeWidth
     * @param strokeColor
     * @return gradientDrawable
     */
    private GradientDrawable getDrawable(int bgCircleAngle, int bgColor, int storkeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(bgCircleAngle);
        gradientDrawable.setColor(bgColor);
        gradientDrawable.setStroke(storkeWidth, strokeColor);
        return gradientDrawable;
    }

    private GradientDrawable getDrawable(float topLeftCA, float topRigthCA, float buttomLeftCA, float buttomRightCA, int bgColor, int storkeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();

        //top-left, top-right, bottom-right, bottom-left
        float[] radius = new float[]{topLeftCA, topLeftCA, topRigthCA, topRigthCA, buttomLeftCA, buttomLeftCA, buttomRightCA, buttomRightCA};
        //这个方法传入的数组长度必须是 > = 8 否则会抛数值下标越界  数组值分别对应 top-left, top-right, bottom-right, bottom-left
        gradientDrawable.setCornerRadii(radius);
        gradientDrawable.setColor(bgColor);
        gradientDrawable.setStroke(storkeWidth, strokeColor);
        return gradientDrawable;
    }


    public SpdTextView setBgColor(int color) {
        bgColor = color;
        return this;
    }

    public SpdTextView setBgCircleAngle(int angle) {
        bgCircleAngle = dp2px(angle);
        return this;
    }

    public SpdTextView setBgClickColor(int color) {
        bgClickColor = color;
        return this;
    }

    public SpdTextView setNormalStorkeWidth(int width) {
        normalStorkeWidth = dp2px(width);
        return this;
    }

    public SpdTextView setNormalStorkeColor(int color) {
        normalStorkeColor = color;
        return this;
    }

    public SpdTextView setPressStorkeWidth(int width) {
        pressStorkeWidth = dp2px(width);
        return this;
    }

    public SpdTextView setPressStorkeColor(int color) {
        pressStorkeColor = color;
        return this;
    }

    public SpdTextView setTopLeftCA(int angle) {
        topLeftCA = dp2px(angle);
        return this;
    }

    public SpdTextView setTopRigthCA(int angle) {
        topRigthCA = dp2px(angle);
        return this;
    }

    public SpdTextView setButtomLeftCA(int angle) {
        buttomLeftCA = dp2px(angle);
        return this;
    }

    public SpdTextView setButtomRightCA(int angle) {
        buttomRightCA = dp2px(angle);
        return this;
    }

    public SpdTextView setPressTextColor(int color) {
        pressTextColor = color;
        return this;
    }

    public void build() {
        setSelect(bgColor, bgCircleAngle, bgClickColor, normalStorkeWidth, normalStorkeColor, pressStorkeWidth,
                pressStorkeColor, topLeftCA, topRigthCA, buttomLeftCA, buttomRightCA);
    }

    /**
     * px 转 dp
     *
     * @param dp
     * @return px
     */
    private int dp2px(int dp) {
        return Utils.dp2px(mContext, dp);
    }

    //直接设置点击监听，就懒得写事件分发了 > _ < ~~
    @Override
    public void onClick(View v) {
//        Toast.makeText(mContext, "spd", Toast.LENGTH_SHORT).show();
    }
}
