package com.cwenhui.yumnote.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.cwenhui.data.local.Const;
import com.cwenhui.domain.model.User;

public class Saver {
    private static SharedPreferences sharePref;

    public static SharedPreferences getIntance() {
        return sharePref;
    }

    public static void initSaver(Context context) {
        sharePref = context.getSharedPreferences("saveinfo", Context.MODE_PRIVATE);
    }

    public static boolean getLoginState() {
        return sharePref.getBoolean(Const.SharePreferenceKey.LOGIN_STATE, false);
    }

    public static void setLoginState(boolean loginState) {
        Editor edit = sharePref.edit();
        edit.putBoolean(Const.SharePreferenceKey.LOGIN_STATE, loginState);
        edit.commit();
    }

    public static <T extends Object>void saveSerializableObject(T object , String key) {
        Editor edit = sharePref.edit();
        edit.putString(key, EnCodeUtil.objectEncode(object));
        edit.commit();
    }
    public static <T extends Object>T getSerializableObject(String key) {
        String base64Publish = sharePref.getString(key, "");
        return EnCodeUtil.objectDecode(base64Publish);
    }

    public static void setToken(String token) {
        Editor editor = sharePref.edit();
        editor.putString(Const.SharePreferenceKey.TOKEN, token);
        editor.commit();
    }

    public static String getToken() {
        return sharePref.getString(Const.SharePreferenceKey.TOKEN, "");
    }

    public static void logout() {
        setToken("");
        setLoginState(false);
        saveSerializableObject(null, Const.SharePreferenceKey.USER);
    }

    public static void login(User user, String token) {
        setLoginState(true);
        setToken(token);
        saveSerializableObject(user, Const.SharePreferenceKey.USER);
    }
}
