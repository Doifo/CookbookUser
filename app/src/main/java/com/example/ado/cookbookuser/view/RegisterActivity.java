package com.example.ado.cookbookuser.view;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ado.cookbookuser.R;
import com.example.ado.cookbookuser.model.User;
import com.example.ado.cookbookuser.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private String newUserName = null;
    private String newUserPassword = null;
    private String newUserPasswordCheck = null;

    private EditText editNewUserName;
    private EditText editNewUserPassword;
    private EditText editNewUserPasswordCheck;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this);
        editNewUserName = findViewById(R.id.edit_new_user_name);
        editNewUserPassword = findViewById(R.id.edit_new_user_password);
        editNewUserPasswordCheck =findViewById(R.id.edit_new_user_password_makeSure);

        Toolbar toolbarRegister = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbarRegister);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        editNewUserName.setText("");
        editNewUserPassword.setText("");
        editNewUserPasswordCheck.setText("");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_register:{
                newUserName = editNewUserName.getText().toString();
                newUserPassword = editNewUserPassword.getText().toString();
                newUserPasswordCheck = editNewUserPasswordCheck.getText().toString();

                if(!hasWrongInput(newUserName,newUserPassword,newUserPasswordCheck))
                    registerPresenter.userRegister(newUserName,newUserPassword,newUserPasswordCheck);
            }
            default:
                break;
        }
    }

    private boolean hasWrongInput(String name,String password,String passwordCheck){
        if(name.equals("") ){
            Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }else if(password.equals("")){
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }else if(passwordCheck.equals("")){
            Toast.makeText(RegisterActivity.this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void onEnterDifferentPassword(){
        Toast.makeText(RegisterActivity.this,"两次输入的密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
    }

    public void onExistUserName(){
        Toast.makeText(RegisterActivity.this,"该用户已存在",Toast.LENGTH_SHORT).show();
    }

    public void onRegisterSucceed(String name,String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setHeadShot("pic_default");
        user.save();
        Toast.makeText(RegisterActivity.this,"用户注册成功",Toast.LENGTH_SHORT).show();
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
