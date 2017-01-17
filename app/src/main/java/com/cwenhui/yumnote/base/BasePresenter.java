package com.cwenhui.yumnote.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 作者: 陈文辉
 * 日期: 2017/1/17 23:40
 * 作用:
 */
public class BasePresenter<T> {
    public Reference<T> mViewRef;

    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView(){
        return mViewRef == null ? null : mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}