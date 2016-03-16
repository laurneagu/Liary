package com.Liary.CustomAlertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.Liary.Database.DatabaseManipulation;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 1/28/2015.
 */
public class EraseDiaryAlertDialog extends DialogFragment {

     Context m_context;

    public static EraseDiaryAlertDialog newInstance() {
        EraseDiaryAlertDialog f = new EraseDiaryAlertDialog();

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
        final View dialogView = inflater.inflate(R.layout.erase_diary_dialog, null);

        Typeface font = Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont);

        int height = GeneralData.s_height;
        int width = GeneralData.s_width;
        int textSize = GeneralData.s_inches * 2; //current_dimensions.height/55);

        Button cancel = (Button)dialogView.findViewById(R.id.cancelForm);
        cancel.getLayoutParams().height = height/8 ;
        cancel.getLayoutParams().width = width/3;
        cancel.setTypeface(font);
        cancel.setTextSize(textSize);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EraseDiaryAlertDialog.this.getDialog().cancel();

            }
        });

        Button submit = (Button)dialogView.findViewById(R.id.submitForm);
        submit.getLayoutParams().height = height/8 ;
        submit.getLayoutParams().width = width/3;
        submit.setTypeface(font);
        submit.setTextSize(textSize);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                    EraseDiaryAlertDialog.this.getDialog().cancel();
                    // User clicked OK button -- delete all the lies
                    DatabaseManipulation.DeleteLieTableEntries();
            }
        });

        ImageView erase_pic = (ImageView)dialogView.findViewById(R.id.headerEraseDiaryPic);
        erase_pic.getLayoutParams().height = height/6 ;

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setInverseBackgroundForced(true);

        return dialog;
    }
}

