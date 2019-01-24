package com.chubby.notsochubby;

import android.content.Intent;
import android.os.Bundle;

import com.chubby.game.GameActivity;
import com.chubby.notsochubby.models.ChubbyApplication;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavigation;
    private NavigationView drawerNavigation;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
    }

    private void setupUI(){
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.vector_drawer_menu);
        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        drawerNavigation = findViewById(R.id.nav_view);
        drawerNavigation.setNavigationItemSelectedListener(this);
        View v = drawerNavigation.getHeaderView(0);
        TextView userNameView = v.findViewById(R.id.drawer_id);
        userNameView.setText(((ChubbyApplication) getApplication()).getUsername());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, NewsFragment.newInstance());
        fragmentTransaction.commit();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawers();
        Fragment fragment = null;
        int itemId = menuItem.getItemId();
        if(itemId == R.id.nav_drawer_exercise || itemId == R.id.navigation_exercise){
            bottomNavigation.getMenu().findItem(R.id.navigation_exercise).setChecked(true);
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_exercise).setChecked(true);
            fragment = GestorExercicioFragment.newInstance();
            toolbar.setTitle(getString(R.string.nav_exercice));
        } else if(itemId == R.id.navigation_diet || itemId == R.id.nav_drawer_diet){
            bottomNavigation.getMenu().findItem(R.id.navigation_diet).setChecked(true);
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_diet).setChecked(true);
            fragment = MealsFragment.newInstance();
            toolbar.setTitle(getString(R.string.nav_diet));
        } else if(itemId == R.id.nav_drawer_calendar || itemId ==  R.id.navigation_calendar){
            //toolbar.setTitle(getString(R.string.nav_calendar));
            Intent intent = new Intent(MainActivity.this, ScheduleReactActivity.class);
            startActivity(intent);
            return false;
        } else if(itemId == R.id.nav_drawer_map || itemId == R.id.navigation_map){
            bottomNavigation.getMenu().findItem(R.id.navigation_map).setChecked(true);
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_map).setChecked(true);
            fragment = MapFragment.newInstance();
            toolbar.setTitle(getString(R.string.nav_map));
        } else if(itemId == R.id.nav_drawer_game || itemId == R.id.navigation_game){
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
            return false;
        } else if(itemId == R.id.nav_drawer_news) {
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_news).setChecked(true);
            fragment = NewsFragment.newInstance();
            toolbar.setTitle(getString(R.string.nav_news));
        } else if(itemId == R.id.nav_drawer_profile) {
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_profile).setChecked(true);
            return true;
        } else if(itemId == R.id.nav_drawer_notifications) {
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_notifications).setChecked(true);
            return true;
        } else if(itemId == R.id.nav_drawer_config) {
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_config).setChecked(true);
            return true;
        } else if(itemId == R.id.nav_drawer_exit){
            finishAndRemoveTask();
            return true;
        } else {
            throw new RuntimeException("Must check all menu items.");
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        return true;
    }

}