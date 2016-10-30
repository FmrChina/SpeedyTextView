# SpeedyTextView
封装了Selector ，以后使用圆角带颜色背景的Selector或者Drawable只需要调用几个方法就好了~~！<br>
## 效果图
![](https://github.com/FmrChina/SpeedyTextView/raw/master/SpdTv.gif) 
## 引入
  compile 'com.fmr:speedytextview:1.0.0'
## 使用
    <speedytextview.fmr.com.spdtvlibrary.SpdTextView
            android:id="@+id/spd5"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_marginTop="10dp"
            android:gravity="center"
            android:padding="10dp"
            android:text="方某人"
            android:textSize="16.0sp"
            app:bgClickColor="@color/yellow"
            app:bgColor="@color/aqua"
            app:buttomLeftCA="30dp"
            app:buttomRightCA="40dp"
            app:normalStorkeColor="@color/cadetblue"
            app:normalStorkeWidth="2dp"
            app:pressStorkeColor="@color/cornflowerblue"
            app:pressStorkeWidth="2dp"
            app:topLeftCA="10dp"
            app:topRightCA="20dp" />
            
## 或者   
        ((SpdTextView)findViewById(R.id.spd6))
                .setBgColor(ContextCompat.getColor(this, R.color.darkorchid))
                .setBgClickColor(ContextCompat.getColor(this, R.color.brown))
                .setNormalStorkeWidth(5)
                .setNormalStorkeColor(ContextCompat.getColor(this, R.color.burlywood))
                .setPressStorkeWidth(2)
                .setPressStorkeColor(ContextCompat.getColor(this, R.color.antiquewhite))
                .setBgCircleAngle(40)
                .build();
                <br>
使用属性代码都有比较详细注释，这里不过多解释
<br>详细说明可以看 [这篇文章](http://blog.csdn.net/silentweek/article/details/52928220)
