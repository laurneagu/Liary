package com.Liary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.Liary.CustomAlertDialog.AuthAlertDialog;
import com.Liary.DiaryManipulator.DiaryReader;
import com.Liary.Utils.GeneralData;

import java.io.IOException;

/**
 * Created by Laur on 2/12/2015.
 */
public class InitActivity  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init);

        // Set screen values
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        int x = (int)Math.pow(dm.widthPixels/dm.xdpi,2);
        int y = (int)Math.pow(dm.heightPixels/dm.ydpi,2);
        int screenInches = 3 * (int)Math.round(Math.sqrt(x+y));

        setGlobalScreenValues(width,height,screenInches,x,y);

        // App Logo
        ImageView img_logo = (ImageView)findViewById(R.id.imageLogo);
        ViewGroup.LayoutParams lp = img_logo.getLayoutParams();
        lp.height = height/2;
        lp.width = width;
        ((ViewGroup.MarginLayoutParams)lp).topMargin = height/6;
        img_logo.setLayoutParams(lp);

        // Sticky Bits Logo
        ImageView img_logo_sticky = (ImageView)findViewById(R.id.imageLogoSticky);
        ViewGroup.LayoutParams lp2 = img_logo_sticky.getLayoutParams();
        lp2.height = height/4;
        lp2.width = width/4;
        ((ViewGroup.MarginLayoutParams)lp2).topMargin = 5 * height/6;
        img_logo_sticky.setLayoutParams(lp2);

            // Sticky Bits Created by
            TextView created_by_tv = (TextView)findViewById(R.id.createdby_textview);
            created_by_tv.setTextSize(screenInches);
            Typeface font = Typeface.createFromAsset(getAssets(), GeneralData.customFont);
            created_by_tv.setTypeface(font);
            ViewGroup.LayoutParams lp3 = created_by_tv.getLayoutParams();
            lp3.height = height/4;
            lp3.width = width/4;
            ((ViewGroup.MarginLayoutParams)lp3).topMargin = 5 * height/6;
            created_by_tv.setLayoutParams(lp3);


            // Ask password / Don't
            DiaryReader reader = new DiaryReader();
            String password = "";
            try {
                password = reader.Read(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }


        int indexFirstNull = password.indexOf('\u0000');
        password = password.substring(0,indexFirstNull);
        final String curr_passs = password;

        // Post delay current activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(curr_passs.compareTo("")!= 0) { // not empty password
                    // display auth form

                    //Toast.makeText(getApplicationContext(), "Current password is : " + old_pass, Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);

                    AuthAlertDialog newFragment = AuthAlertDialog.newInstance(curr_passs);
                    newFragment.show(ft, "dialog");
                }
                else{
                    // no pass set

                    //Toast.makeText(getApplicationContext(), "No password is set", Toast.LENGTH_SHORT).show();
                    Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToNextActivity);
                    InitActivity.this.finish();
                }

            }
        }, 2000);
    }

    private void setGlobalScreenValues(int width, int height, int screenInches,int x, int y){
        GeneralData.s_width = width;
        GeneralData.s_height = height;
        GeneralData.s_inches = screenInches;

        GeneralData.s_phyX = x;
        GeneralData.s_phyY = y;
    }

}
