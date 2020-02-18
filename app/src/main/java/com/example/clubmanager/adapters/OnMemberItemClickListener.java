package com.example.clubmanager.adapters;

import com.example.clubmanager.data.models.Member;

public interface OnMemberItemClickListener {
    void onMemberClick(Member member);
    void onEditMemberClick(Member member);
    void onDeleteMemberClick(Member member);
}
