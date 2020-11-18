package com.xi.xlm.wx.util;

import org.apache.shiro.authc.AuthenticationToken;


public class JwtToken implements AuthenticationToken {

    private String token;
    private String type;

    public JwtToken(String token, String type) {
        this.token = token;
        this.type=type;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
