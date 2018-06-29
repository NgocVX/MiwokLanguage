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

            families.add(new Word(R.drawable.family_father, "father", "әpә"));
            families.add(new Word(R.drawable.family_mother, "mother", "әṭa"));
            families.add(new Word(R.drawable.family_son, "son", "angsi"));
            families.add(new Word(R.drawable.family_daughter, "daughter", "tune"));
            families.add(new Word(R.drawable.family_older_brother, "older brother", "taachi"));
            families.add(new Word(R.drawable.family_younger_brother, "younger brother", "chalitti"));
            families.add(new Word(R.drawable.family_older_sister, "older sister", "teṭe"));
            families.add(new Word(R.drawable.family_younger_sister, "younger sister", "kolliti"));
            families.add(new Word(R.drawable.family_grandmother, "grandmother", "ama"));
            families.add(new Word(R.drawable.family_grandfather, "grandfather", "paapa"));

        WordAdapter itemsAdapter = new WordAdapter(this, R.layout.list_item, families, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }

}
