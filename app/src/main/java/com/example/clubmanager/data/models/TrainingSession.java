package com.example.clubmanager.data.models;

import java.util.Date;

public class TrainingSession extends Model {

    private String id;
    private Date dateHeld;

    private TrainingSession()
    {
        //Created only because Firebase requires  Default Constructor for getting data from Database
        //Do not remove as fetching groups from Firebase will not work
    }

    public TrainingSession(Date date)
    {
        this.id=super.generateUniqueId();
    }

    @Override
    public String getId() {
        return this.id;
    }
}
