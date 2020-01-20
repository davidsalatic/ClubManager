package com.example.clubmanager.data.repositories;

import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.data.models.Model;
import com.example.clubmanager.database_async_tasks.InsertModelToDatabaseAsyncTask;
import com.example.clubmanager.observer.Observer;
import com.example.clubmanager.observer.Subject;

import java.util.ArrayList;

public class ModelRepository implements Subject {

    private ArrayList<Observer>observers;

    public ModelRepository()
    {
        observers=new ArrayList<>();
    }

    public void insert(Model model)
    {
        new InsertModelToDatabaseAsyncTask().execute(model);
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(ArrayList<Group>groups) {
        for(Observer observer: this.observers)
            observer.update(groups);
    }

    @Override
    public void showToastMessageToObservers(String message) {
        for(Observer observer:this.observers)
            observer.showToastMessage(message);
    }
}
