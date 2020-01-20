package com.example.clubmanager.ui.presence;

import androidx.lifecycle.ViewModel;

import com.example.clubmanager.data.repositories.GroupRepository;
import com.example.clubmanager.data.repositories.ModelRepository;
import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public class PresenceViewModel extends ViewModel {

    private ArrayList<Group> groups;
    private GroupRepository repository;

    public PresenceViewModel(PresenceFragment fragment) {

        repository =new GroupRepository();
        repository.addObserver(fragment);
        groups= repository.getAllGroups();

    }
}