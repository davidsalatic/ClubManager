package com.example.clubmanager.adapters;

import com.example.clubmanager.data.models.Group;

public interface OnGroupItemClickListener {
    void onGroupClick(Group group);
    void onEditGroupClick(Group group);
    void onDeleteGroupClick(Group group);
}
