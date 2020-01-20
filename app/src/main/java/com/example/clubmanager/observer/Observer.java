package com.example.clubmanager.observer;

import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public interface Observer {
    void update(ArrayList<Group>groups);
    void showToastMessage(String message);
}
