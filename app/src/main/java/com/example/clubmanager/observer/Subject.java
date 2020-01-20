package com.example.clubmanager.observer;

import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public interface Subject {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(ArrayList<Group>groups);
    void showToastMessageToObservers(String message);
}
