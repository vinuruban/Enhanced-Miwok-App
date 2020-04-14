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
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

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

        ArrayList<String> numbers = new ArrayList<String>();
        numbers.add("One") ;
        numbers.add("Two");
        numbers.add("Three");
        numbers.add("Four");
        numbers.add("Five");
        numbers.add("Six");
        numbers.add("Seven");
        numbers.add("Eight");
        numbers.add("Nine");
        numbers.add("Ten");


    //        No loop - prints number(0);

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//        TextView childView = new TextView(this); // creating a temporary child TextView . It's not made or displayed in the XML. It's simply a container to place text.
//        childView.setText(numbers.get(0));
//        rootView.addView(childView); // addView is a method that displays a view!


    //        Loop using FOR loop

        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        for (int i = 0; i < numbers.size(); i++) {
            TextView childView = new TextView(this); // creating a temporary child TextView . It's not made or displayed in the XML. It's simply a container to place text.
            childView.setText(numbers.get(i));
            rootView.addView(childView); // addView is a method that displays a view!
        }


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
