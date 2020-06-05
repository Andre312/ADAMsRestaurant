package com.example.adamsrestaurant;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;
import java.util.List;

@Table(name = "Users")
public class User  extends Model {
    @Column(name = "FullName")
    public String fullName;

    @Column(name = "UserName")
    public String username;

    @Column(name = "Email")
    public String email;

    @Column(name = "mobilePrefix")
    public int mobilePrefix;

    @Column(name = "Mobile")
    public int mobile;

    @Column(name = "Password")
    public String Password;

    public static List<User>getUserwithEmail(String email){
        Log.d("User Email", email);
        return new Select().from(User.class).where("Email = ?", email).execute();

    }

    public static List<User>getUserwithUsername(String username){
        Log.d("Username", username);
        return new Select().from(User.class).where("Email = ?", username).execute();

    }

    public static List<User>loadAllUsers(){
        return new Select().from(User.class).execute();

    }

}
