package com.example.ado.cookbookuser.model;

import org.litepal.crud.DataSupport;

public class User extends DataSupport{

    private String name;
    private String password ;
    private int gender; //0为未设定，1为男，2为女
    private String headShot;
    private String birthday;

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public int getGender() { return gender; }

    public String getHeadShot() {return headShot; }

    public String getBirthday() { return birthday; }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setGender(int gender) { this.gender = gender; }

    public void setHeadShot(String headShot) {this.headShot = headShot; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

}
