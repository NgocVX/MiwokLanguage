package com.ngoc_vx.miwoklanguage.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ngoc_vx.miwoklanguage.R;
import com.ngoc_vx.miwoklanguage.adapter.WordAdapter;
import com.ngoc_vx.miwoklanguage.object.Word;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> families = new ArrayList<Word>();

            families.add(new Word("father", "әpә"));
            families.add(new Word("mother", "әṭa"));
            families.add(new Word("son", "angsi"));
            families.add(new Word("daughter", "tune"));
            families.add(new Word("older brother", "taachi"));
            families.add(new Word("younger brother", "chalitti"));
            families.add(new Word("older sister", "teṭe"));
            families.add(new Word("younger sister", "kolliti"));
            families.add(new Word("grandmother", "ama"));
            families.add(new Word("grandfather", "paapa"));

        WordAdapter itemsAdapter = new WordAdapter(this, R.layout.list_item, families);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }

}
