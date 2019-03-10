package com.example.app;

public class User
{
    private String userName;

    public User(String name)
    {
        userName=name;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
