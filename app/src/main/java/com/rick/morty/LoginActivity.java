package com.rick.morty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //tuscias langas
        setContentView(R.layout.activity_login); // ikrauna vaizda
        setTitle("Login");


        Button loginbatonas = findViewById(R.id.loginbatonas);//issitraukiam elementus
        final EditText usernametext = findViewById(R.id.usernametext);
        final EditText passwordtext = findViewById(R.id.passwordtext);

        final CheckBox rememberme = (CheckBox) findViewById(R.id.rememberMe);
        //bus konstruojamas vartotojo objektas perduoda info
        final User user=new User(LoginActivity.this);
        //patikriname, ar buvo pazymetas checkbox remember me
        rememberme.setChecked(user.isRememberedForLogin());

        //checkbox logika
        if (rememberme.isChecked()){
            usernametext.setText(user.getUsernameForLogin(),TextView.BufferType.EDITABLE);
            passwordtext.setText(user.getPasswordForLogin(),TextView.BufferType.EDITABLE);
        } else {
            usernametext.setText("",TextView.BufferType.EDITABLE);
            passwordtext.setText("", TextView.BufferType.EDITABLE);
        }

        loginbatonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validation.isValidUsername(usernametext.getText().toString()) && Validation.isValidPassword(passwordtext.getText().toString()))
                {
                    user.setUsernameForLogin(usernametext.getText().toString());
                    user.setPasswordForLogin(passwordtext.getText().toString());
                    if (rememberme.isChecked()){
                        user.setRemembermeKeyForLogin(true);
                    } else {
                        user.setRemembermeKeyForLogin(false);
                    }


                    Intent goToSearchActivity = new Intent(LoginActivity.this,//is
                            SearchActivity.class);//i
                    startActivity(goToSearchActivity);//cia ateina
                }
                else {
                    usernametext.setError(getResources().getString(R.string.login_invalid_username));
                    usernametext.requestFocus();
                }

            }
        });
    }

}
