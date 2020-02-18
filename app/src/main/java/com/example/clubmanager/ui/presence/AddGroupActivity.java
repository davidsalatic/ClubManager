package com.example.clubmanager.ui.presence;

import android.content.Intent;
import android.os.Bundle;

import com.example.clubmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroupActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.clubmanager.EXTRA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Nova grupa");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_item_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.save_item)
        {
            saveItemClicked();
        }
        else if(item.getItemId()==R.id.close)
            finish();

        return super.onOptionsItemSelected(item);
    }

    protected void saveItemClicked() {
        EditText editTextGroupName= findViewById(R.id.etGroupName);

        String groupName = extractTrimmedStringFromEditText(editTextGroupName);

        if(groupName.isEmpty())
        {
            showFieldEmptyToastMessage();
        }
        else
        {
            saveGroup(groupName);
            finish();
        }
    }

    protected String extractTrimmedStringFromEditText(EditText editText) {
        String text=editText.getText().toString();
        text=text.trim();
        return text;
    }

    protected void showFieldEmptyToastMessage()
    {
        Toast.makeText(this,"Sva polja moraju biti popunjena!",Toast.LENGTH_LONG).show();
    }

    protected void saveGroup(String groupName) {
        Intent transferData = new Intent();
        transferData.putExtra(EXTRA_NAME,groupName);
        setResult(RESULT_OK,transferData);
    }
}