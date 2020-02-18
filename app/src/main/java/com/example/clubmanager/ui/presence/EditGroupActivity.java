package com.example.clubmanager.ui.presence;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.clubmanager.R;
import com.example.clubmanager.data.models.Group;

public class EditGroupActivity extends AddGroupActivity {

    private String groupName;
    private String groupId;

    private EditText editTextGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getExtras();

        editTextGroupName = findViewById(R.id.etGroupName);
        editTextGroupName.setText(groupName);


        setTitle("Izmena grupe '"+groupName+"'");
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();

        groupName= extras.getString(PresenceFragment.EXTRA_GROUP_NAME);
        groupId = extras.getString(PresenceFragment.EXTRA_GROUP_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void saveItemClicked() {

        String groupName = super.extractTrimmedStringFromEditText(editTextGroupName);

        if(groupName.isEmpty())
        {
            super.showFieldEmptyToastMessage();
        }
        else
        {
            Group group = new Group(groupId,groupName);
            super.saveModel(group);
            finish();
        }
    }
}