package com.example.clubmanager.observer;

import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public interface GroupsSubject {
    void setObserver(GroupsObserver groupsObserver);
    void notifyObserverWithAllGroups(ArrayList<Group>groups);
    void notifyObserverWithInsertedGroup(Group group);
    void showToastMessageToObserver(String message);
}
