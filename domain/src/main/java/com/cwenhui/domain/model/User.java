package com.cwenhui.domain.model;

import java.io.Serializable;

/**
 * Author: GIndoc on 2017/4/9 20:51
 * email : 735506583@qq.com
 * FOR   :
 */

public class User implements Serializable{
    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
