package com.example.vothanhtrung_shop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DetailNoAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_no_admin);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Extract the product details from the intent
        if(intent != null) {
            String productName = intent.getStringExtra("productName");
            String productImageURL = intent.getStringExtra("productImageURL");
            String productDescription = intent.getStringExtra("productDescription");
            double productPrice = intent.getDoubleExtra("pricedetail", 0); // Default value 0

            // Find views by their IDs
            TextView productNameTextView = findViewById(R.id.detailfoodname);
            ImageView productImageView = findViewById(R.id.imagedetail);
            TextView productDescriptionTextView = findViewById(R.id.detaildescription);
            TextView priceTextView = findViewById(R.id.detailPrice);

            // Set the product details to the corresponding views
            productNameTextView.setText(productName);
            productDescriptionTextView.setText(productDescription);
            priceTextView.setText(String.valueOf(productPrice)); // Convert double to String

            // Load product image using Picasso library
            if(productImageURL != null && !productImageURL.isEmpty()) {
                Picasso.get().load(ApiCaller.url + "/image/products/" + productImageURL).into(productImageView);
            }
        }

        // Set up the click listener for the home button
        ImageView buttonHome = findViewById(R.id.button_left_product);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the previous activity
                finish();
            }
        });
    }
}
