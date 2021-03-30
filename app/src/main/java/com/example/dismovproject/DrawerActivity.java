package com.example.dismovproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
    private NavigationView navigationView, secondMenu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        initViews();    //Calls back initViews function

        setSupportActionBar(toolbar);   //Initialize the toolbar

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }


    //Initialize the views, the side menu
    private void initViews() {
        Log.d(TAG, "initViews: started");
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_drawer);
        secondMenu = findViewById(R.id.menu_right);
        toolbar = findViewById(R.id.toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        secondMenu.setNavigationItemSelectedListener(this);
    }

    //Validates for any option inside the menu
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(), "Item 1", Toast.LENGTH_SHORT).show();
                System.out.println("si");
                break;

            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Item 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item3:
                Toast.makeText(getApplicationContext(), "Item 3", Toast.LENGTH_SHORT).show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
        }
        return false;
    }
}