package com.Liary.CustomAlertDialog;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/19/2015.
 */
public class FirstEntryDialog1 extends DialogFragment {
    Context m_context;
    static FragmentTransaction m_ft;

    public static FirstEntryDialog1 newInstance(FragmentTransaction ft) {
        FirstEntryDialog1 f = new FirstEntryDialog1();
        m_ft = ft;
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
        final View dialogView = inflater.inflate(R.layout.first_entry_dialog1, null);

        Typeface font = Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont);

        int height = GeneralData.s_height;
        int width = GeneralData.s_width;
        int textSize = GeneralData.s_inches * 2; //current_dimensions.height/40);

        Button submit = (Button)dialogView.findViewById(R.id.submitForm);
        submit.getLayoutParams().height = height/14 ;
        submit.getLayoutParams().width = width/3;
        submit.setTypeface(font);
        submit.setTextSize(textSize);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                FirstEntryDialog1.this.getDialog().dismiss();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }

                FirstEntryDialog2 newFragment = FirstEntryDialog2.newInstance();
                newFragment.show(ft, "dialog");
            }
        });

        ImageView wel_pic = (ImageView)dialogView.findViewById(R.id.headerPicPinnochio);
        wel_pic.getLayoutParams().height = height/3 ;

        TextView welcome_message = (TextView)dialogView.findViewById(R.id.welcome_message);
        welcome_message.getLayoutParams().height = height/14 ;
        welcome_message.getLayoutParams().width = width;
        welcome_message.setText("Welcome, liar !");
        welcome_message.setTypeface(font);
        welcome_message.setTextSize(textSize);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setInverseBackgroundForced(true);

        return dialog;
    }
}
