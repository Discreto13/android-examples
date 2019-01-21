package com.examples.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public final class SecondFragment extends Fragment {
    Button btnAction;
    Button btnExit;

    public void onCreate(Bundle args) {
        ((MainActivity)getActivity()).addLog("SecondFragment::onCreate()");
        super.onCreate(args);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).addLog("SecondFragment::onCreateView()");

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        btnAction = rootView.findViewById(R.id.btnAction);
        btnExit = rootView.findViewById(R.id.btnExit);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).addLog("SecondFragment::btnAction");
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).addLog("SecondFragment::btnExit");
                ((MainActivity)getActivity()).selectMainFragment();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
