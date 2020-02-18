package com.example.clubmanager.ui.presence;

import android.os.Bundle;

import com.example.clubmanager.R;
import com.example.clubmanager.data.ModelRepository;
import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.data.models.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroupActivity extends AppCompatActivity {

    private ModelRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repository = ModelRepository.getInstance();

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
            Group group = new Group(groupName);
            saveModel(group);
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

    protected void saveModel(Model model) {
        repository.insert(model);
    }
}
