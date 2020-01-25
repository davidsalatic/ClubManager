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
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener=listener;
    }



    public interface  OnItemClickListener {
        void onEditGroupClick(Group group);
    }

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

    public void changeGroupName(String groupId, String newGroupName) {
        for(Group group:groups)
            if(group.getId().equals(groupId))
                group.setName(newGroupName);

        notifyDataSetChanged();
    }

    class GroupHolder extends RecyclerView.ViewHolder{
        private Button btnGroup;
        private Button btnEditGroup;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            btnGroup=itemView.findViewById(R.id.btnGroup);
            btnEditGroup=itemView.findViewById(R.id.btnEditGroup);


            btnEditGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            Group group = groups.get(position);
                            listener.onEditGroupClick(group);
                        }
                    }
                }
            });
        }


    }

}
