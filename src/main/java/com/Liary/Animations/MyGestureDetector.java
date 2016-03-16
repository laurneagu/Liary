package com.Liary.Animations;

/**
 * Created by Laur on 1/21/2015.
 */

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.Liary.LieDetailsElement.CategoryButtons;
import com.Liary.Utils.GeneralData;


public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private Context m_currContext;
    private RelativeLayout m_currLayout;
    private View.OnTouchListener m_listener;

    // first panel
    EditText m_curr_lie_text;

    // second panel
    ImageView m_imageView;
    EditText m_personLied;
    RelativeLayout m_categButtons;

    int m_initialHeightP1, m_initialHeightP2;
    int m_initialWidthP1, m_initialWidthP2;

    boolean firstEnter = true;

    public MyGestureDetector(Context i_currContext, RelativeLayout i_currLayout, EditText i_currLie,  View.OnTouchListener i_listener,
                               ImageView i_imageView, EditText i_editText, RelativeLayout i_categButtons){
        m_currContext = i_currContext;
        m_currLayout = i_currLayout;
        m_listener = i_listener;

        // first panel
        m_curr_lie_text = i_currLie;

        // second panel
        m_imageView = i_imageView;
        m_personLied = i_editText;
        m_categButtons = i_categButtons;

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                swipeRightToLeft();
            }
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                swipeLeftToRight();
            }
        } catch (Exception e) {
            // nothing
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    public void resetDimensions(View v, int width, int height){
        v.getLayoutParams().height = height;
        v.getLayoutParams().width = width;
    }

    public synchronized void swipeLeftToRight(){
        // panel you entered
        GeneralData.panelNr = 1;

        resetDimensions(m_imageView, 0,0);
        resetDimensions(m_personLied,0,0);
        m_personLied.setEnabled(false);
        m_personLied.setFocusable(false);
        resetDimensions(m_categButtons,0,0);

        m_curr_lie_text.setHeight(m_initialHeightP1);
        m_curr_lie_text.setWidth(m_initialWidthP1);
        m_curr_lie_text.setEnabled(true);
        m_curr_lie_text.setFocusableInTouchMode(true);
    }

    public synchronized void swipeRightToLeft(){
         // panel you entered
        GeneralData.panelNr = 2;

        m_curr_lie_text.setHeight(0);
        m_curr_lie_text.setWidth(0);
        //m_curr_lie_text.setEnabled(false);
        m_curr_lie_text.setFocusable(false);

        //adding view to layout -- first enter
        if(firstEnter == true) {
            m_initialHeightP1 = m_curr_lie_text.getHeight();
            m_initialWidthP1 = m_curr_lie_text.getWidth();

            m_currLayout.addView(m_imageView);
            m_currLayout.addView(m_personLied);
            m_currLayout.addView(m_categButtons);
            CategoryButtons.createButtons(m_categButtons);

            m_initialHeightP2 = 3 * GeneralData.buttonSizeHeight / 2;
            m_initialWidthP2 =  GeneralData.displaySizeWidth / 3;

            firstEnter = false;
        }

        // after first enter
        resetDimensions(m_imageView, m_initialWidthP2, m_initialHeightP2);
        resetDimensions(m_personLied, m_initialWidthP2, m_initialHeightP2);

        resetDimensions(m_categButtons, m_initialWidthP2, m_initialHeightP2);

        m_personLied.setEnabled(true);
        m_personLied.setFocusableInTouchMode(true);

    }
}
