package com.example.clubmanager.data.models;

import com.example.clubmanager.Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Member extends Model{

    private String id;
    private String name;
    private String surname;
    private Group group;
    private ArrayList<TrainingSessionPresence> trainingSessionPresences;

    private Member()
    {
        //Created only because Firebase requires  Default Constructor for getting data from Database
        //Do not remove as fetching groups from Firebase will not work
    }

    public Member(String name,String surname)
    {
        this.id = super.generateUniqueId();
        this.name=name;
        this.surname=surname;
        super.setParentDatabasePath(Database.MEMBERS_PATH);
        trainingSessionPresences= new ArrayList<>() ;
    }

    public void addTrainingSession(TrainingSessionPresence trainingSessionPresence)
    {
        this.trainingSessionPresences.add(trainingSessionPresence);
    }

    public void setGroup(Group group)
    {
        this.group=group;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Group getGroup() {
        return group;
    }

    public ArrayList<TrainingSessionPresence> getTrainingSessionPresences() {
        return trainingSessionPresences;
    }
}
