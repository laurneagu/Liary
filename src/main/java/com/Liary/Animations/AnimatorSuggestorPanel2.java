package com.Liary.Animations;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/31/2015.
 */
public class AnimatorSuggestorPanel2 extends Animation {

    ImageView m_imgBack1, m_imgBack2;
    int m_initialRightMargin1, m_initialRightMargin2;

    public AnimatorSuggestorPanel2(ImageView i_imgBack1, ImageView i_imgBack2)
    {
        m_imgBack1 = i_imgBack1;
        m_imgBack2 = i_imgBack2;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) m_imgBack1.getLayoutParams();
        m_initialRightMargin1 = params.rightMargin;

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) m_imgBack2.getLayoutParams();
        m_initialRightMargin2 = params2.rightMargin;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) m_imgBack1.getLayoutParams();
        params.rightMargin += 2;
        m_imgBack1.setLayoutParams(params);

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) m_imgBack2.getLayoutParams();
        params2.rightMargin += 2;
        m_imgBack2.setLayoutParams(params2);

        if(interpolatedTime == GeneralData.animDuration) {
            m_imgBack1.setVisibility(View.INVISIBLE);
            m_imgBack2.setVisibility(View.INVISIBLE);

            params = (RelativeLayout.LayoutParams) m_imgBack1.getLayoutParams();
            params.rightMargin = m_initialRightMargin1;
            m_imgBack1.setLayoutParams(params);

            params2 = (RelativeLayout.LayoutParams) m_imgBack2.getLayoutParams();
            params2.rightMargin = m_initialRightMargin2 ;
            m_imgBack2.setLayoutParams(params2);
        }
    }
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
