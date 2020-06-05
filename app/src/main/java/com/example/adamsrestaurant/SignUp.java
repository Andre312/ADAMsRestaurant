package com.example.adamsrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.util.Log;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    TextInputLayout fullName, username, email, mobile, password, confirmPassword;
    CountryCodePicker mobileCCP;
    CheckBox terms;
    Button signUp;

    String nameInput, emailInput, usernameInput, mobileInput, passwordInput, passwordConfirmInput;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myDB = new DatabaseHelper(this);

        fullName = findViewById(R.id.text_input_fullname);
        username = findViewById(R.id.text_input_username);
        email = findViewById(R.id.text_input_email);
        mobile = findViewById(R.id.text_input_mobile);
        password = findViewById(R.id.text_input_password);
        confirmPassword = findViewById(R.id.text_input_password_confirm);
        mobileCCP = findViewById(R.id.countryCodePicker);
        mobileCCP.setCountryForNameCode("MT");
        terms = findViewById(R.id.cb_termsNConditions);
        signUp = findViewById(R.id.btn_signup);



    }


    public static final Pattern VALID_FULLNAME = Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
    private boolean validateName(){
        Boolean valid = true;
        nameInput = fullName.getEditText().getText().toString();
        Matcher matcher = VALID_FULLNAME.matcher(nameInput);

        if(nameInput.isEmpty()){
            fullName.setError("Field can't be empty");
            valid = false;
        }
        else if(matcher.find() == false){
            fullName.setError("Invalid Full Name format");
            valid = false;
        }
        else{
            fullName.setError(null);
        }
        return valid;
    }


    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private boolean validateEmail(){
        Boolean valid = true;
        emailInput = email.getEditText().getText().toString().toLowerCase();
        Matcher matcher = VALID_EMAIL.matcher(emailInput);
            Cursor result = myDB.checkEmail(emailInput);

        if(emailInput.isEmpty()){
            email.setError("Field can't be empty");
            valid = false;
        }
        else if(matcher.find() == false){
            email.setError("Invalid Email format");
            valid = false;
        }
        else if(result.getCount() > 0){
                // The email address has been taken;
            email.setError("Email already taken");
            valid = false;
        }
        else{
            email.setError(null);
        }

            //String currentEmail;
//            List<User> storedUsers = User.loadAllUsers();
//            //mId started counting from -1;
//            for (int i = 0; i < storedUsers.size(); i++) {
//                User myUser = storedUsers.get(i);
//                currentEmail = myUser.email;
//                if (currentEmail.equals(emailInput)) {
//                    email.setError("Email already taken");
//                    valid = false;
//                }
//                else{
//                    email.setError(null);
//                }
//            }

        return valid;
    }
    public static final Pattern VALID_USERNNAME = Pattern.compile("^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
    private boolean validateUsername(){
        Boolean valid = true;
        usernameInput = username.getEditText().getText().toString();
        Matcher matcher = VALID_USERNNAME.matcher(usernameInput);
        Cursor result = myDB.checkUsername(usernameInput);

        if(usernameInput.isEmpty()){
            username.setError("Field can't be Empty");
            valid = false;
        }
        else if(username.getEditText().getText().length() < 8){
            username.setError("Username must be at least 8 char");
            valid = false;
        }
        else if(username.getEditText().getText().length() > 20){
            username.setError("Username too long");
            valid = false;
        }
        else if(matcher.find() == false){
            username.setError("Invalid username format. Required [A-Za-z][0-9]");
        }
        else if(result.getCount() > 0){
            username.setError("Username already taken");
            valid = false;


//            String currentUsername;
//            List<User> storedUsers = User.loadAllUsers();
//            for (int i = 0; i < storedUsers.size(); i++) {
//                User myUser = storedUsers.get(i);
//                currentUsername = myUser.username;
//                if (currentUsername.equals(usernameInput)) {
//                    username.setError("Username already taken");
//                    valid = false;
//                }
//                else{
//                    username.setError(null);
//                }
//            }
        }
        else {
            username.setError(null);
        }
        return valid;
    }

    public static final Pattern VALID_MOBILE = Pattern.compile("^[0-9]+$");
    private boolean validateMobile(){
        Boolean valid = true;
        mobileInput = mobile.getEditText().getText().toString();
        Matcher matcher = VALID_MOBILE.matcher(mobileInput);
        if(mobileInput.isEmpty()){
            mobile.setError("Field can't be Empty");
            valid = false;
        }
        else if(matcher.find() == false){
            mobile.setError("Mobile No. should be numeric without the country prefix");
        }
        else{
            mobile.setError(null);
        }

        return valid;
    }

    public  static final Pattern VALID_PASSWORD = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    private Boolean validatePassword(){
        Boolean valid = true;
        passwordInput = password.getEditText().getText().toString();
        Matcher matcher = VALID_PASSWORD.matcher(passwordInput);
        if(passwordInput.isEmpty()){
            password.setError("Field can't be Empty");
            valid = false;
        }
        else if(password.getEditText().getText().length() < 8){
            password.setError("Password must be at least 8 characters long");
            valid = false;
        }
        else if(matcher.find() == false){
            password.setError("Password must be alphanumeric format");
            valid = false;
        }
        else{
            // Could not find a library with a built in hashing function
            password.setError(null);
        }
        return valid;
    }

    private Boolean validateConfirmPassword(){
        Boolean valid = true;
        passwordInput = password.getEditText().getText().toString();
        passwordConfirmInput = confirmPassword.getEditText().getText().toString();
        if(passwordConfirmInput.isEmpty()){
            confirmPassword.setError("Field can't be Empty");
            valid = false;
        }
        if(passwordInput.equals(passwordConfirmInput) != true){;
            confirmPassword.setError("Passwords do not match");
            valid = false;
        }
        else {
            confirmPassword.setError(null);
        }
        return valid;
    }

    public void btn_signUp_Clicked(View v){
    //Validate data entries and username availability
        if(!validateName() | !validateEmail() | !validateUsername() | !validateMobile() | !validatePassword() | !validateConfirmPassword()){
            return;
        }



        boolean userInserted = myDB.insertUser(nameInput, usernameInput, emailInput, mobileCCP.getSelectedCountryCode(), mobileInput, passwordInput);

        if(userInserted == true)
        {
            //Toast.makeText(SignUp.this, "User Inserted", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SignUp.this, HomePage.class));
            finish();
        }
        else{
            Toast.makeText(SignUp.this, "User Insertion Failed", Toast.LENGTH_LONG).show();
        }

//        ActiveAndroid.initialize(this);
//        User newUser = new User();
//        newUser.fullName = nameInput;
//        newUser.username = usernameInput;
//        newUser.email = emailInput;
//        newUser.mobilePrefix = mobileCCP.getSelectedCountryCodeAsInt();
//        newUser.mobile = Integer.parseInt(mobileInput);
//        newUser.Password = passwordInput;
//        newUser.save();


         //List<User> storedUsers = User.loadAllUsers();



    }
}
