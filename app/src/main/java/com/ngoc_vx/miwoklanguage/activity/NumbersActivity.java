package com.ngoc_vx.miwoklanguage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ngoc_vx.miwoklanguage.R;
import com.ngoc_vx.miwoklanguage.adapter.WordAdapter;
import com.ngoc_vx.miwoklanguage.object.Word;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> numbers = new ArrayList<Word>();
            numbers.add(new Word(R.drawable.number_one,"one", "lutti"));
            numbers.add(new Word(R.drawable.number_two,"two", "otiiko"));
            numbers.add(new Word(R.drawable.number_three,"three", "tolookosu"));
            numbers.add(new Word(R.drawable.number_four,"four", "oyyisa"));
            numbers.add(new Word(R.drawable.number_five,"five", "massokka"));
            numbers.add(new Word(R.drawable.number_six,"six", "temmokka"));
            numbers.add(new Word(R.drawable.number_seven,"seven", "kenekaku"));
            numbers.add(new Word(R.drawable.number_eight, "eight","kawinta"));
            numbers.add(new Word(R.drawable.number_nine, "nine","wo'e"));
            numbers.add(new Word(R.drawable.number_ten,"ten","na'aacha"));

        WordAdapter itemsAdapter = new WordAdapter(this, R.layout.list_item, numbers, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }
}
