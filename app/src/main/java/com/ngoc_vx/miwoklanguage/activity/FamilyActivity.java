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

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> families = new ArrayList<Word>();

        families.add(new Word(R.drawable.family_father, "father", "әpә", R.raw.family_father));
        families.add(new Word(R.drawable.family_mother, "mother", "әṭa", R.raw.family_mother));
        families.add(new Word(R.drawable.family_son, "son", "angsi", R.raw.family_son));
        families.add(new Word(R.drawable.family_daughter, "daughter", "tune", R.raw.family_daughter));
        families.add(new Word(R.drawable.family_older_brother, "older brother", "taachi", R.raw.family_older_brother));
        families.add(new Word(R.drawable.family_younger_brother, "younger brother", "chalitti", R.raw.family_younger_brother));
        families.add(new Word(R.drawable.family_older_sister, "older sister", "teṭe", R.raw.family_older_sister));
        families.add(new Word(R.drawable.family_younger_sister, "younger sister", "kolliti", R.raw.family_younger_sister));
        families.add(new Word(R.drawable.family_grandmother, "grandmother", "ama", R.raw.family_grandmother));
        families.add(new Word(R.drawable.family_grandfather, "grandfather", "paapa", R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, R.layout.list_item, families, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        // Set a click listener to play the audio when user click on list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(FamilyActivity.this, "Clicked item list numbers", Toast.LENGTH_SHORT).show();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = families.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());

                // Start the audio file
                mediaPlayer.start();
            }
        });
    }

}
