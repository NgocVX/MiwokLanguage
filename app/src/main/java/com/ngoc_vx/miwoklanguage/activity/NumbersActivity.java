package com.ngoc_vx.miwoklanguage.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ngoc_vx.miwoklanguage.R;
import com.ngoc_vx.miwoklanguage.adapter.WordAdapter;
import com.ngoc_vx.miwoklanguage.object.Word;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    // Handle audio focus when playing audio file
    private AudioManager mAudioManager;

    /**
     * This listener get trigger when media player has completed playing the audio file.
     */
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    /**
     * this listener get trigger whenever audio manager has audio focus change
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.v("AudioFocus-NumberAct: ", "AUDIOFOCUS_GAIN");
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.v("AudioFocus-NumberAct: ", "AUDIOFOCUS_LOSS_TRANSIENT");
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.v("AudioFocus-NumberAct: ", "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.v("AudioFocus-NumberAct: ", "AUDIOFOCUS_LOSS");
                    releaseMediaPlayer();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        Log.v("NumberActivity", "on create");

        // create and setup Audio Manager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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


                // Release the media player if it currently exits because we are about to
                // play a different sound file
                releaseMediaPlayer();


                int result = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // we have audio focus now

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, number.getAudioResourceId());


                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(completionListener);
                }

            }
        });
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("NumberActivity", "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("NumberActivity", "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("NumberActivity", "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("NumberActivity", "on stop");

        // when the activity is stoped, release the media player resource
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("NumberActivity", "on destroy");
    }
}
