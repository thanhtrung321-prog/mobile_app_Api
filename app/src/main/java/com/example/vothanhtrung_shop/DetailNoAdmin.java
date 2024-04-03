package com.example.vothanhtrung_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailNoAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_no_admin);
        TextView textView = findViewById(R.id.listdetailnoadmin);
        compnentlistdetail component= new compnentlistdetail(textView);
        component.Oncreated();
    }
}