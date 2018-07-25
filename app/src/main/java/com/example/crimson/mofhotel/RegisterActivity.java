package com.example.crimson.mofhotel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.home){
            Intent myintent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.login){
            Intent myintent = new Intent(RegisterActivity.this,login_activity.class);
            startActivity(myintent);
        }
        if(id == R.id.register){
            Intent myintent = new Intent(RegisterActivity.this,RegisterActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.booking){
            Toast.makeText(this,"This is Booking page",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.contact){
            Toast.makeText(this,"This is Contact page",Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
