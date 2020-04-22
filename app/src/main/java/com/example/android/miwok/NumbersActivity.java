/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.awt.font.NumericShaper;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer; //it's a global variable since media songs needs to be remembered

    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            releaseMediaPlayer(); // Cleans up the media player by releasing its resources.
            Toast.makeText(NumbersActivity.this,"I'm done!",Toast.LENGTH_SHORT).show();
        }
    }; //This same object was created and called repeatedly. Therefore, we set it as the global variable.

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) { //if temporary loss of audio or if temporary loss of audio with an option to lower volume
                        // IN OUR CASE, the sound should be paused if i pull down to dropdown menu, if i try playing a Miwok word during a call or whilst playing spotify
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0); //this sets the music back to second 0. Since our audio is short, it's okay to play from beginning if interrupted
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){ //if we've lost audio focus
                        releaseMediaPlayer(); //stop playback and cleanup resources
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

//        String [] numbers = new String[10];
//        numbers[0] = "One";
//        numbers[1] = "Two";
//        numbers[2] = "Three";
//        numbers[3] = "Four";
//        numbers[4] = "Five";
//        numbers[5] = "Six";
//        numbers[6] = "Seven";
//        numbers[7] = "Eight";
//        numbers[8] = "Nine";
//        numbers[9] = "Ten";


//        Previously, only one word was displayed per line. This is because when googling LAYOUT "simple_list_item_", we found out that it's XML file only had one TextView element.
//        We need 2 TextViews at a time to display. Thus, we created object instead of a single TextView to view at a time

//            Steps for below
//        1) Created list_view.xml that has two TextView elements to store Miwok and default lang words at a time - this was create cos "simple_list_item_" only had one TextView
//        2) Created a Word class that contains attributes (miwokTranslation and defaultTranslation), methods ( getDefaultTranslation() and getMiwokTranslation() ), and constructors to include miwok and default numbers!
//        3) Created 10 word objects
//        4) Created a WordAdapter that extends the from ArrayAdapter - the class has methods to get the following attributes: Miwok and default lang words. The class also has a constructor - the constructor is called to create object in 5)
//           ** this constructor is different to the constructor created in Word.class - this constructs from ArrayAdapter whereas Main.class doesn't extend from another class **
//        5) Created a WordAdapter object called "adapter"
//        6) Finally passed the adapter object into the ListView!

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); //instantiating audioManager object

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

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activityNumbers layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // set media player
        // OnItemClickListener since we are clicking the view, not a button!
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position); //stores the single word object that's clicked

                releaseMediaPlayer(); // the setOnCompletionListener below only gets called only if the song is completed. What if we interrupt a media while its playing and we click to play the next song? in that case, we can this method here and empty mediaPlayer!


        // Request audio focus for playback
                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC, //type of audio
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT); // how long to request audio for - our Miwok song is short hence why we picked AUDIOFOCUS_GAIN_TRANSIENT

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) { //this means we successfully gained audio focus
                    // Start playback
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getMediaResourceId()); //since we passed in the mediaLocation into the object and created a get method, we were able to get the resourceID of the word that was clicked here!
                    mediaPlayer.start();

                    // the method gets called when the media stops - it's an async callback
                    // every the below is called, the previously used to create the same object repeatedly. The method created within the object is generic too. Therefore, we set the object as a global variable and we will only call the variable here (completeListener)
                    // if we simply just call the releaseMediaPlayer() instead of passing the method into the below, media would have immediately stopped as soon as it starts to play. Hence the below.
                    mediaPlayer.setOnCompletionListener(completeListener);
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        releaseMediaPlayer();
        audioManager.abandonAudioFocus(afChangeListener); // Abandon audio focus when playback complete
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

}


//        The below only displayed one word per line. This is because when googling LAYOUT "simple_list_item_", we found out that it's XML file only had one TextView element. Also "ArrayAdapter<String>...numbers)" only allows one input

//        Below is the for View Recycling to prevent the long list above from taking up a lot of memory
//            No loop needed!
//             We need to add ListView and ArrayAdapter, so I've changed the View in activityNumbers to ListView
//            "simple_list_item_1" below is an android predefined layout file

//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(itemsAdapter);





//        USE ONE OF THE OPTIONS BELOW IF THE LIST IS SMALL AND DOESN'T REQUIRE VIEW RECYCLING


    //        No loop - prints number(0);

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//        TextView childView = new TextView(this); // creating a temporary child TextView . It's not made or displayed in the XML. It's simply a container to place text.
//        childView.setText(numbers.get(0));
//        rootView.addView(childView); // addView is a method that displays a view!


    //        Loop using FOR loop

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        for (int i = 0; i < numbers.size(); i++) {
//            TextView childView = new TextView(this); // creating a temporary child TextView . It's not made or displayed in the XML. It's simply a container to place text.
//            childView.setText(numbers.get(i));
//            rootView.addView(childView); // addView is a method that displays a view!
//        }


    //        Loop using While loop

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        int i = 0;
//        while (i < numbers.size()) {
//            TextView childView = new TextView(this); // creating a temporary child TextView . It's not made or displayed in the XML. It's simply a container to place text.
//            childView.setText(numbers.get(i));
//            rootView.addView(childView); // addView is a method that displays a view!
//            i++;
//        }

