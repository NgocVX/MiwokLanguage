package com.ngoc_vx.miwoklanguage.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ngoc_vx.miwoklanguage.R;
import com.ngoc_vx.miwoklanguage.adapter.WordAdapter;
import com.ngoc_vx.miwoklanguage.object.Word;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word(R.drawable.number_one, "one", "lutti", R.raw.number_one));
        numbers.add(new Word(R.drawable.number_two, "two", "otiiko", R.raw.number_two));
        numbers.add(new Word(R.drawable.number_three, "three", "tolookosu", R.raw.number_three));
        numbers.add(new Word(R.drawable.number_four, "four", "oyyisa", R.raw.number_four));
        numbers.add(new Word(R.drawable.number_five, "five", "massokka", R.raw.number_five));
        numbers.add(new Word(R.drawable.number_six, "six", "temmokka", R.raw.number_six));
        numbers.add(new Word(R.drawable.number_seven, "seven", "kenekaku", R.raw.number_seven));
        numbers.add(new Word(R.drawable.number_eight, "eight", "kawinta", R.raw.number_eight));
        numbers.add(new Word(R.drawable.number_nine, "nine", "wo'e", R.raw.number_nine));
        numbers.add(new Word(R.drawable.number_ten, "ten", "na'aacha", R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, R.layout.list_item, numbers, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(NumbersActivity.this, "Clicked item list numbers", Toast.LENGTH_SHORT).show();

                // Get the {@link Word} object at the given position the user clicked on
                Word number = numbers.get(position);
                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, number.getAudioResourceId());
                // Start the audio file
                mediaPlayer.start();
            }
        });
    }
}
