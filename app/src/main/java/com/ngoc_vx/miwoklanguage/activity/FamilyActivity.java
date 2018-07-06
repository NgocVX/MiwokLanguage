package com.ngoc_vx.miwoklanguage.activity;

import android.content.Context;
import android.media.AudioManager;
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

    // Handles play audio files
    private MediaPlayer mMediaPlayer;

    // handles audio focus when playing the audio
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    // this listener get triggered when audio focus changes
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create and setup Audio Manager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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

                // Release the media player if it currently exists because we are about to
                // play difference sound file
                releaseMediaPlayer();

                // request audio focus
                int result = mAudioManager.requestAudioFocus(
                        onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // check state audio focus
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // when the activity is stopped, release the media player resource
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // release audio focus
            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

}
