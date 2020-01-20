package com.example.clubmanager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    public static final String GROUPS_PATH = "groups";

    public static FirebaseDatabase getInstance()
    {
        return FirebaseDatabase.getInstance();
    }

    public static DatabaseReference getRootDatabaseReference()
    {
        return getInstance().getReference();
    }

    public static DatabaseReference getDatabaseReference(String path)
    {
        return getInstance().getReference(path);
    }

}
