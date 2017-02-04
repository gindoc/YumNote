package com.cwenhui.domain.response;

/**
 * 作者: GIndoc
 * 日期: 2017/2/4 17:22
 * 作用:
 */
public class Response {
    private int erro_code;

    private String erro_msg;

    public String getErro_msg() {
        return erro_msg;
    }

    public void setErro_msg(String erro_msg) {
        this.erro_msg = erro_msg;
    }

    public int getErro_code() {
        return erro_code;
    }

    public void setErro_code(int erro_code) {
        this.erro_code = erro_code;
    }
}
