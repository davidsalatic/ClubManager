package com.example.clubmanager.database_async_tasks;

import android.os.AsyncTask;

import com.example.clubmanager.Database;
import com.example.clubmanager.data.models.Model;

public class InsertModelToDatabaseAsyncTask extends AsyncTask<Model,Void,Void> {
    @Override
    protected Void doInBackground(Model... models) {
        Model modelToInsert = models[0];
        String databasePathOfModel = generateDatabasePathOfModel(modelToInsert);
        Database.getDatabaseReference(databasePathOfModel).setValue(modelToInsert);
        return null;
    }

    private String generateDatabasePathOfModel(Model model) {
        return model.getParentDatabasePath()+"/"+model.getId();
    }
}
