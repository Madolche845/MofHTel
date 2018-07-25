package com.example.crimson.mofhotel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.renderscript.Script;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crimson.mofhotel.model.User;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class login_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private  Button signin,signup;
    private EditText txtuser,txtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        signin = (Button)findViewById(R.id.btnsignin);
        signup = (Button)findViewById(R.id.btnsignup);
        txtuser = (MaterialEditText) findViewById(R.id.txtuser);
        txtpass = (MaterialEditText) findViewById(R.id.txtpass);

        //Init FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(login_activity.this,RegisterActivity.class);
                startActivity(myintent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(login_activity.this);
                mDialog.setMessage("Please waiting...!");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if user not exist in database
                        if(dataSnapshot.child(txtuser.getText().toString()).exists()) {
                            //Get User
                            mDialog.dismiss();
                            User user = dataSnapshot.child(txtuser.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(txtpass.getText().toString())) {
                                Toast.makeText(login_activity.this, "Sign in Successful!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(login_activity.this, "Wrong Password!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(login_activity.this,"User not exit in DataBase",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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
            Intent home = new Intent(login_activity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }
        if(id == R.id.login){
            Toast.makeText(this,"You on screen already!",Toast.LENGTH_LONG).show();

            Intent home = new Intent(login_activity.this, login_activity.class);
            startActivity(home);
            finish();

        }
        if(id == R.id.register){
            Intent myintent = new Intent(login_activity.this,RegisterActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.booking){
            Intent myintent = new Intent(login_activity.this,BookingActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.contact){

            Intent myintent = new Intent(login_activity.this,MapsActivity.class);
            startActivity(myintent);

            Intent map = new Intent(login_activity.this, MapsActivity.class);
            startActivity(map);
            finish();

        }
        return false;
    }
}
