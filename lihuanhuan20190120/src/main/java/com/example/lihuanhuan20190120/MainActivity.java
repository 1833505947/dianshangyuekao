package com.example.lihuanhuan20190120;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lihuanhuan20190120.activity.Main2Activity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity{
    UMShareAPI umShareAPI;
    private EditText mEdName;
    private EditText mEdPwd;
    /**
     * 记住密码
     */
    private CheckBox mJi;
    /**
     * 登录
     */
    private Button mLogin;
    /**
     * 注册
     */
    private Button mReg;
    /**
     * qq第三方登录
     */
    private Button mQqlogin;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        umShareAPI = UMShareAPI.get(this);
        initView();

    }



    private void initView() {
        mEdName = (EditText) findViewById(R.id.ed_name);
        mEdPwd = (EditText) findViewById(R.id.ed_pwd);
        mJi = (CheckBox) findViewById(R.id.ji);
        mLogin = (Button) findViewById(R.id.login);
        mQqlogin = findViewById(R.id.qqlogin);
        mQqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Toast.makeText(MainActivity.this, "开始", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mJi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

               /* mEdName.setText(preferences.getString("name",""));
                mEdPwd.setText(preferences.getString("pwd",""));*/
            }
        });
        mReg = (Button) findViewById(R.id.reg);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                String name = preferences.getString("name", mEdName.getText().toString());

                String pwd = preferences.getString("pwd", mEdPwd.getText().toString());
                edit.commit();

                Toast.makeText(MainActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
       mReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,RegActivity.class);
               startActivity(intent);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
