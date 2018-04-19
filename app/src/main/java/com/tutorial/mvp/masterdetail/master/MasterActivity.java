package com.tutorial.mvp.masterdetail.master;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tutorial.mvp.masterdetail.R;
import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.fragments.CardListFragment;

import butterknife.ButterKnife;

public class MasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getString(R.string.master_activity_title));

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CardListFragment cardListFragment = new CardListFragment();

        fragmentTransaction.add(R.id.fragmentContainer, cardListFragment, "MasterListFragment");
        fragmentTransaction.commit();*/
    }
}
