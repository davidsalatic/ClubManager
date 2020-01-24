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
import com.example.clubmanager.R;
import com.example.clubmanager.adapters.GroupAdapter;
import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.observer.GroupsObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class PresenceFragment extends Fragment implements GroupsObserver {

    public static final int ADD_GROUP_REQUEST = 1;

    private PresenceViewModel presenceViewModel;
    private RecyclerView rvGroups;
    private GroupAdapter groupAdapter;
    private View root;
    private FloatingActionButton fabAddGroup;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_presence, container, false);

        presenceViewModel= new PresenceViewModel(this);
        groupAdapter = new GroupAdapter();

        configureAddGroupButtonAndSetClickListener();

        configureRecyclerView();

        return root;
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
        startActivityForResult(intent,ADD_GROUP_REQUEST);
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

        super.onActivityResult(requestCode, resultCode, data);
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
    public void showToastMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }


}