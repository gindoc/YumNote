package com.cwenhui.yumnote.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.cwenhui.yumnote.widgets.pathView.PathAnimView;
import com.cwenhui.yumnote.widgets.pathView.res.StoreHousePath;
import com.cwenhui.yumnote.widgets.pathView.utils.PathParserUtils;

/**
 * Author: GIndoc on 2017/4/13 14:53
 * email : 735506583@qq.com
 * FOR   :
 */

public class LoadingPathView extends PathAnimView {
    public LoadingPathView(Context context) {
        this(context, null);
    }

    public LoadingPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSourcePath(PathParserUtils.getPathFromArrayFloatList(StoreHousePath.getPath("YumNote")));
    }
}
