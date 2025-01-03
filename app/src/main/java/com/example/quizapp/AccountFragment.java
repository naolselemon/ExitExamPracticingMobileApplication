package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
        private LinearLayout logoutButton;
        private TextView profile_image_text, name, score, rank;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        init(view);

        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle("My Account");

        String username = DBQuery.myProfile.getName();

        if (username != null) {
            name.setText(username);
        } else {
            Log.e("AccountFragment", "name TextView is null");
        }

        profile_image_text.setText(username.toUpperCase().substring(0,1));

        if (DBQuery.myPerformance != null) {
            score.setText(String.valueOf(DBQuery.myPerformance.getScore()));
        } else {
            Log.e("AccountFragment", "DBQuery.myPerformance is null.");
        }

//        score.setText(String.valueOf(DBQuery.myPerformance.getScore()));


        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        return view;
    }

    private void init(View view){
            logoutButton = view.findViewById(R.id.logoutLL);
            profile_image_text = view.findViewById(R.id.profile_image);
            name = view.findViewById(R.id.profile_image_name);
            score = view.findViewById(R.id.overall_score);
            rank = view.findViewById(R.id.rank);

            if (logoutButton == null) Log.e("AccountFragment", "logoutButton is null");
            if (profile_image_text == null) Log.e("AccountFragment", "profile_image_text is null");
            if (name == null) Log.e("AccountFragment", "name is null");
            if (score == null) Log.e("AccountFragment", "score is null");
            if (rank == null) Log.e("AccountFragment", "rank is null");
        }


}