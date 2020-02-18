package com.example.clubmanager.ui.presence;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubmanager.Database;
import com.example.clubmanager.R;
import com.example.clubmanager.adapters.GroupAdapter;
import com.example.clubmanager.adapters.OnGroupItemClickListener;
import com.example.clubmanager.data.ModelRepository;
import com.example.clubmanager.data.models.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import static android.app.Activity.RESULT_OK;
import static com.example.clubmanager.constants.Requests.ADD_GROUP_REQUEST;
import static com.example.clubmanager.constants.Requests.EDIT_GROUP_REQUEST;

public class PresenceFragment extends Fragment{

    public static final String EXTRA_GROUP_NAME = "com.example.clubmanager.EXTRA_GROUP_NAME";
    public static final String EXTRA_GROUP_ID = "com.example.clubmanager.EXTRA_GROUP_ID";

    private RecyclerView rvGroups;
    private GroupAdapter groupAdapter;
    private View root;
    private FloatingActionButton fabAddGroup;
    private  ModelRepository repository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_presence, container, false);

        repository = ModelRepository.getInstance();

        configureGroupAdapterAndSetClickListener();

        configureAddGroupButtonAndSetClickListener();

        configureRecyclerView();

        DatabaseReference groupsReference = Database.getDatabaseReference(Database.GROUPS_PATH);
        listenToDatabaseChanges(groupsReference);

        return root;
    }


    private void configureGroupAdapterAndSetClickListener() {
        groupAdapter = new GroupAdapter();
        groupAdapter.setOnGroupItemClickListener(new OnGroupItemClickListener() {
            @Override
            public void onEditGroupClick(Group group) {
                startEditGroupActivity(group);
            }

            @Override
            public void onDeleteGroupClick(Group group) {
                repository.remove(group);
            }

            @Override
            public void onGroupClick(Group group) {
                startGroupActivity(group);
            }
        });
    }

    private void startEditGroupActivity(Group group) {
        Intent intent = new Intent(getContext(), EditGroupActivity.class);
        intent.putExtra(EXTRA_GROUP_ID, group.getId());
        intent.putExtra(EXTRA_GROUP_NAME,group.getName());
        startActivity(intent);
    }


    private void configureAddGroupButtonAndSetClickListener() {
        fabAddGroup = root.findViewById(R.id.fabAddGroup);
        fabAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddGroupActivity();
            }
        });
    }

    private void startAddGroupActivity() {
        Intent intent = new Intent(getContext(), AddGroupActivity.class);
        startActivity(intent);
    }

    private void startGroupActivity(Group group) {
        Intent intent = new Intent(getContext(), GroupActivity.class);
        intent.putExtra(EXTRA_GROUP_ID,group.getId());
        intent.putExtra(EXTRA_GROUP_NAME,group.getName());
        startActivity(intent);
    }

    private void configureRecyclerView() {
        rvGroups= root.findViewById(R.id.rvGroups);
        rvGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGroups.setHasFixedSize(true);

        rvGroups.setAdapter(groupAdapter);
    }

    private void listenToDatabaseChanges(DatabaseReference groupsReference) {
        groupsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Group newGroup = dataSnapshot.getValue(Group.class);
                groupAdapter.addGroup(newGroup);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Group newGroup = dataSnapshot.getValue(Group.class);
                groupAdapter.editGroup(newGroup);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Group removedGroup = dataSnapshot.getValue(Group.class);
                groupAdapter.removeGroup(removedGroup);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}