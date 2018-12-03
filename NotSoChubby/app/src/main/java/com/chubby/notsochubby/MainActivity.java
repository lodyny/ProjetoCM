package com.chubby.notsochubby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private NavigationView.OnNavigationItemSelectedListener mOnDrawerNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            Fragment fragment = null;
            menuItem.setChecked(true);
            switch (menuItem.getItemId()){
                case R.id.nav_drawer_diet:
                    ActionBar actionbar = getSupportActionBar();
                    actionbar.setTitle(R.string.nav_diet);
                    fragment = new GestorCaloriasFragment();break;
                case R.id.nav_drawer_exercise:
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setTitle(R.string.nav_exercice);
                    fragment = new GestorExercicioFragment();
            }
            if (fragment !=null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.fragment_container,fragment);
                ft.commit();
            }
            mDrawerLayout.closeDrawers();
            return true;
        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnBottomNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_exercise:
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setTitle(R.string.nav_exercice);
                    fragment = new GestorExercicioFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.fragment_container,fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_diet:
                    //mudança fragmento diet
                    return true;
                case R.id.navigation_calendar:
                    //mudança fragmento calendar
                    return true;
                case R.id.navigation_map:
                    //mudança fragmento map
                    return true;
                case R.id.navigation_game:
                    //mudança fragmento game
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.vector_drawer_menu);
        actionbar.setTitle(R.string.nav_news);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnBottomNavigationItemSelectedListener);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(mOnDrawerNavigationItemSelectedListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
