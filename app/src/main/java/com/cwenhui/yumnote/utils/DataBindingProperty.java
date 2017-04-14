package com.cwenhui.yumnote.utils;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Author: GIndoc on 2017/4/14 22:02
 * email : 735506583@qq.com
 * FOR   :
 */

public class DataBindingProperty {
    @BindingAdapter("time_convert")
    public static void timeConvert(TextView textView, long time) {
        textView.setText(TimeUtils.milliseconds2String(time));
    }
}
