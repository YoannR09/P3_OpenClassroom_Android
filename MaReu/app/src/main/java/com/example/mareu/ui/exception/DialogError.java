package com.example.mareu.ui.exception;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.example.mareu.R;
import com.example.mareu.ui.AddMeetingActivity;
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
