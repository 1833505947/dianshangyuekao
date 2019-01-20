package com.example.lihuanhuan20190120;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity{

    private EditText mEdName;
    private EditText mEdPwd;
    private EditText mEdQrpwd;
    /**
     * 注册
     */
    private Button mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();

    }

    private void initData() {
        mReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("name",mEdName.getText().toString());
                edit.putString("pwd",mEdPwd.getText().toString());
                edit.putString("qrpwd",mEdQrpwd.getText().toString());
                edit.commit();
                if (mEdPwd.getText().toString().equals(mEdQrpwd.getText().toString())){
                    Toast.makeText(RegActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(RegActivity.this,"请确认两次密码相同!",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void initView() {
        mEdName = (EditText) findViewById(R.id.ed_name);
        mEdPwd = (EditText) findViewById(R.id.ed_pwd);
        mEdQrpwd = (EditText) findViewById(R.id.ed_qrpwd);
        mReg = (Button) findViewById(R.id.reg);

    }


}
