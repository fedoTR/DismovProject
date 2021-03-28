package com.example.dismovproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "DrawerActivity";

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        initViews();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    private void initViews() {
        Log.d(TAG, "initViews: started");
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        navigationView = (NavigationView)findViewById(R.id.navigation_drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(), "Item 1", Toast.LENGTH_SHORT).show();
                System.out.println("si");
                break;

            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Item 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item3:
                Toast.makeText(getApplicationContext(), "Item 1", Toast.LENGTH_SHORT).show();
                break;


        }
        return false;
    }
}