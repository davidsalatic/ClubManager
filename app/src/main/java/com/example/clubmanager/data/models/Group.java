package com.example.clubmanager.data.models;

import com.example.clubmanager.Database;

import java.util.List;
import java.util.UUID;

public class Group extends Model{

    private String name;
    private List<Member> members;
    private String id;

    private Group()
    {
        //Created only because Firebase requires  Default Constructor for getting data from Database
        //Do not remove as fetching groups from Firebase will not work
    }

    public Group(String name)
    {
        this.name=name;
        this.id=generateUniqueId();
        setParentDatabasePath( Database.GROUPS_PATH);
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public void addMember(Member member)
    {
        this.members.add(member);
    }

    public void removeMember(Member member)
    {
        this.members.remove(member);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
