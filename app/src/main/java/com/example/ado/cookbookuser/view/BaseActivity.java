package com.example.ado.cookbookuser.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ado.cookbookuser.model.User;
import com.example.ado.cookbookuser.model.UserForNow;

import org.litepal.crud.DataSupport;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public static User userForNow = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(userForNow == null){
            String userName = DataSupport.findAll(UserForNow.class).get(0).getUserName();
            List<User> users = DataSupport.findAll(User.class);
            for(User user:users){
                if (user.getName().equals(userName)) {
                    userForNow = user;
                    Toast.makeText(this,userForNow.getName(),Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }
}
