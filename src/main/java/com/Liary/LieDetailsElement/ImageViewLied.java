package com.Liary.LieDetailsElement;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/21/2015.
 */
public class ImageViewLied {

    public static ImageView CreateView(Context i_currContext){
        //ImageView Setup
        ImageView imageView = new ImageView(i_currContext);
        //setting image resource
        imageView.setImageResource(R.drawable.target);

        //setting image position
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        imageView.setId(69);

        imageView.getLayoutParams().height =  3 * GeneralData.buttonSizeHeight / 2;
        imageView.getLayoutParams().width =  GeneralData.displaySizeWidth / 3;
        imageView.setPadding(0,0,0,0);

        return  imageView;
    }
}
