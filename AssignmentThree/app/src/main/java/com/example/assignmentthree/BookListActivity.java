package com.example.assignmentthree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class BookListActivity extends AppCompatActivity {

    private ArrayList<String> bookTitles;
    private boolean[] bookSelections;
    private double totalPrice = 0.0;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String category = extras.getString("category");
            bookTitles = getBookTitles(category);
            bookSelections = new boolean[bookTitles.size()];
            totalPriceTextView = findViewById(R.id.totalPriceTextView);
            Button seeCartButton = findViewById(R.id.seeCartButton);

            ListView bookListView = findViewById(R.id.bookListView);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    bookTitles
            );
            bookListView.setAdapter(adapter);

            bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    bookSelections[position] = !bookSelections[position];
                    totalPrice = calculateTotalPrice();
                    totalPriceTextView.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));

                    // Launch the DetailActivity when a book item is clicked
                    Intent intent = new Intent(BookListActivity.this, DetailActivity.class);
                    intent.putExtra("bookTitle", bookTitles.get(position));
                    startActivity(intent);
                }
            });

            seeCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implement the cart activity launch here
                }
            });
        }
    }

    private ArrayList<String> getBookTitles(String category) {
        ArrayList<String> bookTitles = new ArrayList<>();

        switch (category) {
            case "Travel":
                bookTitles.add("Nordic Islands: Iceland, Greenland, Norway, Faroe Islands");
                bookTitles.add("Elements: In Pursuit of the Wild");
                bookTitles.add("Seeing Silence: The Beauty of the World’s Most Quiet Places");
                bookTitles.add("Floating Luxury: The Most Luxurious Cruise Ships");
                break;
            case "Cookbooks":
                bookTitles.add("Mi Cocina: Recipes and Rapture from My Kitchen in Mexico");
                bookTitles.add("Mandy's Gourmet Salads: Recipes for Lettuce and Life");
                bookTitles.add("Korean American: Food That Tastes Like Home");
                bookTitles.add("Favorite Cakes: Showstopping Recipes for Every Occasion");
                break;
            case "Science and Technology":
                bookTitles.add("Java Coding Guidelines: 75 Recommendations for Reliable and Secure Programs");
                bookTitles.add("Why Things Bite Back: Technology and the Revenge of Unintended Consequences");
                bookTitles.add("The Distracted Mind: Ancient Brains in a High-Tech World");
                bookTitles.add("Ethics in Technology: A Philosophical Study");
                break;
            case "Children's Books":
                bookTitles.add("Good Night, Little Blue Truck");
                bookTitles.add("The Wonderful Things You Will Be");
                bookTitles.add("The Very Hungry Caterpillar");
                bookTitles.add("The Very Sleepy Bear");
                break;
        }

        return bookTitles;
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < bookSelections.length; i++) {
            if (bookSelections[i]) {
                // Implement the book price calculation here
                // Update the 'total' variable accordingly
            }
        }
        return total;
    }
}

