package com.example.dismovproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import static com.example.dismovproject.Register.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link miperfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class miperfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public miperfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment miperfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static miperfilFragment newInstance(String param1, String param2) {
        miperfilFragment fragment = new miperfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_miperfil, container, false);

        FirebaseFirestore fStore;
        FirebaseAuth fAuth;
        String userID;
        TextView usermailField, fullnameFiled;

        //Firebase instances
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Instance textviews
        usermailField = view.findViewById(R.id.usermailField);
        fullnameFiled = view.findViewById(R.id.fullnameField);

        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        if(userID == null){
            try {
                throw new IllegalAccessException("No has iniciado sesión");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //DocumentReference documentReference = fStore.collection("users").document(userID);

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    usermailField.setText((CharSequence) document.get("email"));
                    fullnameFiled.setText((CharSequence) document.get("fullname"));
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });





        return view;
    }
}