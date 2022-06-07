package com.example.booklisting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();

    private static final String BOOK_DATA_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.fetchBookData(BOOK_DATA_REQUEST_URL);
    }
}