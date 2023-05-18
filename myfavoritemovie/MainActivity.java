//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.myfavoritemovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView settingsButton;

    private static final int SETTING_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        textView = findViewById(R.id.textView);
        settingsButton = findViewById(R.id.imageView2);

        // Set click listener for settings button
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, SETTING_ACTIVITY_REQUEST_CODE);
            }
        });

        // Retrieve the saved theme
        SharedPreferences preferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        String savedBackground = preferences.getString("background", "Default");
        String savedTextColor = preferences.getString("textColor", "Dark");

        // Update the theme if a saved theme exists
        if (!savedBackground.equals("Default") || !savedTextColor.equals("Dark")) {
            updateTheme(savedBackground, savedTextColor);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String selectedBackground = data.getStringExtra("background");
                String selectedTextColor = data.getStringExtra("textColor");

                updateTheme(selectedBackground, selectedTextColor);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Update the theme when the configuration changes (e.g., orientation change)
        updateTheme(textView.getBackground().toString(), textView.getCurrentTextColor() == getResources().getColor(R.color.dark_text_color) ? "Dark" : "Light");
    }

    private void updateTheme(String background, String textColor) {
        // Save the selected theme
        SharedPreferences preferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (background != null) {
            editor.putString("background", background);
        }
        if (textColor != null) {
            editor.putString("textColor", textColor);
        }

        editor.apply();

        // Apply the saved theme
        LinearLayout layout = findViewById(R.id.layoutMain);
        TextView textView = findViewById(R.id.textView);
        TextView titleTextView = findViewById(R.id.titleTextView);

        if (layout != null) {
            if (background.equals("Default")) {
                // Set the default background image
                layout.setBackground(getResources().getDrawable(R.drawable.rotkbackground));
                layout.getBackground().setColorFilter(null);
            } else if (background.equals("Warm")) {
                // Apply warm background color with overlay
                int overlayColor = getResources().getColor(R.color.warm_overlay_color);
                layout.setBackground(getResources().getDrawable(R.drawable.rotkbackground));
                layout.getBackground().setColorFilter(overlayColor, PorterDuff.Mode.SRC_OVER);
            } else if (background.equals("Cold")) {
                // Apply cold background color with overlay
                int overlayColor = getResources().getColor(R.color.cold_overlay_color);
                layout.setBackground(getResources().getDrawable(R.drawable.rotkbackground));
                layout.getBackground().setColorFilter(overlayColor, PorterDuff.Mode.SRC_OVER);
            } else if (background.equals("Epic")) {
                // Apply epic background color with overlay
                int overlayColor = getResources().getColor(R.color.epic_overlay_color);
                layout.setBackground(getResources().getDrawable(R.drawable.rotkbackground));
                layout.getBackground().setColorFilter(overlayColor, PorterDuff.Mode.SRC_OVER);
            }
        }

        if (textView != null) {
            if (textColor.equals("Dark")) {
                // Set text color to dark
                textView.setTextColor(getResources().getColor(R.color.dark_text_color));
            } else if (textColor.equals("Light")) {
                // Set text color to light
                textView.setTextColor(getResources().getColor(R.color.light_text_color));
            }
        }

        if (titleTextView != null) {
            if (textColor.equals("Dark")) {
                // Set title text color to dark
                titleTextView.setTextColor(getResources().getColor(R.color.dark_text_color));
            } else if (textColor.equals("Light")) {
                // Set title text color to light
                titleTextView.setTextColor(getResources().getColor(R.color.light_text_color));
            }
        }
    }
}