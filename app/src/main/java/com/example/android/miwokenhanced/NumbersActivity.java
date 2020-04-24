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
package com.example.android.miwokenhanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category); // NumbersActivity displays the activity_category.xml layout, and the NumbersFragment displays the activity_words.xml layout.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment()) //this installs the NumbersFragment into the NumberActivity. Change the NumbersFragment to something else and you will see the change of fragment!
                .commit();
    }
}

// This activity sets the activity_category XML layout resource as the content view.
// Then the NumbersFragment is created and inserted it into the container view (activity_category),
// using a FragmentTransaction (no need to understand the details of this now).
// Since the container has “match_parent” for width and height,
// the NumbersFragment will take up the whole width and height of the screen.