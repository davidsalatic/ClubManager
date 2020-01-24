package com.example.clubmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubmanager.R;
import com.example.clubmanager.data.models.Group;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {

    private ArrayList<Group> groups;

    public GroupAdapter()
    {
        groups=new ArrayList<>();
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item,parent,false);
        return new GroupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        Group currentGroup = groups.get(position);
        holder.btnGroup.setText(currentGroup.getName());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void setGroups(ArrayList<Group>groups)
    {
        this.groups=groups;
        notifyDataSetChanged();
    }

    public void addGroup(Group group)
    {
        this.groups.add(group);
        notifyDataSetChanged();
    }

    class GroupHolder extends RecyclerView.ViewHolder{
        private Button btnGroup;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            btnGroup=itemView.findViewById(R.id.btnGroup);
        }
    }
}
