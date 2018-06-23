package com.ngoc_vx.miwoklanguage;

import android.view.View;
import android.widget.Toast;

public class NumbersClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "You clicked Number List", Toast.LENGTH_LONG).show();
    }
}
