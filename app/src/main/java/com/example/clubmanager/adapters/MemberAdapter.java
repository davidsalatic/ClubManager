package com.example.clubmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubmanager.R;
import com.example.clubmanager.data.models.Member;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

    private ArrayList<Member> members;
    private OnMemberItemClickListener listener;

    public void setOnMemberItemClickListener(OnMemberItemClickListener listener) {
        this.listener = listener;
    }

    public MemberAdapter() {
        members= new ArrayList<>();
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new MemberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        Member currentMember= members.get(position);
        holder.btnMemberName.setText(currentMember.getName()+" "+currentMember.getSurname());
    }

    public void addMember(Member member) {
        this.members.add(member);
        notifyDataSetChanged();
    }

    public void editMember(Member newMember) {
        for (Member oldMember : members)
            if (oldMember.getId().equals(newMember.getId())) {
                //TODO
            }

        notifyDataSetChanged();
    }

    public void removeMember(Member removedMember) {
        for(int i=0;i<members.size();i++) {
            if (members.get(i).getId().equals(removedMember.getId())) {
                //TODO
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return members.size();
    }


    //Holder class
    class MemberHolder extends RecyclerView.ViewHolder {
        private Button btnMemberName;
        private Button btnEditMember;
        private Button btnDeleteMember;

        MemberHolder(@NonNull View itemView) {
            super(itemView);
            btnMemberName = itemView.findViewById(R.id.btnMember);
            btnEditMember = itemView.findViewById(R.id.btnEditMember);
            btnDeleteMember = itemView.findViewById(R.id.btnDeleteMember);

            btnEditMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Member member= members.get(position);
                    listener.onEditMemberClick(member);
                }
            });

            btnDeleteMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Member member= members.get(position);
                    listener.onDeleteMemberClick(member);
                }
            });

            btnMemberName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Member member= members.get(position);
                    listener.onMemberClick(member);
                }
            });
        }
    }
}
