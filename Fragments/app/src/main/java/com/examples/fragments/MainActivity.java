package com.examples.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mLoggerView;
    Fragment mMainFragment;
    Fragment mFirstFragment;
    Fragment mSecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // logger init
        mLoggerView = findViewById(R.id.loggerView);
        mLoggerView.setMovementMethod(new ScrollingMovementMethod());
        addLog("MainActivity::onCreate");

        // fragment init
        selectMainFragment();
    }

    public void addLog(String text) {
        text = '\n' + text;
        mLoggerView.append(text);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void selectMainFragment() {
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        setFragment(mMainFragment);
    }

    public void selectFirstFragment() {
        if (mFirstFragment == null) {
            mFirstFragment = new FirstFragment();
        }
        setFragment(mFirstFragment);
    }

    public void selectSecondFragment() {
        if (mSecondFragment == null) {
            mSecondFragment = new SecondFragment();
        }
        setFragment(mSecondFragment);
    }
}
