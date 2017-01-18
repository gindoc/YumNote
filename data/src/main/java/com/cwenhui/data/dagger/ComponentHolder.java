package com.cwenhui.data.dagger;

/**
 * Author: GIndoc on 2017/1/18 21:53
 * email : 735506583@qq.com
 * FOR   :
 */

public class ComponentHolder {
    private static DataComponent dataComponent;

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    public static void setDataComponent(DataComponent dataComponent) {
        ComponentHolder.dataComponent = dataComponent;
    }
}
