package com.abhi.moviemania.views;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abhi.moviemania.R;
import com.abhi.moviemania.databinding.ActivityLoginBinding;
import com.abhi.moviemania.models.User;
import com.abhi.moviemania.util.DaggerMyComponent;
import com.abhi.moviemania.util.MyComponent;
import com.abhi.moviemania.util.SharedPrefModule;
import com.abhi.moviemania.viewmodels.LoginViewModel;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;


public class LoginActivity extends AppCompatActivity {

    Button login_button;
    EditText email_editText,password_editText;

    private MyComponent myComponent;
    @Inject
    SharedPreferences sharedPreferences;

    ProgressDialog TempDialog;
    CountDownTimer CDT;
    int i =5;


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        init();

        myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        myComponent.inject(LoginActivity.this);

        loginViewModel.getUser().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {


                User user = (User) o;

                if (user != null) {
                    if (user.isPasswordLengthGreaterThan5() && user.isEmailValid()) {
                        login_button.setVisibility(View.VISIBLE);
                    } else {
                        login_button.setVisibility(View.INVISIBLE);
                    }

                    if (user.getFlag()) {

                        AlertDialog dialog = new SpotsDialog.Builder()
                                .setContext(LoginActivity.this)
                                .setTheme(R.style.Custom)
                                .build();
                        //dialog.setMessage("Loading");
                        dialog.show();




                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user.getEmail());
                        editor.apply();

                        //we can check api call and I have make a small use of dagger to save values in shared prefs..
                        loaderIfApi(dialog);


                    }
                }
            }
        });
    }

    private void loaderIfApi(AlertDialog alertDialog) {



        CDT = new CountDownTimer(5000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                i--;
                if(i==2){

                    // use of dagger
                    myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(LoginActivity.this)).build();
                    alertDialog.setMessage("Welcome "+ sharedPreferences.getString("username", "default"));
                }
            }

            public void onFinish()
            {

                //alertDialog.setTitle("Welcome "+ sharedPreferences.getString("username", "default"));

                alertDialog.dismiss();
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        }.start();

    }

    private void init() {
        login_button = findViewById(R.id.login_button);
        email_editText = findViewById(R.id.email_editText);
        password_editText = findViewById(R.id.password_editText);
    }
}
