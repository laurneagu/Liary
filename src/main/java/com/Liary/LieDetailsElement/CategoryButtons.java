package com.Liary.LieDetailsElement;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/21/2015.
 */
public class CategoryButtons {
    private static Context m_context;
    public static RelativeLayout CreateView(final Context i_currContext, int i_rightOfId){
        m_context =i_currContext;
        RelativeLayout categ_relLayout = new RelativeLayout(i_currContext);

        categ_relLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

        //setting image position
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);
        params.height =   3 * GeneralData.buttonSizeHeight / 2;
        params.width = GeneralData.displaySizeWidth / 3;
        params.addRule(RelativeLayout.RIGHT_OF, i_rightOfId);
        categ_relLayout.setLayoutParams(params);

        categ_relLayout.setBackgroundColor(Color.GREEN);

        return  categ_relLayout;
    }


    public static void createButtons(RelativeLayout i_categLayout){

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);

        final Button[] buttons = new Button[3];

        String[] colorsHex = new String[]{"#66cd00", "#ffff00", "#ff0000"};
        String[] textButton = new String[]{"White lie", "Half-truth", "Fabrication"};

        for(int i = 0 ; i < 3 ; i++) {
            final Button button = new Button(m_context);
            button.setBackgroundColor(Color.parseColor(colorsHex[i]));

            params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);

            button.getLayoutParams().height = GeneralData.buttonSizeHeight / 2;
            button.getLayoutParams().width = GeneralData.displaySizeWidth / 3;

            button.setId(i+1);
            button.setText(textButton[i]);
            //button.setTextSize(GeneralData.buttonSizeHeight/10);
            button.setTypeface( Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont));
            button.setTextColor(Color.parseColor("#bdbdbd"));

            if(i > 0){
                params = (RelativeLayout.LayoutParams)(button.getLayoutParams());
                params.addRule(RelativeLayout.BELOW, buttons[i-1].getId());
                button.setLayoutParams(params);
            }

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    for(int i = 0 ; i < 3 ; i++){

                        if(buttons[i].equals(button)) {
                            GradientDrawable gd = new GradientDrawable();
                            gd.setColor(GeneralData.colorsInt[i]);
                            gd.setStroke(5, 0xFFFFFFFF);

                            GeneralData.m_butt_selected = i;
                            buttons[i].setBackgroundDrawable(gd);
                        }
                        else{
                            GradientDrawable gd = new GradientDrawable();
                            gd.setColor(GeneralData.colorsInt[i]);
                            gd.setStroke(5, GeneralData.colorsInt[i]);

                            buttons[i].setBackgroundDrawable(gd);
                        }
                    }
                }
            });

            buttons[i] = button;

            i_categLayout.addView(buttons[i]);
        }

    }
}
