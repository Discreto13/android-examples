package com.examples.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public final class MainFragment extends Fragment {
    Button btnAction;
    Button btnFirstFragment;
    Button btnSecondFragment;

    public void onCreate(Bundle args) {
        ((MainActivity)getActivity()).addLog("MainFragment::onCreate()");
        super.onCreate(args);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).addLog("MainFragment::onCreateView()");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btnAction = rootView.findViewById(R.id.btnAction);
        btnFirstFragment = rootView.findViewById(R.id.btnFirstActivity);
        btnSecondFragment = rootView.findViewById(R.id.btnSecondActivity);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).addLog("MainFragment::btnAction");
            }
        });
        btnFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).addLog("MainFragment::btnFirstFragment");
                ((MainActivity)getActivity()).selectFirstFragment();
            }
        });
        btnSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).addLog("MainFragment::btnSecondFragment");
                ((MainActivity)getActivity()).selectSecondFragment();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
