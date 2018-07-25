package com.example.crimson.mofhotel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ContactActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setTitle("Contact");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().show();

        textView = findViewById(R.id.text);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_recent:
                        textView.setText("");
                        return true;
                    case R.id.item_favorite:
                        Intent map = new Intent(ContactActivity.this, MapsActivity.class);
                        startActivity(map);
                    case R.id.item_nearby:
                        Toast.makeText(ContactActivity.this,"Calling...",Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.item_recent);
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(ContactActivity.this, HomeActivity.class);
        startActivity(main);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {

        int id = menuItem.getItemId();
        if(id == R.id.home){
            Intent home = new Intent(ContactActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }
        if(id == R.id.login){
            Intent myintent = new Intent(ContactActivity.this,login_activity.class);
            startActivity(myintent);
        }
        if(id == R.id.register){
            Intent home = new Intent(ContactActivity.this, RegisterActivity.class);
            startActivity(home);
        }
        if(id == R.id.booking){
            Intent home = new Intent(ContactActivity.this, BookingActivity.class);
            startActivity(home);
        }
        if(id == R.id.contact){
            Toast.makeText(this,"You on screen already!",Toast.LENGTH_LONG).show();
        }
        return false;
    }



}
