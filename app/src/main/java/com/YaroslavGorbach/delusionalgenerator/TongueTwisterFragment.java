package com.YaroslavGorbach.delusionalgenerator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TongueTwisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TongueTwisterFragment extends Fragment {



    public TongueTwisterFragment() {
        // Required empty public constructor
    }

    public static TongueTwisterFragment newInstance() {
        return new TongueTwisterFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tongue_twister, container, false);
    }
}