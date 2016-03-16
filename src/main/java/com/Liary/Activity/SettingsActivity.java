package com.Liary.Activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.Liary.DiaryManipulator.DiaryReader;
import com.Liary.DiaryManipulator.DiaryWriter;
import com.Liary.LeftSidePanel.LeftPanelItemClicker;
import com.Liary.R;
import com.Liary.Utils.GeneralData;


import java.io.IOException;

/**
 * Created by Laur on 2/5/2015.
 */
public class SettingsActivity extends Activity {
    // Left side panel
    private String[] mPanelElems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        int textSize = GeneralData.s_inches;

        // Initial data
        final RelativeLayout rlSetPassw = (RelativeLayout) findViewById(R.id.setPasswordLayout);
        rlSetPassw.setVisibility(View.INVISIBLE);

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

        // Left side panel
        mPanelElems = getResources().getStringArray(R.array.panel_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPanelElems));
        // Set the list's click listener
        LeftPanelItemClicker.OnItemClick(mDrawerList, getApplicationContext(),SettingsActivity.this);

        showPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        // Layout elements
        ImageView imgSettings = (ImageView)findViewById(R.id.imgSett);

        RelativeLayout.LayoutParams lieListParams =  (RelativeLayout.LayoutParams)imgSettings.getLayoutParams();
        lieListParams.topMargin = 2 * GeneralData.buttonSizeHeight / 3;
        lieListParams.height =  3 * GeneralData.buttonSizeHeight / 2;
        lieListParams.width = GeneralData.displaySizeWidth;
        imgSettings.setLayoutParams(lieListParams);

        com.Liary.Utils.CustomFontCreator tvSetPass = (com.Liary.Utils.CustomFontCreator)findViewById(R.id.tvSetPassword);
        tvSetPass.getLayoutParams().width = GeneralData.displaySizeWidth/5;
        com.Liary.Utils.CustomFontCreator tvReSetPass = (com.Liary.Utils.CustomFontCreator)findViewById(R.id.tvRewritePassword);
        tvReSetPass.getLayoutParams().width = GeneralData.displaySizeWidth/5;

        Switch passSwitch = (Switch)findViewById(R.id.switchPassword);
        passSwitch.setTypeface(Typeface.createFromAsset(getAssets(), GeneralData.customFont));
        passSwitch.setHighlightColor(GeneralData.buttonSizeHeight);
        passSwitch.setTextSize( 2 * textSize);

        // Set Switch state : true/false
        DiaryReader reader = new DiaryReader();
        String password = "";
        try {
            password = reader.Read(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(password.charAt(0) != '\u0000'){ // not empty password
            passSwitch.setChecked(true);
            makeSetPassRLVisible(rlSetPassw);
        }
        else{
            passSwitch.setChecked(false);
        }

        passSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(!isChecked){
                   try {
                       makeSetPassRLInvisible(rlSetPassw);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               else {
                   makeSetPassRLVisible(rlSetPassw);
               }
            }
        });

        final EditText passET = (EditText)findViewById(R.id.etPassw);
        final EditText passRewriteET = (EditText)findViewById(R.id.etPasswRewrite);

        final ImageButton submitPass = (ImageButton)findViewById(R.id.submitPass);
        submitPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String pass1 = passET.getText().toString();
                String pass2 = passRewriteET.getText().toString();

               if(pass1.compareTo(pass2) != 0  ) {
                   Toast.makeText(getApplicationContext(), "Passwords must match !", Toast.LENGTH_SHORT).show();
               }
                else{
                   if(pass1.length() < 3){
                       Toast.makeText(getApplicationContext(), "Passwords must have at least 3 characters !",
                                    Toast.LENGTH_SHORT).show();
                   }
                   else {
                       DiaryWriter writer = new DiaryWriter();
                       try {
                           writer.Write(getApplicationContext(), pass1);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }

                       Toast.makeText(getApplicationContext(), "Password saved !",
                               Toast.LENGTH_SHORT).show();
                   }
                 }
            }
        });
    }

    public void makeSetPassRLVisible(RelativeLayout i_setPassRL){
        i_setPassRL.getLayoutParams().height = GeneralData.buttonSizeHeight * 3 ;
        i_setPassRL.getLayoutParams().width = GeneralData.displaySizeWidth;
        i_setPassRL.setVisibility(View.VISIBLE);
    }

    public void makeSetPassRLInvisible(RelativeLayout i_setPassRL) throws IOException {
        i_setPassRL.getLayoutParams().height = 0;
        i_setPassRL.getLayoutParams().width = 0;
        i_setPassRL.setVisibility(View.INVISIBLE);

        DiaryWriter i_eraseFile = new DiaryWriter();
        i_eraseFile.EraseData(getApplicationContext());
    }
}
