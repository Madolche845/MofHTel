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
import android.widget.Toast;

import android.content.Intent;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);
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
            Toast.makeText(this,"You on screen already!",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.login){
            Intent myintent = new Intent(HomeActivity.this,login_activity.class);
            startActivity(myintent);
        }
        if(id == R.id.register){
            Intent myintent = new Intent(HomeActivity.this,RegisterActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.booking){
            if(id == R.id.booking){
                Intent myintent = new Intent(HomeActivity.this,BookingActivity.class);
                startActivity(myintent);
            }
        }
        if(id == R.id.contact){
            Intent contact = new Intent(HomeActivity.this, ContactActivity.class);
            startActivity(contact);
            finish();
        }
        return false;
    }
    //Button Exit app.
    boolean twice;
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (twice == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(HomeActivity.this,"กดอีกครั้งเพื่อออก", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                twice = true;
            }
        },3000);
        twice = true;
    }

    public class MyTimerTask extends TimerTask{

        @Override
        public void run(){
            HomeActivity.this.runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}