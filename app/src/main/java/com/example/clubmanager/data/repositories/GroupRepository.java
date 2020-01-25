package com.example.clubmanager.data.repositories;

import androidx.annotation.NonNull;

import com.example.clubmanager.Database;
import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.observer.GroupsObserver;
import com.example.clubmanager.observer.GroupsSubject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//singleton class
public class GroupRepository extends ModelRepository implements GroupsSubject {

    private static final GroupRepository groupRepository = new GroupRepository();

    private GroupsObserver observer;

    @Override
    public void setObserver(GroupsObserver groupsObserver) {
        this.observer=groupsObserver;

        //Every time that a Subject subscribes, notify him with the groups from database
        getGroupsFromDatabaseAndNotifyObserver();
    }

    private void getGroupsFromDatabaseAndNotifyObserver() {

        final ArrayList<Group> listOfGroups= new ArrayList<>();

        DatabaseReference databaseReference = Database.getDatabaseReference(Database.GROUPS_PATH);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot databaseData) {

                listOfGroups.clear();

                List<String> keys = new ArrayList<>();

                for (DataSnapshot groupSnapshot : databaseData.getChildren()) {
                    keys.add(groupSnapshot.getKey());
                    listOfGroups.add(groupSnapshot.getValue(Group.class));
                }

                notifyObserverWithAllGroups(listOfGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToastMessageToObserver(""+databaseError.getCode()+":"+databaseError.getMessage());
            }
        });
    }

    @Override
    public void notifyObserverWithAllGroups(ArrayList<Group> groups) {
        observer.updateWithAllGroups(groups);
    }

    @Override
    public void showToastMessageToObserver(String message) {
        observer.showToastMessage(message);
    }

    public void insert(Group group) {
        super.insert(group);
        notifyObserverWithInsertedGroup(group);
    }

    public void updateGroupName(String groupId, String newGroupName) {
        String databasePathOfGroup = getDatabasePathOfGroup(groupId);
        Database.getDatabaseReference(databasePathOfGroup).child("name").setValue(newGroupName);
        notifyObserverWithUpdatedGroupName(groupId,newGroupName);
    }

    private String getDatabasePathOfGroup(String groupId) {
        return Database.GROUPS_PATH+"/"+groupId;
    }

    public void notifyObserverWithUpdatedGroupName(String groupId, String newGroupName) {
        observer.updateWithNewGroupName(groupId,newGroupName);
    }

    @Override
    public void notifyObserverWithInsertedGroup(Group group) {
        observer.updateWithInsertedGroup(group);
    }

    private GroupRepository() {
    }

    public static GroupRepository getInstance() {
        return groupRepository;
    }



}
