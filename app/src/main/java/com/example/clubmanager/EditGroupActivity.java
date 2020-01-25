package com.example.clubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.clubmanager.ui.presence.PresenceFragment;



public class EditGroupActivity extends AddGroupActivity {

    public static final String EXTRA_NEW_GROUP_NAME = "com.example.clubmanager.EXTRA_NEW_GROUP_NAME";
    public static final String EXTRA_GROUP_ID = "com.example.clubmanager.EXTRA_GROUP_ID";

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
            saveGroup(groupName);
            finish();
        }
    }

    @Override
    protected void saveGroup(String groupName) {
        Intent transferData = new Intent();
        transferData.putExtra(EXTRA_NEW_GROUP_NAME,groupName);
        transferData.putExtra(EXTRA_GROUP_ID,groupId);
        setResult(RESULT_OK,transferData);
    }
}
