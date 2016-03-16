package com.Liary.CustomAlertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/18/2015.
 */
public class ExplainButtonAlertDialog extends DialogFragment {
    Context m_context;

    static int m_index_tapped;
    static String m_curr_explain;
    static Button m_curr_button;

    public static ExplainButtonAlertDialog newInstance(Button curr_button, int index_tapped, String curr_explain) {
        ExplainButtonAlertDialog f = new ExplainButtonAlertDialog();

        m_index_tapped = index_tapped;
        m_curr_explain = curr_explain;
        m_curr_button = curr_button;

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

        final View dialogView = inflater.inflate(R.layout.explain_button_dialog, null);

        Typeface font = Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont);

        TextView tv_explain_button = (TextView)dialogView.findViewById(R.id.text_explain);
        tv_explain_button.getLayoutParams().height = GeneralData.buttonSizeHeight/2 ;
        tv_explain_button.getLayoutParams().width =GeneralData.displaySizeWidth/3;
        tv_explain_button.setTypeface(font);
        tv_explain_button.setTextSize(GeneralData.buttonSizeHeight/20);
        if(m_curr_button.getText().toString() == GeneralData.submitText){
            tv_explain_button.setText(GeneralData.submitExplain);
        }
        else {
            tv_explain_button.setText(m_curr_explain);
        }
        builder.setView(dialogView);

        Button submit = (Button)dialogView.findViewById(R.id.submitForm);
        submit.getLayoutParams().height = GeneralData.buttonSizeHeight/3 ;
        submit.getLayoutParams().width =GeneralData.displaySizeWidth/3;
        submit.setTypeface(font);
        submit.setTextSize(GeneralData.buttonSizeHeight/15);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ExplainButtonAlertDialog.this.getDialog().cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setInverseBackgroundForced(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.y = GeneralData.buttonSizeHeight * m_index_tapped + GeneralData.buttonSizeHeight / 2;
        wmlp.x = GeneralData.displaySizeWidth /2;

        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}
