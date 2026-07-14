package com.notebook.entity;

public class User {
    private String ID;
    private String userName;
    private String password;
    private String registerTime;

    public User(){};
    public User(String ID,String userName,String password,String registerTime){
        this.ID=ID;
        this.userName=userName;
        this.password=password;
        this.registerTime=registerTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
    @Override
    public String toString() {
        return ID+", "+userName+", "+password+", "+registerTime;
    }
}
