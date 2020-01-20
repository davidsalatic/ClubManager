package com.example.clubmanager.ui.memberships;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.clubmanager.R;

public class MembershipFragment extends Fragment {

    private MembershipViewModel membershipViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        membershipViewModel =
                ViewModelProviders.of(this).get(MembershipViewModel.class);
        View root = inflater.inflate(R.layout.fragment_membership, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        membershipViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}