package com.example.clubmanager.data.repositories;

import com.example.clubmanager.data.models.Model;
import com.example.clubmanager.database_async_tasks.InsertModelToDatabaseAsyncTask;
public abstract class ModelRepository {

    public void insert(Model model)
    {
        new InsertModelToDatabaseAsyncTask().execute(model);
    }

}
