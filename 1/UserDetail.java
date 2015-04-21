package com.example.divya.asesqlite;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSubmit;
    EditText editTextUsername;
    EditText editTextPassword;
    private int _User_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnSubmit.setOnClickListener(this);




        _User_Id =0;
        Intent intent = getIntent();
        _User_Id =intent.getIntExtra("user_Id", 0);
        UserRepo repo = new UserRepo(this);
        User user = new User();
        user = repo.getUserById(_User_Id);


        editTextUsername.setText(user.username);
        editTextPassword.setText(user.password);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSubmit)){
            UserRepo repo = new UserRepo(this);
            User user = new User();
            user.username=editTextUsername.getText().toString();
            user.password=editTextPassword.getText().toString();


            if (_User_Id==0){
                _User_Id = repo.insert(user);

                Toast.makeText(this,"New User Insert",Toast.LENGTH_SHORT).show();
            }

        }

        else
        {
            finish();
        }


    }

}