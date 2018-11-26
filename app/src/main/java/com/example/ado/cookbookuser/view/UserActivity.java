package com.example.ado.cookbookuser.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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

    private User myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if(userForNow!= null) myself = BaseActivity.userForNow;

        Toolbar toolbarUser = findViewById(R.id.toolbar_user);
        ImageView userHandShot = findViewById(R.id.user_headShot);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        final TextView header = findViewById(R.id.header);

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
                }else if(i == 0){

                }else{
                    header.setVisibility(View.GONE);
                }
            }
        });

        if(myself.getHeadShot().equals("pic_default")){
            Glide.with(UserActivity.this).load(R.drawable.animal).into(userHandShot);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(userForNow!= null) myself = BaseActivity.userForNow;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
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
        }
        return super.onOptionsItemSelected(item);
    }
}
