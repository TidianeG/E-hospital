package com.enginer.e_hospital;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enginer.e_hospital.ui.home.HomeViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InscriptionFragment} factory method to
 * create an instance of this fragment.
 */
public class InscriptionFragment extends Fragment {

    private HomeViewModel homeViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inscription, container, false);
        final TextView textView = root.findViewById(R.id.nav_inscription);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}