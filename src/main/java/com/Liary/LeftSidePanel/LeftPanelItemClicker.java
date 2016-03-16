package com.Liary.LeftSidePanel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.Liary.Activity.CreditsActivity;
import com.Liary.Activity.HelpActivity;
import com.Liary.Activity.SettingsActivity;
import com.Liary.MainActivity;

/**
 * Created by Laur on 2/5/2015.
 */
public class LeftPanelItemClicker {

    public static void OnItemClick(ListView i_Drawerlist, final Context i_context,final Activity i_currActivity){
        i_Drawerlist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
                        switch(position) {
                            case 0:
                                Intent homeIntent = new Intent(i_currActivity, MainActivity.class);
                                i_currActivity.startActivity(homeIntent);
                                break;
                            case 1:
                                Intent settingsIntent = new Intent(i_currActivity, SettingsActivity.class);
                                i_currActivity.startActivity(settingsIntent);
                                break;
                            case 2:
                                Intent helpIntent = new Intent(i_currActivity, HelpActivity.class);
                                i_currActivity.startActivity(helpIntent);
                                break;
                            case 3:
                                Intent creditsIntent = new Intent(i_currActivity, CreditsActivity.class);
                                i_currActivity.startActivity(creditsIntent);
                                break;
                        }
                    }});
    }
}