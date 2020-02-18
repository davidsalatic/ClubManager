package com.example.clubmanager.ui.presence;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.clubmanager.Database;
import com.example.clubmanager.R;
import com.example.clubmanager.adapters.GroupAdapter;
import com.example.clubmanager.adapters.MemberAdapter;
import com.example.clubmanager.adapters.OnGroupItemClickListener;
import com.example.clubmanager.adapters.OnMemberItemClickListener;
import com.example.clubmanager.data.ModelRepository;
import com.example.clubmanager.data.models.Group;
import com.example.clubmanager.data.models.Member;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class GroupActivity extends AppCompatActivity {

    private String groupName;
    private String groupId;

    private RecyclerView rvMembers;
    private MemberAdapter memberAdapter;
    private FloatingActionButton fabAddTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getExtras();

        configureMemberAdapterAndSetClickListener();

        configureAddGroupButtonAndSetClickListener();

        configureRecyclerView();

        DatabaseReference membersReference = Database.getDatabaseReference(Database.MEMBERS_PATH);
        listenToDatabaseChanges(membersReference);

        setTitle(groupName);
    }

    private void listenToDatabaseChanges(DatabaseReference membersReference) {
        membersReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Member newMember = dataSnapshot.getValue(Member.class);
                if(newMember.getGroupId().equals(groupId))
                {
                    memberAdapter.addMember(newMember);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void configureMemberAdapterAndSetClickListener() {
        memberAdapter = new MemberAdapter();
        memberAdapter.setOnMemberItemClickListener(new OnMemberItemClickListener() {
            @Override
            public void onMemberClick(Member member) {

            }

            @Override
            public void onEditMemberClick(Member member) {

            }

            @Override
            public void onDeleteMemberClick(Member member) {

            }
        });
    }

    private void configureAddGroupButtonAndSetClickListener() {
        fabAddTraining = findViewById(R.id.fabAddTrainingSession);
        fabAddTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    private void configureRecyclerView() {
        rvMembers= findViewById(R.id.rvMembers);
        rvMembers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMembers.setHasFixedSize(true);

        rvMembers.setAdapter(memberAdapter);
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
