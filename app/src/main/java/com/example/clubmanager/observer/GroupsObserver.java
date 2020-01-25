package com.example.clubmanager.observer;

import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public interface GroupsObserver {
    void updateWithAllGroups(ArrayList<Group>groups);
    void updateWithInsertedGroup(Group group);
    void showToastMessage(String message);

    void updateWithUpdatedGroupName(String groupId, String newGroupName);
}
