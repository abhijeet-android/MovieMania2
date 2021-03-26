package com.abhi.moviemania.util;

import com.abhi.moviemania.views.LoginActivity;
import com.abhi.moviemania.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class})
public interface MyComponent {
    void inject(LoginActivity activity);
}