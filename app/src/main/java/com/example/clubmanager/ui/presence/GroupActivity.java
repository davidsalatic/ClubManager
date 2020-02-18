package com.example.clubmanager.ui.presence;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import com.example.clubmanager.R;

public class GroupActivity extends AppCompatActivity {

    private String groupName;
    private String groupId;
    public static final String EXTRA_ADD_MEMBER_GROUP_NAME = "com.example.clubmanager.EXTRA_ADD_MEMBER_GROUP_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getExtras();

        setTitle(groupName);
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();

        groupName= extras.getString(PresenceFragment.EXTRA_GROUP_NAME);
        groupId = extras.getString(PresenceFragment.EXTRA_GROUP_ID);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_member_item)
        {
            startAddMemberActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startAddMemberActivity() {
        Intent intent = new Intent(getBaseContext(),AddMemberActivity.class);
        intent.putExtra(PresenceFragment.EXTRA_GROUP_ID,groupId);
        startActivity(intent);
    }


}
