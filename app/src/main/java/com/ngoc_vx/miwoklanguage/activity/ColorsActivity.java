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

public class ColorsActivity extends AppCompatActivity {

    // Handles playback of all audio file
    private MediaPlayer mMediaPlayer;

    // Handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    // this listener gets triggered when media play has completed playing sound file
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            // now that audio file has finished playing, release the media player resource
            releaseMediaPlayer();
        }
    };

    // This listener gets triggered when the media player has completed playing a audio file
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    mMediaPlayer.stop();
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

        final ArrayList<Word> colors = new ArrayList<Word>();
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ColorsActivity.this, "Clicked item list color", Toast.LENGTH_SHORT).show();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = colors.get(position);

                // Release the media player if it currently exists because we are about to
                // play difference sound file
                releaseMediaPlayer();

                // request audio focus so in oder to play audio file
                int result = mAudioManager.requestAudioFocus(
                        onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // now we have audio focus

                    // create and setup the media player for audio resource
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());

                    // play audio file
                    mMediaPlayer.start();

                    // release audio resource after play completed
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

            // release a audio focus
            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
