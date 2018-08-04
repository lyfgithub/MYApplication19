package com.studycoordinatelayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * CoordinatorLayout的使用核心是Behavior，Behavior就是执行你定制的动作。
 * 在讲Behavior之前必须先理解两个概念：Child和Dependency，什么意思呢？
 * Child当然是子View的意思了，是谁的子View呢，当然是CoordinatorLayout的子View；
 * 其实Child是指要执行动作的CoordinatorLayout的子View。
 *
 * 而Dependency是指Child依赖的View。
 * 简而言之，就是如过Dependency这个View发生了变化，那么Child这个View就要相应发生变化。
 * 发生变化是具体发生什么变化呢？这里就要引入Behavior，Child发生变化的具体执行的代码都是放在Behavior这个类里面。
 * ------------------------------------------------------------------------------------------
 * http://blog.csdn.net/huachao1001/article/details/51554608
 * 我们定义一个类，继承CoordinatorLayout.Behavior<T>,其中，泛型参数T是我们要执行动作的View类，也就是Child。
 */
public class MyBehavior extends CoordinatorLayout.Behavior<Button> {
    private int width;

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    //判断child的布局是否依赖dependency
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        //如果dependency是TempView的实例，说明它就是我们所需要的Dependency
        return dependency instanceof TempView;
    }

    /*当dependency发生改变时（位置、宽高等），执行这个函数
    返回true表示child的位置或者是宽高要发生改变，否则就返回false */
    @Override //每次dependency位置发生变化，都会执行onDependentViewChanged方法
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button btn, View dependency) {
        //根据dependency的位置，设置Button的位置

        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = width - left - btn.getWidth();
        int y = top;

        setPosition(btn, x, y);
        return true;
    }

    //改变位置
    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
    }


}
