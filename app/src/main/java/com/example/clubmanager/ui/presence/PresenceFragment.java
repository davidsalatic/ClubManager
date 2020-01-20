package com.example.clubmanager.ui.presence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubmanager.R;
import com.example.clubmanager.adapters.GroupAdapter;
import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public class PresenceFragment extends Fragment implements com.example.clubmanager.observer.Observer {

    private PresenceViewModel presenceViewModel;
    private RecyclerView rvGroups;
    private GroupAdapter groupAdapter;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_presence, container, false);
        presenceViewModel= new PresenceViewModel(this);
        rvGroups= root.findViewById(R.id.rvGroups);
        rvGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGroups.setHasFixedSize(true);

        groupAdapter = new GroupAdapter();
        rvGroups.setAdapter(groupAdapter);
        presenceViewModel= new PresenceViewModel(this);

        return root;
    }

    @Override
    public void update(ArrayList<Group>groups) {
            groupAdapter.setGroups(groups);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }
}