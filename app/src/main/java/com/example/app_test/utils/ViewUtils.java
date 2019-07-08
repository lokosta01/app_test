package com.example.app_test.utils;

import android.view.View;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

public class ViewUtils {

    public static void navigate(View view, NavDirections direction){
        Navigation.findNavController(view).navigate(direction);
    }
    public static void navigate(View view, NavDirections direction, FragmentNavigator.Extras extras){
        Navigation.findNavController(view).navigate(direction, extras);
    }
}

