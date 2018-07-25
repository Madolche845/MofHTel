package com.example.crimson.mofhotel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crimson.mofhotel.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    MaterialEditText txtusername,txtname,txtpass,txtsurname,txtbirth;
    Button signup;

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

        txtname = (MaterialEditText) findViewById(R.id.txtname);
        txtsurname = (MaterialEditText) findViewById(R.id.txtlname);
        txtusername = (MaterialEditText) findViewById(R.id.txtusername);
        txtpass = (MaterialEditText) findViewById(R.id.txtpass);
        txtbirth = (MaterialEditText) findViewById(R.id.txtbirth);
        signup = (Button) findViewById(R.id.btnsignup);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                mDialog.setMessage("Please waiting...!");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if user not exist in database
                        if(dataSnapshot.child(txtusername.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(RegisterActivity.this,"Username Aleady Register!",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            mDialog.dismiss();
                            User user = new User(txtname.getText().toString(),txtsurname.getText().toString(),txtpass.getText().toString(),txtbirth.getText().toString());
                            table_user.child(txtusername.getText().toString()).setValue(user);

                            Toast.makeText(RegisterActivity.this,"Register Successful!!",Toast.LENGTH_LONG).show();
                            finish();
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
            Intent myintent = new Intent(RegisterActivity.this,BookingActivity.class);
            startActivity(myintent);
        }
        if(id == R.id.contact){
            Intent myintent = new Intent(RegisterActivity.this,MapsActivity.class);
            startActivity(myintent);
        }
        return false;
    }
}
