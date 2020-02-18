package com.example.clubmanager.data.models;

import java.util.UUID;

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

    protected String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public abstract String getId();
}
