package com.example.android.miwokenhanced;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.miwokenhanced.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            releaseMediaPlayer();
            Toast.makeText(getActivity(),"I'm done!",Toast.LENGTH_SHORT).show(); // "this" = NumbersFragment, so we changed it to getActivity() (which refers to NumberActivity)
        }
    };

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false); // NumbersActivity displays the activity_category.xml layout, and the NumbersFragment displays the activity_words.xml layout.

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE); //"getActivity()." added in addition to the code from NumbersActivity. BTW, getActivity() = NumbersActivity !!!!!!!!!!!!!!!

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("utti", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmoka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers); // "this" = NumbersFragment, so we changed it to getActivity() (which refers to NumberActivity)

        ListView listView = (ListView) rootView.findViewById(R.id.list); //"rootview." added in addition to the code from NumbersActivity

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();



                int result = audioManager.requestAudioFocus(afChangeListener,

                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getMediaResourceId()); // "this" = NumbersFragment, so we changed it to getActivity() (which refers to NumberActivity)
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(completeListener);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
