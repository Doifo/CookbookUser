package com.example.ado.cookbookuser.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ado.cookbookuser.R;
import com.example.ado.cookbookuser.model.User;
import com.example.ado.cookbookuser.model.UserForNow;
import com.example.ado.cookbookuser.presenter.LoginPresenter;

import org.litepal.crud.DataSupport;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar toolbarLogin;
    private EditText editUserName;
    private EditText editUserPassword;
    private Button btnLogin;
    private TextView btnRegister;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);

        toolbarLogin = findViewById(R.id.toolbar_login);
        editUserName = findViewById(R.id.edit_user_name);
        editUserPassword = findViewById(R.id.edit_user_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register_user);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        setSupportActionBar(toolbarLogin);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        editUserPassword.setText("");
        editUserName.setText("");
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                String userName = editUserName.getText().toString();
                String userPassword = editUserPassword.getText().toString();

                if(!hasWrongInput(userName,userPassword))
                    loginPresenter.userLogin(userName,userPassword);
                break;
            }
            case R.id.btn_register_user:{
                //Toast.makeText(LoginActivity.this,"Register",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

    private boolean hasWrongInput(String name,String password){
        if(name.equals("") ){
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }else if(password.equals("")){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void onNotExistUserName(){
        Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
    }

    public void onWrongPassword(){
        Toast.makeText(LoginActivity.this,"密码错误，请检查后重新输入",Toast.LENGTH_SHORT).show();
    }

    public void onLoginSucceed(User user){

        DataSupport.deleteAll(UserForNow.class);
        UserForNow userForNow = new UserForNow();
        userForNow.setUserName(user.getName());
        userForNow.save();

        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        finish();
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
