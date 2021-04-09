package com.example.dismovproject.ui.imagenes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dismovproject.R;

public class ImagenesFragment extends Fragment {

    private ImagenesViewModel imagenesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        imagenesViewModel =
                new ViewModelProvider(this).get(ImagenesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_imagenes, container, false);
        imagenesViewModel.getText().observe(getViewLifecycleOwner(), s -> {
        });
        return root;
    }
}