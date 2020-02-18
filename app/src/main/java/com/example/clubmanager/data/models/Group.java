package com.example.clubmanager.data.models;

import com.example.clubmanager.Database;

public class Group extends Model{

    private String name;
    private String id;

    private Group()
    {
        //Created only because Firebase requires  Default Constructor for getting data from Database
        //Do not remove as fetching groups from Firebase will not work
    }

    public Group(String name)
    {
        this.name=name;
        this.id=super.generateUniqueId();
        super.setParentDatabasePath(Database.GROUPS_PATH);
    }

    public Group(String id,String name)
    {
        this.id=id;
        this.name=name;
        super.setParentDatabasePath(Database.GROUPS_PATH);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
