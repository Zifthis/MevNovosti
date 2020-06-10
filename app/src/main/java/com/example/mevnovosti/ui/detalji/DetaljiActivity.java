package com.example.mevnovosti.ui.detalji;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mevnovosti.R;

public class DetaljiActivity extends AppCompatActivity {

    private static final String TAG = "DetaljiActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji_layout);
        Log.d(TAG, "onCreate: started");

        getIncomingIntent();
    }

    public void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for any intents.");
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("name_url")){
            Log.d(TAG, "getIncomingIntent: have found extras");

            String imageUrl = getIntent().getStringExtra("image_url");
            String nameUrl = getIntent().getStringExtra("name_url");

            setImage(imageUrl,nameUrl);
        }
    }

    private void setImage(String imageUrl, String nameUrl){
        TextView naslov = findViewById(R.id.detalji_opis);
        naslov.setText(nameUrl);

        ImageView image = (ImageView)findViewById(R.id.detalji_imageview);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }

}
