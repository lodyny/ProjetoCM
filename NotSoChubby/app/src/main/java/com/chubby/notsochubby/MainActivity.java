package com.chubby.notsochubby;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.chubby.game.GameActivity;
import com.chubby.notsochubby.models.ChubbyApplication;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavigation;
    private NavigationView drawerNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
    }

    private void setupUI(){
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
        } else if(itemId == R.id.navigation_diet || itemId == R.id.nav_drawer_diet){
            bottomNavigation.getMenu().findItem(R.id.navigation_diet).setChecked(true);
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_diet).setChecked(true);
            fragment = MealsFragment.newInstance();
        } else if(itemId == R.id.nav_drawer_calendar || itemId ==  R.id.navigation_calendar){
            bottomNavigation.getMenu().findItem(R.id.navigation_calendar).setChecked(true);
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_calendar).setChecked(true);
            fragment = CalendarFragment.newInstance();
        } else if(itemId == R.id.nav_drawer_map || itemId == R.id.navigation_map){
            bottomNavigation.getMenu().findItem(R.id.navigation_map).setChecked(true);
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_map).setChecked(true);
            fragment = MapFragment.newInstance();
        } else if(itemId == R.id.nav_drawer_game || itemId == R.id.navigation_game){
            //bottomNavigation.getMenu().findItem(R.id.navigation_game).setChecked(true);
            //drawerNavigation.getMenu().findItem(R.id.nav_drawer_game).setChecked(true);
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
            return true;
        } else if(itemId == R.id.nav_drawer_news) {
            drawerNavigation.getMenu().findItem(R.id.nav_drawer_news).setChecked(true);
            fragment = NewsFragment.newInstance();
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
            finish();
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
