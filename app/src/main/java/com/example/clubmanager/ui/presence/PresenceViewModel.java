package com.example.clubmanager.ui.presence;

import androidx.lifecycle.ViewModel;

import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.data.repositories.GroupRepository;

public class PresenceViewModel extends ViewModel {

    private GroupRepository groupRepository;

    public PresenceViewModel(PresenceFragment fragment) {

        groupRepository=GroupRepository.getInstance();

        groupRepository.setObserver(fragment);
    }

    public void insert(Group group) {
        groupRepository.insert(group);
    }
}