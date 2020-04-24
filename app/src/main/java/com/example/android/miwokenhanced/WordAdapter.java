package com.example.android.miwokenhanced;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colourID;

    public WordAdapter(Activity context, ArrayList<Word> words, int colourResourceID) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        //here is where we call the super-class and the "concrete" class //this is actually from the super-class (ArrayAdapter) and here we are constructing using the default input param of 3 // resource = 0 cos we are inflating the resource in getView() method instead
        //this constructor is different to the constructor created in Word.class - this constructs from ArrayAdapter whereas Main.class doesn't extend from another class
        colourID = colourResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_textView);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_textView);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.imageView);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView

        // set visibilty OFF if PhraseActivity calls using its own constructor
        if (currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getImageResourceId());
        }
        else {
            iconView.setVisibility(View.GONE);
        }

        // sets the background colour depending on which activity i clicked on
        LinearLayout textContainer = (LinearLayout) listItemView.findViewById(R.id.textContainer);
        int colour = ContextCompat.getColor(getContext(), colourID); //converts colour resource id into colour value. Also getContext() is the activity that is called into the adapter, ie. NumberActivity
        textContainer.setBackgroundColor(colour);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
