package com.luteflex.microservices.user.bean;


public class UserConfiguration
{
    private int maximum;
    private int minimum;
    //no-argument constructor
    protected UserConfiguration()
    {
    }
    //generating getters
    public int getMaximum()
    {
        return maximum;
    }
    public int getMinimum()
    {
        return minimum;
    }
    //genetrating constructor using fields
    public UserConfiguration(int maximum, int minimum)
    {
        super();
        this.maximum = maximum;
        this.minimum = minimum;
    }
}