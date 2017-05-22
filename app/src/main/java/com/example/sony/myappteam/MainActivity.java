package com.example.sony.myappteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //inisialisasi var
    EditText username, passwd;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi var
        username = (EditText) findViewById(R.id.usernameEt);
        passwd = (EditText) findViewById(R.id.passEt);
        login = (Button) findViewById(R.id.loginBtn);

    }

    public void post(View v) {
        String id, pass, url;
        id = username.getText().toString();
        pass = passwd.getText().toString();

        //alamat IP untuk parsing JSON
        url = "http://androtes1.000webhostapp.com/_getLoginUser.php?username=" + id + "&password=" + pass;

        new Getter(this, url, 0);
    }


}
