package com.Liary.Activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.Liary.Adapter.BlackListAdapter;
import com.Liary.Database.DatabaseManipulation;
import com.Liary.Database.Lie;
import com.Liary.LeftSidePanel.LeftPanelItemClicker;
import com.Liary.R;
import com.Liary.Utils.GeneralData;
import com.Liary.Utils.HashtableSorter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Laur on 2/5/2015.
 */
public class BlackListActivity  extends Activity {

    // Left side panel
    private String[] mPanelElems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacklist);

        ArrayList<Lie> m_lies = DatabaseManipulation.GetLies();
        createListAdapter(m_lies);

        // Initialize main panel
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

        final ListView m_LieList = (ListView)findViewById(R.id.blacklielist);
        RelativeLayout.LayoutParams lieListParams =  (RelativeLayout.LayoutParams)m_LieList.getLayoutParams();
        lieListParams.topMargin = GeneralData.buttonSizeHeight/2;
        m_LieList.setLayoutParams(lieListParams);

        // Left side panel
        mPanelElems = getResources().getStringArray(R.array.panel_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPanelElems));
        // Set the list's click listener
        LeftPanelItemClicker.OnItemClick(mDrawerList, getApplicationContext(), BlackListActivity.this);

        showPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    private void createListAdapter(final ArrayList<Lie> i_lies) {

        ListView m_BlackLieList = (ListView)findViewById(R.id.blacklielist);
        Hashtable<String, Integer> blackList = new Hashtable<String, Integer>();

        for(Lie lie : i_lies){
            String personLied = lie.getPersonLied();
            if(!blackList.containsKey(personLied)){
                blackList.put(personLied, 1);
            }
            else{
                blackList.put(personLied, blackList.get(personLied) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedBlackList = HashtableSorter.sortValue(blackList);

        BlackListAdapter adapter = new BlackListAdapter(this, sortedBlackList, getAssets());
        m_BlackLieList.setAdapter(adapter);
    }
}
