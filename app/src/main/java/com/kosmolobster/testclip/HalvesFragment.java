package com.kosmolobster.testclip;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HalvesFragment extends Fragment {
    CircleView circleView;
    private View halvesView;


    public HalvesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        halvesView = inflater.inflate(R.layout.fragment_halves, container, false);

        circleView = (CircleView)halvesView.findViewById(R.id.circleView);
        circleView.animateHalfCircleMenus(true);

        return halvesView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
