package com.ngoc_vx.miwoklanguage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ngoc_vx.miwoklanguage.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("MainActivity", "on create");

        // Find view that shows numbers category
        TextView numbers = findViewById(R.id.numbers);
        // set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        // Find view that shows family category
        TextView family = findViewById(R.id.family);
        // set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        // Find view that shows Colors category
        TextView colors = findViewById(R.id.colors);
        // set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        // Find view that shows phrases category
        TextView phrases = findViewById(R.id.phrases);
        // set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("MainActivity", "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MainActivity", "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MainActivity", "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MainActivity", "on destroy");
    }

}
