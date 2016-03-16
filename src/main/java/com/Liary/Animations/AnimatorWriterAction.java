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
public class AnimatorWriterAction extends Animation
    {
        private RelativeLayout m_layout;
        private EditText m_writeText;

        private Button m_blackList;
        private Button m_eraseData;

        private int m_initialHeight;
        private int m_actualHeight;

        private RelativeLayout m_currLieLayout;


        private Button m_tellLieButton;

        public AnimatorWriterAction(RelativeLayout i_layout, Button i_blackList, EditText i_writeText, Button i_eraseData,
                                    RelativeLayout i_currLieLayout, Button i_tellLieButton)
        {
            m_layout = i_layout;
            m_writeText = i_writeText;
            m_blackList = i_blackList;
            m_eraseData = i_eraseData;

            m_initialHeight = GeneralData.buttonSizeHeight;
            m_actualHeight = GeneralData.buttonSizeHeight;
            m_tellLieButton = i_tellLieButton;
            m_currLieLayout = i_currLieLayout;

            m_blackList.setHeight(0);

            m_writeText.setHeight(m_actualHeight);
            m_currLieLayout.getLayoutParams().height = m_actualHeight;


            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)i_eraseData.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, m_currLieLayout.getId());
            i_eraseData.setLayoutParams(params);

        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newHeight = m_actualHeight +  (int) (m_initialHeight / 2 * interpolatedTime);

            m_writeText.setHeight(newHeight);
            m_currLieLayout.getLayoutParams().height = newHeight;

            if(interpolatedTime == GeneralData.animDuration){
                m_eraseData.setBackgroundColor(Color.parseColor("#7e7e7e"));
                m_eraseData.setText(GeneralData.submitText);

                m_writeText.setEnabled(true);
                m_writeText.setFocusableInTouchMode(true);
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
