package com.example.ado.cookbookuser.view;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ado.cookbookuser.R;
import com.example.ado.cookbookuser.model.User;

public class UserActivity extends BaseActivity implements View.OnClickListener{

    private User userForNowUserActivity;

    private Toolbar toolbarUser;
    private ImageView userHandShot;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private TextView header;
    private FloatingActionButton fabEditInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbarUser = findViewById(R.id.toolbar_user);
        userHandShot = findViewById(R.id.user_headShot);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = findViewById(R.id.appBar);
        header = findViewById(R.id.header);
        fabEditInformation = findViewById(R.id.fab_edit_information);

        setSupportActionBar(toolbarUser);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //collapsingToolbarLayout.setTitle(myself.getName());

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(Math.abs(i) >= appBarLayout.getTotalScrollRange()){
                    header.setVisibility(View.VISIBLE);
                }else{
                    header.setVisibility(View.GONE);
                }
            }
        });

        fabEditInformation.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //登录状态
        if(BaseActivity.userForNow != null){
            userForNowUserActivity = BaseActivity.userForNow;
            Glide.with(UserActivity.this).load(R.drawable.animal).into(userHandShot);
        }else{
            userForNowUserActivity = null;
            Glide.with(UserActivity.this).load(R.drawable.pic_default).into(userHandShot);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab_edit_information:{
                Intent intent = new Intent(UserActivity.this,EditUserActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_user,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
            case R.id.add:{

                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
