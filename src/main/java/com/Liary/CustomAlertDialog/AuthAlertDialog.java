package com.Liary.CustomAlertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.Liary.MainActivity;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/13/2015.
 */
public class AuthAlertDialog extends DialogFragment {

    String m_pass;
    Context m_context;

    public static AuthAlertDialog newInstance(String i_pass) {
        AuthAlertDialog f = new AuthAlertDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("pass", i_pass);
        f.setArguments(args);

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

        m_pass = getArguments().getString("pass");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.auth_dialog, null);

        Typeface font = Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont);

        int width = GeneralData.s_width;
        int height = GeneralData.s_height;
        int textSize = GeneralData.s_inches * 2; //current_dimensions.height/55);

        Button cancel = (Button)dialogView.findViewById(R.id.cancelForm);
        cancel.getLayoutParams().height = height/8 ;
        cancel.getLayoutParams().width = width/3;
        cancel.setTypeface(font);
        cancel.setTextSize(textSize);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AuthAlertDialog.this.getDialog().cancel();
                System.exit(0);
            }
        });

        Button submit = (Button)dialogView.findViewById(R.id.submitForm);
        submit.getLayoutParams().height = height/8 ;
        submit.getLayoutParams().width = width/3;
        submit.setTypeface(font);
        submit.setTextSize(textSize);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText i_tvPass = (EditText) dialogView.findViewById(R.id.passwordAuth);
                String written_pass = i_tvPass.getText().toString();

                if (written_pass.compareTo(m_pass) == 0) {
                    AuthAlertDialog.this.getDialog().cancel();

                    Intent goToNextActivity = new Intent(m_context, MainActivity.class);
                    goToNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(goToNextActivity);

                } else {
                    Toast.makeText(m_context, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.setInverseBackgroundForced(true);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_BACK){
                    Toast.makeText(m_context, "Are you sure ? Please press 'Cancel' inside the form", Toast.LENGTH_SHORT ).show();
                    return true;
                }
                return false;
            }
        });

        return dialog;
    }

}


