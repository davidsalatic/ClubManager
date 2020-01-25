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

import com.example.clubmanager.AddGroupActivity;
import com.example.clubmanager.EditGroupActivity;
import com.example.clubmanager.R;
import com.example.clubmanager.adapters.GroupAdapter;
import com.example.clubmanager.adapters.OnGroupItemClickListener;
import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.observer.GroupsObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.clubmanager.constants.Requests.ADD_GROUP_REQUEST;
import static com.example.clubmanager.constants.Requests.EDIT_GROUP_REQUEST;

public class PresenceFragment extends Fragment implements GroupsObserver {

    public static final String EXTRA_GROUP_NAME = "com.example.clubmanager.EXTRA_GROUP_NAME";
    public static final String EXTRA_GROUP_ID = "com.example.clubmanager.EXTRA_GROUP_ID";

    private PresenceViewModel presenceViewModel;
    private RecyclerView rvGroups;
    private GroupAdapter groupAdapter;
    private View root;
    private FloatingActionButton fabAddGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_presence, container, false);

        presenceViewModel= new PresenceViewModel(this);

        configureGroupAdapterAndSetClickListener();

        configureAddGroupButtonAndSetClickListener();

        configureRecyclerView();

        return root;
    }

    private void configureGroupAdapterAndSetClickListener() {
        groupAdapter = new GroupAdapter();
        groupAdapter.setOnGroupItemClickListener(new OnGroupItemClickListener() {
            @Override
            public void onEditGroupClick(Group group) {
                startEditGroupActivity(group);
            }
        });
    }

    private void startEditGroupActivity(Group group) {
        Intent intent = new Intent(getContext(), EditGroupActivity.class);
        intent.putExtra(EXTRA_GROUP_ID,group.getId());
        intent.putExtra(EXTRA_GROUP_NAME,group.getName());
        startActivityForResult(intent, EDIT_GROUP_REQUEST);
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
        startActivityForResult(intent, ADD_GROUP_REQUEST);
    }

    private void configureRecyclerView() {
        rvGroups= root.findViewById(R.id.rvGroups);
        rvGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGroups.setHasFixedSize(true);

        rvGroups.setAdapter(groupAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(isSuccessfulAdd(requestCode,resultCode))
        {
            String groupName = data.getStringExtra(AddGroupActivity.EXTRA_NAME);

            presenceViewModel.insert(new Group(groupName));
        }
        else if(isSuccessfulEdit(requestCode,resultCode))
        {
            String newGroupName = data.getStringExtra(EditGroupActivity.EXTRA_NEW_GROUP_NAME);
            String idOfEditedGroup = data.getStringExtra(EditGroupActivity.EXTRA_GROUP_ID);

            presenceViewModel.updateGroupName(idOfEditedGroup,newGroupName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isSuccessfulEdit(int requestCode, int resultCode) {
        if(resultCode==RESULT_OK && requestCode==EDIT_GROUP_REQUEST)
            return true;

        return false;
    }

    public boolean isSuccessfulAdd(int requestCode ,int resultCode)
    {
        if(resultCode==RESULT_OK && requestCode==ADD_GROUP_REQUEST)
            return true;

        return false;
    }

    @Override
    public void updateWithAllGroups(ArrayList<Group>groups) {
            groupAdapter.setGroups(groups);
    }

    @Override
    public void updateWithInsertedGroup(Group group) {
        groupAdapter.addGroup(group);
    }

    @Override
    public void updateWithNewGroupName(String groupId, String newGroupName) {
        groupAdapter.changeGroupName(groupId,newGroupName);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }


}