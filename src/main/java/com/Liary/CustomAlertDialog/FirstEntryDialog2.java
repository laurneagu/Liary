package com.Liary.CustomAlertDialog;

import android.app.*;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

/**
 * Created by Laur on 2/19/2015.
 */
public class FirstEntryDialog2 extends DialogFragment {
    Context m_context;

    public static FirstEntryDialog2 newInstance() {
        FirstEntryDialog2 f = new FirstEntryDialog2();

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
        final View dialogView = inflater.inflate(R.layout.first_entry_dialog2, null);

        Typeface font = Typeface.createFromAsset(m_context.getAssets(), GeneralData.customFont);

        int height = GeneralData.s_height;
        int width = GeneralData.s_width;
        int textSize = GeneralData.s_inches ; //current_dimensions.height/60);

        Button submit = (Button)dialogView.findViewById(R.id.submitForm);
        submit.getLayoutParams().height = height/14 ;
        submit.getLayoutParams().width = width/3;
        submit.setTypeface(font);
        submit.setTextSize(textSize);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                FirstEntryDialog2.this.getDialog().dismiss();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }

                FirstEntryDialog3 newFragment = FirstEntryDialog3.newInstance();
                newFragment.show(ft, "dialog");
            }
        });

        ImageView liary_pic = (ImageView)dialogView.findViewById(R.id.imageLogoDialog);
        liary_pic.getLayoutParams().height = height/3 ;

        TextView welcome_message = (TextView)dialogView.findViewById(R.id.welcome_message);
        welcome_message.getLayoutParams().height = height/8 ;
        welcome_message.getLayoutParams().width = width;
        welcome_message.setText("This is Liary !\n Your lies are safe here !\n Long tap on each button to see its role !");
        welcome_message.setTypeface(font);
        welcome_message.setTextSize(textSize);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.y = GeneralData.buttonSizeHeight/8;
        wmlp.x = 0;

        dialog.setCanceledOnTouchOutside(false);
        dialog.setInverseBackgroundForced(true);

        return dialog;
    }
}
