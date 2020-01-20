package com.example.clubmanager.database_async_tasks;

import android.os.AsyncTask;

import com.example.clubmanager.data.models.Model;
import com.google.firebase.database.FirebaseDatabase;

public class InsertModelToDatabaseAsyncTask extends AsyncTask<Model,Void,Void> {
    @Override
    protected Void doInBackground(Model... models) {
        FirebaseDatabase.getInstance().getReference().child(models[0].getDatabasePath()).child(models[0].getId()).setValue(models[0]);
        return null;
    }
}
