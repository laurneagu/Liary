package com.Liary.Animations;

import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/24/2015.
 */
public class AnimatorWriterActionRevertPanel2 extends Animation {

    private RelativeLayout m_layout;

    private ImageView m_imageView;
    private EditText m_personLied;
    private RelativeLayout m_categButtons;

    private Button m_blackList;
    private Button m_eraseData;

    private int m_initialHeight;
    private int m_actualHeight;

    private RelativeLayout m_curr_lie_layout;
    private EditText m_editText;

    public AnimatorWriterActionRevertPanel2(RelativeLayout i_layout, Button i_blackList, ImageView i_imageView, EditText i_personLied, RelativeLayout i_categButtons, Button i_eraseData, RelativeLayout i_curr_lie_layout, EditText i_editText)
    {
        m_layout = i_layout;
        m_imageView = i_imageView;
        m_personLied = i_personLied;
        m_categButtons = i_categButtons;

        m_blackList = i_blackList;
        m_eraseData = i_eraseData;

        m_initialHeight = GeneralData.buttonSizeHeight;
        m_actualHeight = m_imageView.getHeight();

        m_curr_lie_layout = i_curr_lie_layout;

        m_editText = i_editText;
    }
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight = m_actualHeight -  (int) (m_initialHeight / 2 * interpolatedTime);

        m_imageView.getLayoutParams().height = newHeight;
        m_personLied.setHeight(newHeight);
        m_categButtons.getLayoutParams().height = newHeight;

        m_curr_lie_layout.getLayoutParams().height = newHeight;
        //m_editText.getLayoutParams().height = newHeight;

        if(interpolatedTime == GeneralData.animDuration){
            m_imageView.getLayoutParams().height = 0;
            m_personLied.setHeight(0);
            m_categButtons.getLayoutParams().height = 0;

            m_curr_lie_layout.getLayoutParams().height = 0;
            m_blackList.setHeight(m_initialHeight);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)m_eraseData.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, m_blackList.getId());
            m_eraseData.setLayoutParams(params);

            m_eraseData.setBackgroundColor(Color.parseColor("#FF8800"));
            m_eraseData.setText("Erase all diary");
        }

        m_layout.requestLayout();

    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }


};

