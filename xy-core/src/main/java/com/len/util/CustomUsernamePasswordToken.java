package com.len.util;

import com.len.menu.LoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author 
 * @date 2018/8/18.
 * @email 
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private LoginType type;



    public CustomUsernamePasswordToken(final String username, final String password, LoginType loginType) {
        super(username,password);
        this.type = loginType;
    }


    public LoginType getType() {
        return type;
    }

    public void setType(LoginType type) {
        this.type = type;
    }
}
