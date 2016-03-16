package com.Liary.CustomAlertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/19/2015.
 */
public class FirstEntryDialog3 extends DialogFragment {
    Context m_context;

    public static FirstEntryDialog3 newInstance() {
        FirstEntryDialog3 f = new FirstEntryDialog3();

        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        m_context=activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.first_entry_dialog3, null);

        Typeface font = Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont);

        int height = GeneralData.s_height;
        int width = GeneralData.s_width;
        int textSize = GeneralData.s_inches; //current_dimensions.height/55);

        Button submit = (Button)dialogView.findViewById(R.id.submitForm);
        submit.getLayoutParams().height = height/14 ;
        submit.getLayoutParams().width = width/3;
        submit.setTypeface(font);
        submit.setTextSize(textSize);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                FirstEntryDialog3.this.getDialog().dismiss();

                Toast.makeText(m_context, "Redirecting to set a password..", Toast.LENGTH_SHORT).show();

                Intent goToNextActivity = new Intent(m_context, com.Liary.Activity.SettingsActivity.class);
                startActivity(goToNextActivity);

            }
        });

        ImageView liary_pic = (ImageView)dialogView.findViewById(R.id.imageLogoDialog3);
        liary_pic.getLayoutParams().height = height/3 ;

        TextView welcome_message = (TextView)dialogView.findViewById(R.id.welcome_message);
        welcome_message.getLayoutParams().height = height/8 ;
        welcome_message.getLayoutParams().width = width;
        welcome_message.setText("Swipe left to see main panel !\n You can set a password on launch !\n There is also a help section for more details ! \n Enjoy !");
        welcome_message.setTypeface(font);
        welcome_message.setTextSize(textSize);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.y = 3 * GeneralData.buttonSizeHeight;
        wmlp.x = GeneralData.displaySizeWidth/2 ;

        dialog.setCanceledOnTouchOutside(false);
        dialog.setInverseBackgroundForced(true);

        return dialog;
    }
}
