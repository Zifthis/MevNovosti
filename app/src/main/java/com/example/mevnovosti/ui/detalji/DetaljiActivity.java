package com.example.mevnovosti.ui.detalji;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mevnovosti.R;

public class DetaljiActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji_layout);




        getIncomingIntent();
    }

    public void getIncomingIntent() {
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("name_url") && getIntent().hasExtra("opis_url") && getIntent().hasExtra("datum_url")) {

            String imageUrl = getIntent().getStringExtra("image_url");
            String nameUrl = getIntent().getStringExtra("name_url");
            String opisUrl = getIntent().getStringExtra("opis_url");
            String datumUrl = getIntent().getStringExtra("datum_url");


            setImage(imageUrl, nameUrl, opisUrl, datumUrl);
        }
    }

    private void setImage(String imageUrl, String nameUrl, String opisUrl, String datumUrl) {
        TextView naslov = findViewById(R.id.detalji_naslov);
        TextView opis = findViewById(R.id.detalji_opis);
        TextView datum = findViewById(R.id.detalji_datum);
        naslov.setText(nameUrl);
        opis.setText(opisUrl);
        datum.setText(datumUrl);


        ImageView image = (ImageView) findViewById(R.id.detalji_imageview);
        Glide.with(this)
                .asBitmap()
                .placeholder(R.drawable.mev_logo)
                .load(imageUrl)
                .into(image);
    }





}
