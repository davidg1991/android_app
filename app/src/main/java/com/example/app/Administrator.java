package com.example.app;

public class Administrator
{
    private String administratorName;
    private String password;

    public Administrator(String name, String password)
    {
        administratorName=name;
        this.password=password;
    }

    public String getAdministratorName()
    {
        return administratorName;
    }

    public void setAdministratorName(String administratorName)
    {
        this.administratorName = administratorName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
