package com.example.adamsrestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.stetho.inspector.protocol.module.Database;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import com.facebook.stetho.Stetho;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    // For Facebook API - Currently not available for individual developers
    private TextView tv_userinfo;
    private ImageView iv_profilepic;
    private LoginButton fb_facebooklogin;
    Button register;
    Button login;

    DatabaseHelper myDB;

    //For Google API
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 1;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myDB.deleteDatabase(this);
        myDB = new DatabaseHelper(this);

         //Creating the Database and table Users
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m01_garlicbread);
//
//            myDB.insertDish("Starters", "Garlic Bread", "Slices of baguette covered with a garlic spread.",
//                    "0", "0", "0", "1", "1", 3.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m02_bruschetta);
//
//            myDB.insertDish("Starters", "Bruschetta", "Toasted bread with chopped tomatoes, onions, olives & seasoning.", "0", "0", "0", "1", "1", 3.50, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m03_mushroomsoup);
//
//            myDB.insertDish("Starters", "Mushroom Soup", "Fresh home-made mushroom soup with croutons.", "0", "0", "0", "0", "1", 6.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m04_cheeseplatter);
//
//            myDB.insertDish("Starters", "Cheese Platter", "A selection of cheeses including brie, cheddar, mozzarella & gorgonzola, grapes, walnuts & water biscuits (Shared between 2).", "0", "1", "1", "0", "1", 12.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m05_chickenwings);
//
//            myDB.insertDish("Starters", "Chicken wings", "A portion of chicken wings marinated in buffalo sauce  .", "1", "0", "0", "0", "0", 5.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m06_prawncocktail);
//
//            myDB.insertDish("Starters", "Prawn Cocktails", "A glass of prawns on a base of lettuce with a drizzle of lemon juice & mayonnaise.", "0", "1", "0", "0", "0", 4.50, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m07_springrolls);
//
//            myDB.insertDish("Starters", "Spring Rolls", "Crispy home-made spring rolls filled with tofu with a side of sweet & sour sauce.", "0", "0", "0", "1", "1", 4.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m08_icebergsalad);
//
//            myDB.insertDish("Salads", "Iceberg Salad", "Iceberg lettuce, onions, black olives, tomatoes, feta cheese & tomatoes.", "0", "1", "0", "0", "1", 7.80, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m09_chickensalad);
//
//            myDB.insertDish("Salads", "Chicken Caesar Salad", "Grilled chicken breast, lettuce, croutons & parmesan shavings.", "0", "0", "0", "0", "0", 9.20, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m10_tunasalad);
//
//            myDB.insertDish("Salads", "Tuna Salad", "Tuna chunks, lettuce, olives, cucumbers, cherry tomatoes, feta cheese & olive oil drizzle.", "0", "1", "0", "0", "0", 8.70, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m11_salmonsalad);
//
//            myDB.insertDish("Salads", "Smoked Salmon Salad", "Smoked salmon, rocket leaves, sundried tomatoes & seasoning", "0", "0", "0", "0", "0", 9.50, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m12_bolognaise);
//
//            myDB.insertDish("Pasta", "Spaghetti Bolognese", "Tomato sauce, 100% pure minced beef, onions, garlic & parsley.", "0", "0", "0", "1", "0", 7.50, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m13_tortellini);
//
//            myDB.insertDish("Pasta", "Tortellini Béchamel", "Fresh cream, mushrooms, onions, parsley & bacon.", "0", "1", "0", "1", "0", 7.60, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m14_tagliatelle);
//
//            myDB.insertDish("Pasta", "Tagliatelle Pesto", "Pesto, blue cheese, honey, walnuts & fresh cream.", "0", "1", "1", "1", "1", 8.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m15_lasagna);
//
//            myDB.insertDish("Pasta", "Lasagna", "Oven baked lasagna with Bolognese sauce, béchamel covered with melted cheese.", "0", "1", "0", "1", "0", 8.30, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m16_risotto);
//
//            myDB.insertDish("Pasta", "Risotto Marinara", "Mussel, shrimps, king prawns, white wine & parsley.", "0", "0", "0", "1", "0", 9.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m17_arrabiata);
//
//            myDB.insertDish("Pasta", "Pene Arrabiata", "Bell peppers, black pitted olives, baby plum tomatoes, chopped red chilli  & garlic.", "1", "0", "0", "1", "1", 7.90, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m18_margherita);
//
//            myDB.insertDish("Pizza", "Margherita", "Tomato sauce, mozzarella & basil.", "0", "1", "0", "1", "1", 6.50, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m19_capricciosa);
//
//            myDB.insertDish("Pizza", "Capricciosa", "Tomato sauce, mozzarella, ham, artichoke, mushrooms & black olives.", "0", "1", "0", "1", "1", 8.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m20_stagioni);
//
//            myDB.insertDish("Pizza", "Quattro Stagioni", "Tomato sauce, mozzarella, ham, mushrooms, peas, artichoke, eggs & olives.", "0", "1", "0", "1", "1", 8.30, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m21_diavola);
//
//            myDB.insertDish("Pizza", "Diavola", "Tomato sauce, mozzarella, jalapenos, spicy salami & red chilli.", "1", "1", "0", "1", "0", 7.70, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m22_formaggi);
//
//            myDB.insertDish("Pizza", "Quattro Formaggi", "Tomato sauce, mozzarella, brie, gorgonzola & goat cheese.", "0", "1", "0", "1", "1", 7.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m23_bbqchicken);
//
//            myDB.insertDish("Pizza", "BBQ Chicken", "Tomato sauce, mozzarella, chicken strips, red onions, bbq sauce & oregano.", "0", "1", "0", "1", "0", 8.90, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m24_funghi);
//
//            myDB.insertDish("Pizza", "Funghi", "Tomato sauce, mozzarella, wild mushrooms & ham.", "0", "1", "0", "1", "0", 7.20, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m25_calzone);
//
//            myDB.insertDish("Pizza", "Calzone", "Closed pizza with tomato sauce, mozzarella, ham & green pitted olives.", "0", "1", "0", "1", "0", 8.50, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m26_cheeseburger);
//
//            myDB.insertDish("Burgers", "Cheese Burger", "Burger, cheddar cheese, red onions, tomatoes, lettuce & pickles.", "0", "1", "0", "1", "0", 9.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m27_chickenburger);
//
//            myDB.insertDish("Burgers", "Chicken Burger", "Orange zest marinated chicken breast, red onions, tomatoes, lettuce, pickles & mayo.", "0", "0", "0", "1", "0", 9.70, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m28_jumboburger);
//
//            myDB.insertDish("Burgers", "Whiskey Jumbo Burger", "Burger, cheddar cheese, onion rings, bacon, pickles, red onion, tomatoes, lettuce & Jack Daniels\\’ sauce.", "0", "1", "0", "1", "0", 11.30, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m29_doubleburger);
//
//            myDB.insertDish("Burgers", "Double Burger", "2 Patties, cheddar cheese, mozzarella, bacon, lettuce & mustard.", "0", "1", "0", "1", "0", 11.60, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m30_specialburger);
//
//            myDB.insertDish("Burgers", "ADAM\'s Special", "100% Angus beef stuffed with melted cheese, caramelized onions, bacon, & secret sauce.", "1", "1", "0", "1", "0", 12.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m31_veggieburger);
//
//            myDB.insertDish("Burgers", "Veggie Burger", "Sweet potatoes & black beans burger, red onions, lettuce & corn.", "0", "0", "0", "1", "1", 10.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m32_nuggets);
//
//            myDB.insertDish("Kids\' Section", "Chicken Nuggets", "6 Home-made chicken nuggets with a side of French fries.", "0", "1", "0", "1", "0", 6.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m33_sausages);
//
//            myDB.insertDish("Kids\' Section", "Sausages", "Sausages with a side of French fries & baked beans.", "0", "0", "0", "1", "0", 6.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m34_pepperonipizza);
//
//            myDB.insertDish("Kids\' Section", "Pepperoni Pizza", "Tomato sauce, mozzarella, pepperoni & oregano.", "0", "1", "0", "1", "0", 6.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m35_fishfingers);
//
//            myDB.insertDish("Kids\' Section", "Fish Fingers", "6 Home-made fish fingers with a portion of French fries.", "0", "1", "0", "1", "0", 6.40, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m36_halfribs);
//
//            myDB.insertDish("Grill", "Half-rack BBQ Spare-ribs", "Slowly cooked to perfection, half a rack pork loin ribs with southern honey & BBQ sauce.", "0", "0", "0", "0", "0", 14.30, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m37_fullribs);
//
//            myDB.insertDish("Grill", "Full-rack BBQ Spare-ribs", "Slowly cooked to perfection, full rack pork loin ribs with southern honey & BBQ sauce.", "0", "0", "0", "0", "0", 19.20, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m38_chickenbreast);
//
//            myDB.insertDish("Grill", "Chicken Breast", "300g of flame grilled chicken breast finished with lemon zest.", "0", "0", "0", "0", "0", 17.10, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m39_ribeye);
//
//            myDB.insertDish("Grill", "Rib-eye Steak", "300g of fresh angus beef rib-eye flame-grilled to your liking. With a side of either mushroom or chimichurri sauce.", "0", "0", "0", "0", "0", 25.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m40_flanksteak);
//
//            myDB.insertDish("Grill", "Flank Steak", "250g of juicy flat cuts of beef. Served with béarnaise sauce.", "0", "0", "0", "0", "0", 22.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m41_brisket);
//
//            myDB.insertDish("Grill", "Brisket", "Slowly cooked for 16 hours in oak smoke covered in BBQ sauce.", "0", "0", "0", "0", "0", 21.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m42_salmon);
//
//            myDB.insertDish("Fish", "Salmon", "A 250g fresh salmon set on crispy asparagus, basil & herb dressing.", "0", "0", "0", "0", "0", 18.30, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m43_cod);
//
//            myDB.insertDish("Fish", "Cod", "250g cod marinated in honey sitting on baby spinach, mashed potato & seasoning.", "0", "1", "0", "1", "0", 17.90, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m44_swordfish);
//
//            myDB.insertDish("Fish", "Swordfish", "300g grilled swordfish topped with olive oil, parsley & lemon.", "0", "0", "0", "0", "0", 20.80, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m45_lobster);
//
//            myDB.insertDish("Fish", "Lobster", "A whole kilo of Live Maine, fresh from Atlantic. Served with a creamy béchamel sauce.", "0", "1", "0", "0", "0", 100.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m46_octopus);
//
//            myDB.insertDish("Fish", "Octopus & Calamari", "Slow cooked octopus and Calamari in garlic, fresh herbs & lemon.", "0", "0", "0", "0", "0", 17.20, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m47_icecream);
//
//            myDB.insertDish("Dessert", "Ice-cream", "3 scoops of ice-cream topped with whipped cream. Flavours: chocolate, vanilla, strawberry & pistachio.", "0", "1", "1", "1", "1", 5.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m48_lavacake);
//
//            myDB.insertDish("Dessert", "Chocolate Fondant", "A warm lava cake, filled with melted chocolate, garnished with powdered sugar,  raspberries and a scoop of vanilla ice-cream (Requires 15mins to prepare).", "0", "1", "0", "1", "1", 6.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m49_cheesecake);
//
//            myDB.insertDish("Dessert", "Stawberry Cheesecake", "New York Styled classic strawberry cheesecake.", "0", "1", "0", "1", "1", 6.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m50_lemon);
//
//            myDB.insertDish("Dessert", "Lemon Meringue", "2 mini lemon meringue tarts.", "0", "1", "0", "1", "1", 6.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m51_chocolatecake);
//
//            myDB.insertDish("Dessert", "Chocolate Cake", "Double chocolate cake with a layer of dark cocoa spread.", "0", "1", "0", "1", "1", 6.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m52_pancakes);
//
//            myDB.insertDish("Dessert", "Blueberry Pancake", "A stack of fluffy baked American style blueberry pancakes smothered with maple syrup.", "0", "1", "0", "1", "1", 6.00, bitmapToByte(bitmap), "0");
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

        Stetho.initializeWithDefaults(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        signInButton = findViewById(R.id.g_googlesignin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent , SIGN_IN);
            }
        });

        register = (Button) findViewById(R.id.btn_signup);
        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
                finish();
            }
        });

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogIn.class));
                finish();
            }
        });

        //iv_profilepic = findViewById(R.id.iv_profilepic);
        fb_facebooklogin = findViewById(R.id.fb_facebooklogin);

        callbackManager = CallbackManager.Factory.create();

        fb_facebooklogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tv_userinfo.setText("User Id:"+loginResult.getAccessToken().getUserId());
                String imageURL = "https://graph.facebook.com/"+ loginResult.getAccessToken().getUserId()+ "/picture?return_ssl_resources=1";
                Picasso.get().load(imageURL).into(iv_profilepic);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SIGN_IN:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

                if(result.isSuccess()){
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
        }


        //callbackManager.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
