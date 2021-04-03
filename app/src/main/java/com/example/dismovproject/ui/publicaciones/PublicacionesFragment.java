package com.example.dismovproject.ui.publicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dismovproject.R;

public class PublicacionesFragment extends Fragment {

    private PublicacionesViewModel publicacionesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        publicacionesViewModel =
                new ViewModelProvider(this).get(PublicacionesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_publicaciones, container, false);
        publicacionesViewModel.getText().observe(getViewLifecycleOwner(),
                s -> {
                });
        return root;
    }
}