package com.example.ado.cookbookuser.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ado.cookbookuser.R;
import com.example.ado.cookbookuser.model.User;
import com.example.ado.cookbookuser.model.UserForNow;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
View.OnClickListener
{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View navLayout;
    private Toolbar toolbarMain;
    private Button myself;
    private TextView quitUser;
    private FloatingActionButton fabUser;
    private CircleImageView circleImageView;
    private TextView userName;
    private Menu navMenu;

    private static final String TAG = "MainActivity";

    private User userForNowMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myself = findViewById(R.id.myself);
        quitUser = findViewById(R.id.quit_user);
        toolbarMain = findViewById(R.id.toolbar_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_user_view);
        fabUser = findViewById(R.id.fab_user);
        navLayout = navigationView.getHeaderView(0);
        circleImageView = navLayout.findViewById(R.id.nav_user_headShot);
        userName = navLayout.findViewById(R.id.nav_user_name);
        navMenu = navigationView.getMenu();

        setSupportActionBar(toolbarMain);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        myself.setOnClickListener(this);
        quitUser.setOnClickListener(this);
        fabUser.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawerLayout.closeDrawers();

        //登录状态
        if(BaseActivity.userForNow != null) {
            userForNowMainActivity = BaseActivity.userForNow;

            myself.setVisibility(View.GONE);
            quitUser.setVisibility(View.VISIBLE);
            fabUser.show();

            Glide.with(MainActivity.this).load(R.drawable.animal).into(circleImageView);
            userName.setText(userForNowMainActivity.getName());
        }
        else{
            userForNowMainActivity = null;

            myself.setVisibility(View.VISIBLE);
            quitUser.setVisibility(View.GONE);
            fabUser.hide();

            Glide.with(MainActivity.this).load(R.drawable.pic_default).into(circleImageView);
            userName.setText("未登录");
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
            case R.id.myself:{
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            }
            case R.id.quit_user:{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("退出当前帐号");
                builder.setMessage("您确定要退出吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataSupport.deleteAll(UserForNow.class);
                        drawerLayout.closeDrawers();
                        MainActivity.this.onStart();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
            }
            case R.id.nav_user_headShot:{
                if(BaseActivity.userForNow == null){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent);
                }
                break;
            }
            default:
                break;
        }
    }

    //menu onclick
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_myFavorite:{

            }
            case R.id.nav_myCookbook:{

            }
            case R.id.nav_setting:{

            }
            case R.id.nav_switch:{

            }
            case R.id.nav_exit:{

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
