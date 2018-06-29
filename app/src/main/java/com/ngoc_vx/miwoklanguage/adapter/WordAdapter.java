package com.ngoc_vx.miwoklanguage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ngoc_vx.miwoklanguage.R;
import com.ngoc_vx.miwoklanguage.object.Word;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    private int colorResourceId;

    public WordAdapter(@NonNull Context context, int resource, @NonNull List<Word> objects, int colorResourceId) {
        super(context, resource, objects);
        this.colorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView miwokImageView = listItemView.findViewById(R.id.miwok_image);

        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            miwokImageView.setImageResource(currentWord.getImageResouceId());
            // Make sure the view is visible
            miwokImageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            miwokImageView.setVisibility(View.GONE);
        }

        // Find the TextView in the list_item.xml layout with the ID miwok_word
        TextView miwokWordTextView = listItemView.findViewById(R.id.miwok_word);
        // Get the miwok word from the current Word object and
        // set this text on the miwokWord TextView
        miwokWordTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_word
        TextView defaultTranslateTextView = listItemView.findViewById(R.id.default_word);
        // Get the default translate word from the current Word object and

        // set this text on the default translate TextView
        defaultTranslateTextView.setText(currentWord.getDefaultTranslation());

        // Set the theme color for the list item
        LinearLayout textContainer = listItemView.findViewById(R.id.text_container);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), colorResourceId);

        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
