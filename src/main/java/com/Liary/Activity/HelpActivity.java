package com.Liary.Activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.Liary.LeftSidePanel.LeftPanelItemClicker;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/17/2015.
 */
public class HelpActivity extends Activity {

    // Left side panel
    private String[] mPanelElems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        int textSize = GeneralData.s_inches;

        TextView help_mess = (TextView)findViewById(R.id.help_mess_tv);
        help_mess.setTypeface(Typeface.createFromAsset(getAssets(), GeneralData.customFont));
        RelativeLayout.LayoutParams helpParams =  (RelativeLayout.LayoutParams)help_mess.getLayoutParams();
        helpParams.width = GeneralData.displaySizeWidth;
        helpParams.height = GeneralData.buttonSizeHeight/2;
        //helpParams.topMargin = GeneralData.buttonSizeHeight/2;
        help_mess.setLayoutParams(helpParams);

        ScrollView scroll_view = (ScrollView)findViewById(R.id.scroll_view);
        scroll_view.setPadding(0, GeneralData.buttonSizeHeight/2, 0 ,0);

        final RelativeLayout headerMenu = (RelativeLayout) findViewById(R.id.headerMenu);
        headerMenu.getLayoutParams().height = GeneralData.buttonSizeHeight/2;
        headerMenu.getLayoutParams().width =GeneralData.displaySizeWidth;

        final ImageButton showPanel = (ImageButton)findViewById(R.id.showPanelHelp);
        showPanel.setBackgroundResource(R.drawable.ic_drawer);
        showPanel.getLayoutParams().width = GeneralData.displaySizeWidth/8;
        showPanel.getLayoutParams().height = GeneralData.buttonSizeHeight/8;

        showPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize( GeneralData.buttonSizeHeight / 10);
        textView.setHeight( GeneralData.buttonSizeHeight/2);
        textView.setWidth(GeneralData.displaySizeWidth);
        textView.setTypeface( Typeface.createFromAsset(getAssets(), GeneralData.customFont));

        final TextView liary_general_tv = (TextView) findViewById(R.id.liary_general_tv);
        liary_general_tv.setTypeface( Typeface.createFromAsset(getAssets(), GeneralData.customFont));
        liary_general_tv.setTextSize( textSize);
        liary_general_tv.setText(getResources().getString(R.string.liary_motto));
        liary_general_tv.setHeight(GeneralData.buttonSizeHeight);
        liary_general_tv.setWidth(GeneralData.displaySizeWidth);

        final TextView how_to_use_tv = (TextView) findViewById(R.id.how_to_use_tv);
        how_to_use_tv.setTypeface( Typeface.createFromAsset(getAssets(), GeneralData.customFont));
        how_to_use_tv.setHeight(5* GeneralData.buttonSizeHeight/2);
        how_to_use_tv.setWidth(GeneralData.displaySizeWidth/2);
        how_to_use_tv.setTextSize( textSize);
        how_to_use_tv.setText(getResources().getString(R.string.how_to_use));

        final ImageView how_to_use_iv = (ImageView) findViewById(R.id.how_to_use_iv);
        how_to_use_iv.getLayoutParams().height = 5* GeneralData.buttonSizeHeight/2;
        how_to_use_iv.getLayoutParams().width = GeneralData.displaySizeWidth/2;

        final ImageView main_page_det_ic = (ImageView) findViewById(R.id.main_page_det_ic);
        main_page_det_ic.getLayoutParams().height = 5* GeneralData.buttonSizeHeight/2;
        main_page_det_ic.getLayoutParams().width = GeneralData.displaySizeWidth/2;

        final TextView main_page_det_tv = (TextView) findViewById(R.id.main_page_det_tv);
        main_page_det_tv.setTypeface( Typeface.createFromAsset(getAssets(), GeneralData.customFont));
        main_page_det_tv.setHeight(5* GeneralData.buttonSizeHeight/2);
        main_page_det_tv.setWidth(GeneralData.displaySizeWidth/2);
        main_page_det_tv.setTextSize( textSize);
        main_page_det_tv.setText(getResources().getString(R.string.main_page_det));

        // Left side panel
        mPanelElems = getResources().getStringArray(R.array.panel_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPanelElems));
        // Set the list's click listener
        LeftPanelItemClicker.OnItemClick(mDrawerList, getApplicationContext(), HelpActivity.this);
  }
}
