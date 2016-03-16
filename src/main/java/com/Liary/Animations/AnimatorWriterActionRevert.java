package com.Liary.Animations;

import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/20/2015.
 */
public class AnimatorWriterActionRevert extends Animation {

    private RelativeLayout m_layout;
    private EditText m_writeText;

    private Button m_blackList;
    private Button m_eraseData;

    private int m_initialHeight;
    private int m_actualHeight;

    private RelativeLayout m_curr_lie_layout;

    public AnimatorWriterActionRevert(RelativeLayout i_layout, Button i_blackList, EditText i_writeText, Button i_eraseData, RelativeLayout i_curr_lie_layout)
    {
        m_layout = i_layout;
        m_writeText = i_writeText;

        m_blackList = i_blackList;
        m_eraseData = i_eraseData;

        m_initialHeight = GeneralData.buttonSizeHeight;
        m_actualHeight = m_writeText.getHeight();

        m_curr_lie_layout = i_curr_lie_layout;
     }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight = m_actualHeight -  (int) (m_initialHeight / 2 * interpolatedTime);

        m_writeText.setHeight(newHeight);
        m_curr_lie_layout.getLayoutParams().height = newHeight;

        if(interpolatedTime == GeneralData.animDuration){
            m_writeText.setHeight(0);
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
