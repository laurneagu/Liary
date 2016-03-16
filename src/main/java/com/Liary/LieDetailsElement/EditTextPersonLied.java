package com.Liary.LieDetailsElement;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/21/2015.
 */
public class EditTextPersonLied {

    public static EditText CreateView(Context i_currContext, int i_rightOfId){
        //ImageView Setup
        EditText editTextPersonLied = new EditText(i_currContext);

        //setting image position
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.height =   3 * GeneralData.buttonSizeHeight / 2;
        params.width = GeneralData.displaySizeWidth / 3;
        params.addRule(RelativeLayout.RIGHT_OF, i_rightOfId);
        editTextPersonLied.setLayoutParams(params);

        editTextPersonLied.setBackgroundColor(Color.parseColor("#FF4444"));
        editTextPersonLied.setTextColor(Color.parseColor("#FFFFFF"));
        editTextPersonLied.setHint("you lied to..");
        editTextPersonLied.setGravity(Gravity.CENTER);
        editTextPersonLied.setHintTextColor(Color.parseColor("#FFFFFF"));
        editTextPersonLied.setText("");

        editTextPersonLied.setEnabled(false);
        editTextPersonLied.setFocusable(false);

        editTextPersonLied.setId(70);

        return  editTextPersonLied;
    }
}
