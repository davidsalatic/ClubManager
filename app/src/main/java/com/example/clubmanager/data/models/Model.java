package com.example.clubmanager.data.models;

public abstract class Model {

    private String databasePath;

    public  String getDatabasePath()
    {
        return databasePath;
    }

    public void setDatabasePath(String databasePath)
    {
        this.databasePath=databasePath;
    }

    public abstract String getId();
}
