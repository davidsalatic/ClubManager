package com.example.clubmanager.database_async_tasks;

import android.os.AsyncTask;

import com.example.clubmanager.Database;
import com.example.clubmanager.data.models.Model;

public class InsertModelToDatabaseAsyncTask extends AsyncTask<Model,Void,Void> {
    @Override
    protected Void doInBackground(Model... models) {
        Database.getRootDatabaseReference().child(models[0].getDatabasePath()).child(models[0].getId()).setValue(models[0]);
        return null;
    }
}
