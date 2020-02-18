package com.example.clubmanager.ui.presence;

import android.os.Bundle;

import com.example.clubmanager.data.ModelRepository;
import com.example.clubmanager.data.models.Member;
import com.example.clubmanager.data.models.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.clubmanager.R;

public class AddMemberActivity extends AddGroupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_item_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void saveItemClicked() {
        EditText etMemberName= findViewById(R.id.etMemberName);
        EditText etMemberSurname = findViewById(R.id.etMemberSurname);

        String name = super.extractTrimmedStringFromEditText(etMemberName);
        String surname = super.extractTrimmedStringFromEditText(etMemberSurname);

        if(name.isEmpty() || surname.isEmpty())
        {
            super.showFieldEmptyToastMessage();
        }
        else
        {
            Member member = new Member(name,surname);
            saveMember(member);
//            finish();
        }
    }

    private void saveMember(Member member)
    {
        ModelRepository repository = ModelRepository.getInstance();
        repository.insert(member);
    }
}
