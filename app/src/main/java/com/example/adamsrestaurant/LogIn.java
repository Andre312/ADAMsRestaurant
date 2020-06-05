package com.example.adamsrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.activeandroid.util.Log;
import com.google.android.material.textfield.TextInputLayout;

public class LogIn extends AppCompatActivity {

    TextInputLayout email_username, password;
    String email_usernameInput, passwordInput;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        myDB = new DatabaseHelper(this);

        email_username = findViewById(R.id.text_input_username_email);
        password = findViewById(R.id.text_input_logpassword);



    }

    private boolean correctEmailUsername(){
        boolean valid = true;
        email_usernameInput = email_username.getEditText().getText().toString();
        Cursor resultEmail = myDB.checkEmail(email_usernameInput);
        Log.e("email", String.valueOf(resultEmail.getCount()));
        Cursor resultUsername = myDB.checkUsername(email_usernameInput);
        Log.e("username", String.valueOf(resultUsername.getCount()));
        if(resultEmail.getCount() == 0 && resultUsername.getCount() == 0)
        {
            email_username.setError("Inserted credential is not in the System");
            valid = false;
        }
        else{
            email_username.setError(null);
        }
       return valid;
    }

    private boolean correctPassword(){
        boolean valid = true;
        passwordInput = password.getEditText().getText().toString();
        Cursor resultmatch = myDB.checkLogIn(email_usernameInput, passwordInput);
        if(resultmatch.getCount() == 0){
            password.setError("Invalid Password");
            valid = false;
        }
        else{
            password.setError(null);
        }
        return valid;
    }

    public void btn_logIn_Clicked(View v){
        if(!correctEmailUsername() | !correctPassword()){
            return;
        }

        startActivity(new Intent(LogIn.this, HomePage.class));
        finish();
    }
}
