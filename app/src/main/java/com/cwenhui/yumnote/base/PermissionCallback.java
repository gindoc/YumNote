package com.cwenhui.yumnote.base;

/**
 * 作者: 陈文辉
 * 日期: 2016/12/6 15:53
 * 作用: 权限回调接口
 */

public interface PermissionCallback {
    /**
     * 成功获取权限
     */
    void hasPermission();

    /**
     * 没有权限
     * @param hasPermanentlyDenied 是否点击不再询问，被设置为永久拒绝权限
     */
    void noPermission(Boolean hasPermanentlyDenied);
}
