//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.myfavoritemovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner backgroundSpinner;
    private Spinner textColorSpinner;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Initialize views
        backgroundSpinner = findViewById(R.id.backgroundSpinner);
        textColorSpinner = findViewById(R.id.textColorSpinner);
        confirmButton = findViewById(R.id.confirmButton);

        // Set up background spinner
        ArrayAdapter<CharSequence> backgroundAdapter = ArrayAdapter.createFromResource(this, R.array.background_options, android.R.layout.simple_spinner_item);
        backgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(backgroundAdapter);

        // Set up text color spinner
        ArrayAdapter<CharSequence> textColorAdapter = ArrayAdapter.createFromResource(this, R.array.text_color_options, android.R.layout.simple_spinner_item);
        textColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textColorSpinner.setAdapter(textColorAdapter);

        // Set item selected listeners
        backgroundSpinner.setOnItemSelectedListener(this);
        textColorSpinner.setOnItemSelectedListener(this);

        // Set click listener for confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected background and text color values
                String selectedBackground = backgroundSpinner.getSelectedItem().toString();
                String selectedTextColor = textColorSpinner.getSelectedItem().toString();

                // Create intent to pass back the selected values to MainActivity
                Intent intent = new Intent();
                intent.putExtra("background", selectedBackground);
                intent.putExtra("textColor", selectedTextColor);

                // Set the result and finish the activity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Handle item selection if needed
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle case where nothing is selected if needed
    }
}