package com.tutorial.mvp.masterdetail;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.fragments.CardListFragment;

public class MasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        getSupportActionBar().setTitle(getString(R.string.master_activity_title));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CardListFragment cardListFragment = new CardListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.FRAGMENT_TYPE_KEY, AppConstants.FRAGMENT_TYPE_MASTER_LIST);
        cardListFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragmentContainer, cardListFragment, "MasterListFragment");
        fragmentTransaction.commit();
    }
}
