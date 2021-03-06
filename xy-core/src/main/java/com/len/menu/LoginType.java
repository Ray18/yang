package com.len.menu;

/**
 * @author 
 * @date 2020/8/11 23:05
 */


public enum LoginType {

    MINILOGIN("MiniLogin"),
    SYS("UserLogin");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return this.type;
    }
}
