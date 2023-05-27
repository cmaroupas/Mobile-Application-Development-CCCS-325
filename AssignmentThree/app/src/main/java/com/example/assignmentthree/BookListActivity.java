package com.example.assignmentthree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class BookListActivity extends AppCompatActivity {

    private ArrayList<String> bookTitles;
    private boolean[] bookSelections;

    private String selectedBookTitle;
    private HashMap<String, Integer> bookQuantities;
    private HashMap<String, Double> bookPrices;
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
            bookQuantities = new HashMap<>();
            bookPrices = createDummyBookPrices();
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

                    selectedBookTitle = bookTitles.get(position);
                    Intent intent = new Intent(BookListActivity.this, DetailActivity.class);
                    intent.putExtra("bookTitle", selectedBookTitle);
                    startActivityForResult(intent, 1);
                }
            });

            seeCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Launch the CartActivity
                    launchCartActivityIntent();
                }
            });
        }
    }

    private void launchCartActivityIntent() {
        ArrayList<String> selectedBooks = getSelectedBooks();

        if (selectedBooks.isEmpty()) {
            Toast.makeText(this, "No books selected", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, CartActivity.class);
        intent.putStringArrayListExtra("selectedItems", selectedBooks);
        intent.putExtra("bookPrices", bookPrices); // Pass the bookPrices HashMap
        startActivityForResult(intent, 2);
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
                String bookTitle = bookTitles.get(i);
                int quantity = bookQuantities.getOrDefault(bookTitle, 0);
                double price = getBookPrice(bookTitle);
                double subtotal = quantity * price;
                total += subtotal;
            }
        }
        return total;
    }

    private double getBookPrice(String bookTitle) {
        return bookPrices.getOrDefault(bookTitle, 0.0);
    }

    private HashMap<String, Double> createDummyBookPrices() {
        HashMap<String, Double> bookPrices = new HashMap<>();
        bookPrices.put("Nordic Islands: Iceland, Greenland, Norway, Faroe Islands", 103.40);
        bookPrices.put("Elements: In Pursuit of the Wild", 65.00);
        bookPrices.put("Seeing Silence: The Beauty of the World’s Most Quiet Places", 29.32);
        bookPrices.put("Floating Luxury: The Most Luxurious Cruise Ships", 84.24);
        bookPrices.put("Mi Cocina: Recipes and Rapture from My Kitchen in Mexico", 37.30);
        bookPrices.put("Mandy's Gourmet Salads: Recipes for Lettuce and Life", 35.00);
        bookPrices.put("Korean American: Food That Tastes Like Home", 30.07);
        bookPrices.put("Favorite Cakes: Showstopping Recipes for Every Occasion", 24.95);
        bookPrices.put("Java Coding Guidelines: 75 Recommendations for Reliable and Secure Programs", 48.58);
        bookPrices.put("Why Things Bite Back: Technology and the Revenge of Unintended Consequences", 22.50);
        bookPrices.put("The Distracted Mind: Ancient Brains in a High-Tech World", 23.95);
        bookPrices.put("Ethics in Technology: A Philosophical Study", 64.99);
        bookPrices.put("Good Night, Little Blue Truck", 16.51);
        bookPrices.put("The Wonderful Things You Will Be", 21.00);
        bookPrices.put("The Very Hungry Caterpillar", 11.88);
        bookPrices.put("The Very Sleepy Bear", 9.99);
        return bookPrices;
    }

    private ArrayList<String> getSelectedBooks() {
        ArrayList<String> selectedBooks = new ArrayList<>();
        for (int i = 0; i < bookSelections.length; i++) {
            if (bookSelections[i]) {
                selectedBooks.add(bookTitles.get(i));
            }
        }
        return selectedBooks;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {  // From DetailActivity
                double itemTotalPrice = data.getDoubleExtra("itemTotalPrice", 0.0);
                int quantity = data.getIntExtra("quantity", 0);

                bookQuantities.put(selectedBookTitle, quantity);
                totalPrice = calculateTotalPrice();
                totalPriceTextView.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));
            } else if (requestCode == 2) {  // From CartActivity
                if (data != null) {
                    boolean clearCart = data.getBooleanExtra("clearCart", false);
                    if (clearCart) {
                        bookQuantities.clear();
                        totalPrice = calculateTotalPrice();
                        totalPriceTextView.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));
                    }
                }
            }
        }
    }
}
