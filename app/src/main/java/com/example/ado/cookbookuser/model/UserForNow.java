package com.example.ado.cookbookuser.model;

import org.litepal.crud.DataSupport;

public class UserForNow extends DataSupport {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
