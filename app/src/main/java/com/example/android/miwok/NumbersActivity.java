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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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


        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("utti", "one", R.drawable.number_one));
        words.add(new Word("otiiko", "two", R.drawable.number_two));
        words.add(new Word("tolookosu", "three", R.drawable.number_three));
        words.add(new Word("oyyisa", "four", R.drawable.number_four));
        words.add(new Word("massokka", "five", R.drawable.number_five));
        words.add(new Word("temmoka", "six", R.drawable.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.number_eight));
        words.add(new Word("wo'e", "nine", R.drawable.number_nine));
        words.add(new Word("na'aacha", "ten", R.drawable.number_ten));

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
    }
}
