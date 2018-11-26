package com.example.ado.cookbookuser.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ado.cookbookuser.R;
import com.example.ado.cookbookuser.model.User;

import org.litepal.LitePal;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
View.OnClickListener
{

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myself = findViewById(R.id.myself);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_user_view);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //navigationView.setNavigationItemSelectedListener(this);
        LitePal.getDatabase();
        //DataSupport.deleteAll(User.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        User user = null;

        Button myself = findViewById(R.id.myself);
        FloatingActionButton fabUser = findViewById(R.id.fab_user);

        //登录状态
        if(BaseActivity.userForNow != null) {
            user = BaseActivity.userForNow;
            myself.setVisibility(View.GONE);
            fabUser.show();
            fabUser.setOnClickListener(this);

            user = BaseActivity.userForNow;
//
//            LayoutInflater factory = LayoutInflater.from(this);
//            View view= factory.inflate(R.layout.nav_header, null);
            ;
            CircleImageView circleImageView = navigationView.getHeaderView(0).findViewById(R.id.nav_user_headShot);
            TextView userName = navigationView.getHeaderView(0).findViewById(R.id.nav_user_name);


            //xiugai
            Glide.with(MainActivity.this).load(R.drawable.animal).into(circleImageView);
            userName.setText(user.getName());
        }
        else{
            fabUser.hide();
            RelativeLayout relativeLayout = findViewById(R.id.quit_user);
            relativeLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_user:{
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_myFavorite:{

            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            }
        }
        return true;
    }
}
