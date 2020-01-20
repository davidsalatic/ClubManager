package com.example.clubmanager.data.repositories;

import androidx.annotation.NonNull;

import com.example.clubmanager.Database;
import com.example.clubmanager.data.models.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository extends ModelRepository {

    public ArrayList<Group> getAllGroups() {

        final ArrayList<Group> listOfGroups= new ArrayList<>();

        DatabaseReference databaseReference = getDatabaseReference(Database.GROUPS_PATH);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot databaseData) {

                populateListWithDataFromDatabase(listOfGroups,databaseData);

                notifyObservers(listOfGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToastMessageToObservers(""+databaseError.getCode()+":"+databaseError.getMessage());
            }
        });

        return listOfGroups;
    }

    private void populateListWithDataFromDatabase(ArrayList<Group> listOfGroups, DataSnapshot databaseData) {

        listOfGroups.clear();

        List<String> keys = new ArrayList<>();

        for (DataSnapshot groupSnapshot : databaseData.getChildren()) {
            keys.add(groupSnapshot.getKey());
            listOfGroups.add(groupSnapshot.getValue(Group.class));
        }
    }

    private DatabaseReference getDatabaseReference(String databasePath) {
        return FirebaseDatabase.getInstance().getReference(databasePath);
    }
}
