package com.Liary.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Laur on 2/5/2015.
 */
public class CustomFontCreator extends TextView {

    public CustomFontCreator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontCreator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontCreator(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "CONFN.TTF");
        setTypeface(tf);
    }

}