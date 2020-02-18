package com.example.clubmanager.data;

import com.example.clubmanager.Database;
import com.example.clubmanager.data.models.Model;

public  class ModelRepository {

    private static final ModelRepository modelRepository = new ModelRepository();

    public void insert(Model model) {
        String databasePathOfModel = generateDatabasePathOfModel(model);
        Database.getDatabaseReference(databasePathOfModel).setValue(model);
    }

    public void remove(Model model)
    {
        String databasePathOfModel = generateDatabasePathOfModel(model);
        Database.getDatabaseReference(databasePathOfModel).removeValue();
    }

    private String generateDatabasePathOfModel(Model model) {
        return model.getParentDatabasePath() + "/" + model.getId();
    }

    private ModelRepository() {
    }

    public static ModelRepository getInstance() {
        return modelRepository;
    }




}
