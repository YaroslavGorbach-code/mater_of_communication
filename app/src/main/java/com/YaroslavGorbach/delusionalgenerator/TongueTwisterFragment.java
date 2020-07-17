package com.YaroslavGorbach.delusionalgenerator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.w3c.dom.Text;

import java.util.Random;

public class TongueTwisterFragment extends Fragment {

    private TextView mTwisters_tv;
    private ImageButton mNextTwistButton;
    private String[] mTwists = {};
    private Random r = new Random();
    private AdView mAdView;




    public TongueTwisterFragment() {
        // Required empty public constructor
    }

    public static TongueTwisterFragment newInstance() {
        return new TongueTwisterFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tongue_twister, container, false);
        mTwisters_tv = view.findViewById(R.id.twisters);
        mNextTwistButton = view.findViewById(R.id.nextTwist);
        mTwists = getResources().getStringArray(R.array.twisters);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTwist();
        mNextTwistButton.setOnClickListener(v -> {
            setTwist();
        });

        mAdView = view.findViewById(R.id.adViewTab2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void setTwist() {
        YoYo.with(Techniques.FadeIn)
                .duration(400)
                .playOn(mTwisters_tv);
        mTwisters_tv.setText(mTwists[r.nextInt(mTwists.length)]);
    }
}