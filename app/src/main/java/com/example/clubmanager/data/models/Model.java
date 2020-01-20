package com.example.clubmanager.data.models;

public abstract class Model {

    private String parentDatabasePath;

    public  String getParentDatabasePath()
    {
        return parentDatabasePath;
    }

    protected void setParentDatabasePath(String parentDatabasePath)
    {
        this.parentDatabasePath=parentDatabasePath;
    }

    public abstract String getId();
}
