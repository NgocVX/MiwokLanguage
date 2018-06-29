package com.ngoc_vx.miwoklanguage.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ngoc_vx.miwoklanguage.R;
import com.ngoc_vx.miwoklanguage.adapter.WordAdapter;
import com.ngoc_vx.miwoklanguage.object.Word;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> colors = new ArrayList<Word>();
            colors.add(new Word(R.drawable.color_red, "red", "weṭeṭṭi", R.raw.color_red));
            colors.add(new Word(R.drawable.color_green, "green", "chokokki", R.raw.color_green));
            colors.add(new Word(R.drawable.color_brown, "brown", "ṭakaakki", R.raw.color_brown));
            colors.add(new Word(R.drawable.color_gray, "gray", "ṭopoppi", R.raw.color_gray));
            colors.add(new Word(R.drawable.color_black, "black", "kululli", R.raw.color_black));
            colors.add(new Word(R.drawable.color_white, "white", "kelelli", R.raw.color_white));
            colors.add(new Word(R.drawable.color_dusty_yellow, "dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow));
            colors.add(new Word(R.drawable.color_mustard_yellow, "mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, R.layout.list_item, colors, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }

}
