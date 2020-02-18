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
    private OnGroupItemClickListener listener;

    public void setOnGroupItemClickListener(OnGroupItemClickListener listener) {
        this.listener = listener;
    }

    public GroupAdapter() {
        groups = new ArrayList<>();
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new GroupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        Group currentGroup = groups.get(position);
        holder.btnGroup.setText(currentGroup.getName());
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        notifyDataSetChanged();
    }

    public void editGroup(Group newGroup) {
        for (Group oldGroup : groups)
            if (oldGroup.getId().equals(newGroup.getId())) {
                oldGroup.setName(newGroup.getName());
            }

        notifyDataSetChanged();
    }

    public void removeGroup(Group removedGroup) {
        for(int i=0;i<groups.size();i++) {
            if (groups.get(i).getId().equals(removedGroup.getId()))
                groups.remove(groups.get(i));
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


    //Holder class
    class GroupHolder extends RecyclerView.ViewHolder {
        private Button btnGroup;
        private Button btnEditGroup;
        private Button btnDeleteGroup;

        GroupHolder(@NonNull View itemView) {
            super(itemView);
            btnGroup = itemView.findViewById(R.id.btnGroup);
            btnEditGroup = itemView.findViewById(R.id.btnEditGroup);
            btnDeleteGroup = itemView.findViewById(R.id.btnDeleteGroup);

            btnEditGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Group group = groups.get(position);
                    listener.onEditGroupClick(group);
                }
            });

            btnDeleteGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Group group = groups.get(position);
                    listener.onDeleteGroupClick(group);
                }
            });

            btnGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Group group = groups.get(position);
                    listener.onGroupClick(group);
                }
            });
        }
    }
}
