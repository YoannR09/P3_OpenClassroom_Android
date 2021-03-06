package com.example.mareu.ui.exception;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class DialogError {

    public DialogError(String message, View container) {
        Snackbar.make(container,
                message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.BLACK)
                .setTextColor(Color.WHITE)
    .show();
    }
}
