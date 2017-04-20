package com.cwenhui.yumnote.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.cwenhui.yumnote.R;

/**
 * Author: GIndoc on 2017/4/20 13:28
 * email : 735506583@qq.com
 * FOR   :
 */

public class SimilarToolbarBehavior extends CoordinatorLayout.Behavior {
    private int id;
    public SimilarToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.getResources().obtainAttributes(attrs,
                R.styleable.SimilarToolbarBehavior);
        id = ta.getResourceId(R.styleable.SimilarToolbarBehavior_anchor_id, -1);
        ta.recycle();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == id;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(-dependency.getTop());
        return true;
    }
}
