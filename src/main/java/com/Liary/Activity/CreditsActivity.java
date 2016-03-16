package com.Liary.Activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.Liary.Adapter.CreditsAdapter;
import com.Liary.LeftSidePanel.LeftPanelItemClicker;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/13/2015.
 */
public class CreditsActivity  extends Activity {

    // Left side panel
    private String[] mPanelElems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        // Initialize main panel
        createListAdapter();

        TextView credits_to = (TextView)findViewById(R.id.credits_to_tv);
        credits_to.setTypeface( Typeface.createFromAsset(getAssets(), GeneralData.customFont));
        RelativeLayout.LayoutParams creditsParams =  (RelativeLayout.LayoutParams)credits_to.getLayoutParams();
        creditsParams.topMargin = GeneralData.buttonSizeHeight/2;
        creditsParams.width = GeneralData.displaySizeWidth;
        credits_to.setLayoutParams(creditsParams);

        final RelativeLayout headerMenu = (RelativeLayout) findViewById(R.id.headerMenu);
        headerMenu.getLayoutParams().height = GeneralData.buttonSizeHeight/2;
        headerMenu.getLayoutParams().width =GeneralData.displaySizeWidth;

        final ImageButton showPanel = (ImageButton)findViewById(R.id.showPanel);
        showPanel.setBackgroundResource(R.drawable.ic_drawer);
        showPanel.getLayoutParams().width = GeneralData.displaySizeWidth/8;
        showPanel.getLayoutParams().height = GeneralData.buttonSizeHeight/8;

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize( GeneralData.buttonSizeHeight / 10);
        textView.setHeight( GeneralData.buttonSizeHeight/2);
        textView.setWidth(GeneralData.displaySizeWidth);
        textView.setTypeface( Typeface.createFromAsset(getAssets(), GeneralData.customFont));

        // Left side panel
        mPanelElems = getResources().getStringArray(R.array.panel_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPanelElems));
        // Set the list's click listener
        LeftPanelItemClicker.OnItemClick(mDrawerList, getApplicationContext(), CreditsActivity.this);

        showPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    private void createListAdapter() {
        ListView m_CreditsList = (ListView)findViewById(R.id.creditsPeopleList);
        CreditsAdapter adapter = new CreditsAdapter(this, getAssets());

        m_CreditsList.setAdapter(adapter);
    }
}
