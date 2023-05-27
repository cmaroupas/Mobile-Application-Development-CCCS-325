package com.example.assignmentthree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private Button clearButton;
    private TextView totalTextView;

    private ArrayList<String> selectedItems;
    private ArrayList<Map<String, Object>> cartItems;
    private double totalPrice;

    private HashMap<String, Double> bookPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        clearButton = findViewById(R.id.clearButton);
        totalTextView = findViewById(R.id.totalTextView);

        selectedItems = getIntent().getStringArrayListExtra("selectedItems");
        bookPrices = (HashMap<String, Double>) getIntent().getSerializableExtra("bookPrices"); // Retrieve the bookPrices HashMap


        cartItems = createCartItems(selectedItems);

        bookPrices = createBookPrices();

        totalPrice = calculateTotalPrice(cartItems);
        totalTextView.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));

        String[] from = {"item", "quantity", "price", "total"};
        int[] to = {R.id.itemTextView, R.id.quantityTextView, R.id.priceTextView, R.id.totalTextView};

        SimpleAdapter adapter = new SimpleAdapter(this, cartItems, R.layout.cart_item_layout, from, to);
        cartListView.setAdapter(adapter);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectedItems();
                Toast.makeText(CartActivity.this, "Cart cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Map<String, Object>> createCartItems(ArrayList<String> selectedItems) {
        ArrayList<Map<String, Object>> cartItems = new ArrayList<>();

        for (String item : selectedItems) {
            Map<String, Object> cartItem = new HashMap<>();
            cartItem.put("item", item);
            cartItem.put("quantity", 1);
            double price = getBookPrice(item);
            cartItem.put("price", price);
            cartItem.put("total", price); // Initialize the total price

            cartItems.add(cartItem);
        }

        return cartItems;
    }

    private double calculateTotalPrice(ArrayList<Map<String, Object>> cartItems) {
        double totalPrice = 0.0;

        for (Map<String, Object> cartItem : cartItems) {
            double price = (double) cartItem.get("price");
            int quantity = (int) cartItem.get("quantity");
            double total = price * quantity;
            cartItem.put("total", total); // Update the total price for the item
            totalPrice += total;
        }

        return totalPrice;
    }

    private void clearSelectedItems() {
        cartItems.clear();
        totalPrice = 0.0;
        totalTextView.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));
    }

    private HashMap<String, Double> createBookPrices() {
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

    private double getBookPrice(String bookTitle) {
        return bookPrices.getOrDefault(bookTitle, 0.0);
    }
}
