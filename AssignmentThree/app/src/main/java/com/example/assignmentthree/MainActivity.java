package com.example.assignmentthree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Category 1: Travel
        LinearLayout travelLayout = findViewById(R.id.travelLayout);
        travelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBookListActivity("Travel");
            }
        });

        // Category 2: Cookbooks
        LinearLayout cookbooksLayout = findViewById(R.id.cookbooksLayout);
        cookbooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBookListActivity("Cookbooks");
            }
        });

        // Category 3: Science and Technology
        LinearLayout sciTechLayout = findViewById(R.id.sciTechLayout);
        sciTechLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBookListActivity("Science and Technology");
            }
        });

        // Category 4: Children's Books
        LinearLayout childrensBooksLayout = findViewById(R.id.childrensBooksLayout);
        childrensBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBookListActivity("Children's Books");
            }
        });
    }

    private void launchBookListActivity(String category) {
        Intent intent = new Intent(MainActivity.this, BookListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
    }
