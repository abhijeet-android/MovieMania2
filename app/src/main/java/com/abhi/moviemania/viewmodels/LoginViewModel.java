package com.abhi.moviemania.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abhi.moviemania.models.User;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> emailError = new MutableLiveData<>();
    public MutableLiveData<String> passwordError = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;


    public LoginViewModel() {

    }

    public void onSignInClicked() {
        emailError.setValue(null);
        passwordError.setValue(null);

        User user = new User(email.getValue(), password.getValue(),true);
        if (email.getValue() == null || email.getValue().isEmpty()) {
            emailError.setValue("Enter email address.");
            return;
        }

        if (!user.isEmailValid()) {
            emailError.setValue("Enter a valid email address.");
            return;
        }

        if (password.getValue() == null || password.getValue().isEmpty()) {
            passwordError.setValue("Enter password.");
            return;
        }

        if (!user.isPasswordLengthGreaterThan5()) {
            passwordError.setValue("Password Length should be greater than 5.");
            return;
        }

        userMutableLiveData.setValue(user);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count){
        User user = new User(email.getValue(), password.getValue(), false);
            userMutableLiveData.setValue(user);
    }


    public LiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }
}
