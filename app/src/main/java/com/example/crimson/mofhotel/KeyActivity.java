package com.example.crimson.mofhotel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.content.Intent;
import android.os.Handler;

public class KeyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    Button key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setTitle("Key");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        key = (Button) findViewById(R.id.btnkey);
        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KeyActivity.this,"Successful!!",Toast.LENGTH_LONG).show();
            }
        });
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
            Intent home = new Intent(KeyActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }
        if(id == R.id.login){
            Intent home = new Intent(KeyActivity.this, login_activity.class);
            startActivity(home);

        }
        if(id == R.id.register){
            Intent myintent = new Intent(KeyActivity.this,RegisterActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.key){
            Toast.makeText(this,"You on screen already!",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.booking){
            Intent myintent = new Intent(KeyActivity.this,BookingActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.contact){
            Intent myintent = new Intent(KeyActivity.this,ContactActivity.class);
            startActivity(myintent);

        }
        return false;
    }
}
