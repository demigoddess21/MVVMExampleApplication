package com.cals.mvvmexampleapplication.mvvm;

import android.content.Context;
import android.widget.Toast;

public class AppUtils {
    public static void showToast(Context context, String message) {
        if(message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
