package com.example.clubmanager.ui.presence;

import androidx.lifecycle.ViewModel;

import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.data.ModelRepository;

class PresenceViewModel extends ViewModel {

    private ModelRepository modelRepository;

    PresenceViewModel(PresenceFragment fragment) {

        modelRepository=ModelRepository.getInstance();
    }

    void insert(Group group) {
        modelRepository.insert(group);
    }

    void updateGroup(Group newGroup)
    {
        modelRepository.update(newGroup);
    }

    void remove(Group group)
    {
        modelRepository.remove(group);
    }
}