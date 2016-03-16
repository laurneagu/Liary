package com.Liary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.Liary.Activity.BlackListActivity;
import com.Liary.Activity.MyLiaryActivity;
import com.Liary.Animations.*;
import com.Liary.CustomAlertDialog.EraseDiaryAlertDialog;
import com.Liary.CustomAlertDialog.ExplainButtonAlertDialog;
import com.Liary.CustomAlertDialog.FirstEntryDialog1;
import com.Liary.Database.DatabaseManipulation;
import com.Liary.Database.Lie;
import com.Liary.DiaryManipulator.DiaryWriter;
import com.Liary.DiaryManipulator.FirstEntry;
import com.Liary.LeftSidePanel.LeftPanelItemClicker;
import com.Liary.LieDetailsElement.CategoryButtons;
import com.Liary.LieDetailsElement.EditTextPersonLied;
import com.Liary.LieDetailsElement.ImageViewLied;
import com.Liary.Utils.GeneralData;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private GestureDetector gestureDetector;
    private MyGestureDetector myGestureDetector;
    private View.OnTouchListener gestureListener;

    // Second panel lie details
    ImageView m_imageView;
    EditText m_personLied;
    RelativeLayout m_categButtons;

    // Left side panel
    private String[] mPanelElems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    // List of lies
    private ArrayList<Lie> m_lies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main);

        // Initialize Database connection
        DatabaseManipulation.OpenOrCreateDatabase(this);
        DatabaseManipulation.CreateLieTable();

        // Get the list of lies
        m_lies = DatabaseManipulation.GetLies();

        try {
            manageUserFirstEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void scaleLabels() {
        int sizePerLabel = GeneralData.s_height / 5;
        int sizeWidth =  GeneralData.s_width;

        // HEADER
        final RelativeLayout headerMenu = (RelativeLayout) findViewById(R.id.headerMenu);
        headerMenu.getLayoutParams().height = sizePerLabel / 2;
        headerMenu.getLayoutParams().width = sizeWidth;

        int textSize = GeneralData.s_inches * 3;

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize(sizePerLabel / 10);
        textView.setHeight(sizePerLabel / 2);
        textView.setWidth(sizeWidth);

        // Edit Text New Lie initially hidden
        final EditText current_lie = (EditText) findViewById(R.id.editTextCurrentLie);
        current_lie.setHeight(0);
        current_lie.setTextSize(textSize/2);
        current_lie.setWidth(sizeWidth);


        // EVENTS SOFT KEYBOARD Edit Text New Lie
        current_lie.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    myGestureDetector.swipeRightToLeft();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(current_lie.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        sizePerLabel = 9 * sizePerLabel / 8;

        GeneralData.buttonSizeHeight = sizePerLabel;
        GeneralData.displaySizeWidth = sizeWidth;

        Button[] mainPageButtons = new Button[]{(Button) findViewById(R.id.buttonBlackList), (Button) findViewById(R.id.buttonWriteToFile),
                (Button) findViewById(R.id.buttonReadFromFile), (Button) findViewById(R.id.buttonEraseDiary)};

        // Custom font
        Typeface font = Typeface.createFromAsset(getAssets(), GeneralData.customFont);
        textView.setTypeface(font);
        m_personLied.setTypeface(font);
        current_lie.setTypeface(font);


        for (int i = 0; i < 4; i++) {
            scaleButton(mainPageButtons[i], sizePerLabel, sizeWidth);
            mainPageButtons[i].setTextSize(textSize);
            mainPageButtons[i].setTypeface(font);
        }
    }

    public void scaleButton(Button i_button, int i_height, int i_width) {
        i_button.setHeight(i_height);
        i_button.setWidth(i_width);
    }

    private void writeToFileEvent(final EditText current_lie) {
        final Button button_write = (Button) findViewById(R.id.buttonWriteToFile);
        button_write.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DiaryWriter writer = new DiaryWriter();
                try {
                    if (GeneralData.writeButtonState == false) {
                        startWriteAction();

                        // Buttons for swipe back suggestion
                        final ImageView i_imgGoBack = (ImageView) findViewById(R.id.imageSwipeBack);
                        i_imgGoBack.getLayoutParams().width = GeneralData.displaySizeWidth / 6;
                        i_imgGoBack.getLayoutParams().height = GeneralData.buttonSizeHeight / 4;
                        i_imgGoBack.setVisibility(View.INVISIBLE);

                        final ImageView i_imgGoBack2 = (ImageView) findViewById(R.id.imageSwipeBack2);
                        i_imgGoBack2.getLayoutParams().width = GeneralData.displaySizeWidth / 6;
                        i_imgGoBack2.getLayoutParams().height = GeneralData.buttonSizeHeight / 4;
                        i_imgGoBack2.setVisibility(View.INVISIBLE);

                    } else {
                        if (GeneralData.panelNr == 1) {
                            startRevertWriteAction();
                        } else { // panel 2
                            startRevertWriteActionPanel2();
                            myGestureDetector.swipeLeftToRight();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void readFromFileEvent() {
        final Button button_read = (Button) findViewById(R.id.buttonReadFromFile);
        button_read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(m_lies.size() > 0) {
                    Intent goToNextActivity = new Intent(getApplicationContext(), MyLiaryActivity.class);
                    startActivity(goToNextActivity);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No lie has been spoken yet !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void blackLieListEvent(){
        final Button button_blackList = (Button) findViewById(R.id.buttonBlackList);
        button_blackList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(m_lies.size() > 0) {
                    // Perform action on click
                    Intent goToNextActivity = new Intent(getApplicationContext(), BlackListActivity.class);
                    startActivity(goToNextActivity);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No lied person has been registered yet !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void eraseAllDataEvent() {
        final Button button_erase_diary = (Button) findViewById(R.id.buttonEraseDiary);
        final EditText current_lie = (EditText) findViewById(R.id.editTextCurrentLie);

        button_erase_diary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DiaryWriter writer = new DiaryWriter();

                try {
                    if (button_erase_diary.getText() == GeneralData.submitText) {
                        // get current edit text area to write to file
                        String lie = current_lie.getText().toString();
                        String person_lied = m_personLied.getText().toString();
                        int category_lie = GeneralData.m_butt_selected;

                        if (!lie.isEmpty() && !person_lied.isEmpty() && category_lie != -1) {
                            DatabaseManipulation.InsertLie(new Lie(lie, person_lied, category_lie));

                            // set current edit text area to empty string after writing the lie
                            current_lie.setText("");
                            m_personLied.setText("");
                            GeneralData.m_butt_selected = -1;

                            // unselect buttons
                            for (int i = 0; i < m_categButtons.getChildCount(); i++) {
                                View child = m_categButtons.getChildAt(i);

                                GradientDrawable gd = new GradientDrawable();
                                gd.setColor(GeneralData.colorsInt[i]);
                                gd.setStroke(5, GeneralData.colorsInt[i]);

                                child.setBackgroundDrawable(gd);
                            }

                            m_lies = DatabaseManipulation.GetLies();

                            if (GeneralData.panelNr == 1) {
                                startRevertWriteAction();
                            } else { // panel 2
                                startRevertWriteActionPanel2();
                                myGestureDetector.swipeLeftToRight();
                            }
                        } else {
                            if (lie.isEmpty())
                                Toast.makeText(getApplicationContext(), "Insert a lie ", Toast.LENGTH_SHORT).show();
                            else {
                                // Vibrate for 500 milliseconds
                                Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibr.vibrate(500);

                                // Animation
                                final ImageView i_imgGoBack = (ImageView) findViewById(R.id.imageSwipeBack);
                                i_imgGoBack.setVisibility(View.VISIBLE);
                                final ImageView i_imgGoBack2 = (ImageView) findViewById(R.id.imageSwipeBack2);
                                i_imgGoBack2.setVisibility(View.VISIBLE);

                                RelativeLayout i_layout = (RelativeLayout) findViewById(R.id.pageLayout);
                                AnimatorSuggestorPanel2 animatorSuggestorPanel2 = new AnimatorSuggestorPanel2(i_imgGoBack, i_imgGoBack2);
                                animatorSuggestorPanel2.setDuration(1000);
                                i_layout.startAnimation(animatorSuggestorPanel2);

                                Toast.makeText(getApplicationContext(), "Swipe left for category and person lied", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                        if (prev != null) {
                            ft.remove(prev);
                        }
                        ft.addToBackStack(null);

                        EraseDiaryAlertDialog newFragment = EraseDiaryAlertDialog.newInstance();
                        newFragment.show(ft, "dialog");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public synchronized void startRevertWriteActionPanel2() {
        final Button i_blackList = (Button) findViewById(R.id.buttonBlackList);
        final Button i_eraseData = (Button) findViewById(R.id.buttonEraseDiary);
        final RelativeLayout i_currentLieLayout = (RelativeLayout) findViewById(R.id.layoutCurrentLie);
        final EditText i_editText = (EditText) findViewById(R.id.editTextCurrentLie);

        RelativeLayout i_layout = (RelativeLayout) findViewById(R.id.pageLayout);
        AnimatorWriterActionRevertPanel2 writeAnimationRev = new AnimatorWriterActionRevertPanel2(i_layout, i_blackList, m_imageView, m_personLied, m_categButtons, i_eraseData, i_currentLieLayout, i_editText);
        writeAnimationRev.setDuration(1000);
        i_layout.startAnimation(writeAnimationRev);

        GeneralData.writeButtonState = false;
    }

    public synchronized void startWriteAction() {
        RelativeLayout i_currentLieLayout = (RelativeLayout) findViewById(R.id.layoutCurrentLie);

        EditText i_editText = (EditText) findViewById(R.id.editTextCurrentLie);
        Button i_blackList = (Button) findViewById(R.id.buttonBlackList);
        Button i_eraseData = (Button) findViewById(R.id.buttonEraseDiary);
        Button i_writeToFileButton = (Button) findViewById(R.id.buttonWriteToFile);

        RelativeLayout i_layout = (RelativeLayout) findViewById(R.id.pageLayout);

        AnimatorWriterAction writeAnimation = new AnimatorWriterAction(i_layout, i_blackList, i_editText, i_eraseData, i_currentLieLayout, i_writeToFileButton);
        writeAnimation.setDuration(1000);
        i_layout.startAnimation(writeAnimation);

        GeneralData.writeButtonState = true;
    }

    public synchronized void startRevertWriteAction() {
        EditText i_editText = (EditText) findViewById(R.id.editTextCurrentLie);
        Button i_blackList = (Button) findViewById(R.id.buttonBlackList);
        Button i_eraseData = (Button) findViewById(R.id.buttonEraseDiary);
        RelativeLayout i_currentLieLayout = (RelativeLayout) findViewById(R.id.layoutCurrentLie);

        RelativeLayout i_layout = (RelativeLayout) findViewById(R.id.pageLayout);
        AnimatorWriterActionRevert writeAnimationRev = new AnimatorWriterActionRevert(i_layout, i_blackList, i_editText, i_eraseData, i_currentLieLayout);
        writeAnimationRev.setDuration(1000);
        i_layout.startAnimation(writeAnimationRev);

        GeneralData.writeButtonState = false;
    }

    public void swipeRightOnCurrentLie(EditText i_curr_lie) {
        final RelativeLayout currLay = (RelativeLayout) findViewById(R.id.layoutCurrentLie);

        currLay.setOnTouchListener(gestureListener);
        i_curr_lie.setOnTouchListener(gestureListener);
    }


    @Override
    public void onStop(){
        super.onStop();
    }

    private boolean firstime = true;

    @Override
    protected void onResume(){
        // TODO Auto-generated method stub
        super.onResume();

        if(!firstime)
            setContentView(R.layout.main);
        else
            firstime = false;

        // second panel - initialize layout childs
        m_imageView = ImageViewLied.CreateView(getApplicationContext());
        m_personLied = EditTextPersonLied.CreateView(getApplicationContext(), m_imageView.getId());
        m_categButtons = CategoryButtons.CreateView(getApplicationContext(), m_personLied.getId());

        scaleLabels();

        final EditText current_lie = (EditText) findViewById(R.id.editTextCurrentLie);

        writeToFileEvent(current_lie);
        readFromFileEvent();
        eraseAllDataEvent();
        blackLieListEvent();
        explainButtons();


        // gesture stuff
        final RelativeLayout i_currentLieLayout = (RelativeLayout) findViewById(R.id.layoutCurrentLie);

        myGestureDetector = new MyGestureDetector(getApplicationContext(), i_currentLieLayout, current_lie, gestureListener,
                m_imageView, m_personLied, m_categButtons);

        gestureDetector = new GestureDetector(this, myGestureDetector);
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        // special events
        swipeRightOnCurrentLie(current_lie);

        // Left side panel
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mPanelElems = getResources().getStringArray(R.array.panel_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPanelElems));

        // Set the list's click listener
        LeftPanelItemClicker.OnItemClick(mDrawerList, getApplicationContext(), MainActivity.this);

        final ImageButton showPanel = (ImageButton) findViewById(R.id.showPanel);
        showPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        showPanel.setBackgroundResource(R.drawable.ic_drawer);
        showPanel.getLayoutParams().width = GeneralData.displaySizeWidth / 8;
        showPanel.getLayoutParams().height = GeneralData.buttonSizeHeight / 8;

        // Buttons for swipe back suggestion
        final ImageView i_imgGoBack = (ImageView) findViewById(R.id.imageSwipeBack);
        i_imgGoBack.getLayoutParams().width = 0;
        i_imgGoBack.getLayoutParams().height = 0;
        final ImageView i_imgGoBack2 = (ImageView) findViewById(R.id.imageSwipeBack2);
        i_imgGoBack2.getLayoutParams().width = 0;
        i_imgGoBack2.getLayoutParams().height = 0;
    }

    public void explainButtons(){
        final Button[] mainPageButtons = new Button[]{(Button) findViewById(R.id.buttonReadFromFile), (Button) findViewById(R.id.buttonWriteToFile),
                 (Button) findViewById(R.id.buttonBlackList), (Button) findViewById(R.id.buttonEraseDiary)};
        final String[] explainButtons = new String[]{getResources().getString(R.string.my_liary_exp),
                                               getResources().getString(R.string.tell_me_exp),
                                               getResources().getString(R.string.black_lie_list_exp),
                                               getResources().getString(R.string.erase_diary_exp)};

        for (int i = 0; i < 4; i++) {
            final int index = i;
            mainPageButtons[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // TODO Auto-generated method stub
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);

                    ExplainButtonAlertDialog newFragment = ExplainButtonAlertDialog.newInstance(mainPageButtons[index], index, explainButtons[index]);
                    newFragment.show(ft, "dialog");
                    return true;
                }
            });
        }
    }

    public void manageUserFirstEntry() throws IOException {
        if(FirstEntry.isFirstEntry(getApplicationContext())){
            //Toast.makeText(getApplicationContext(), "Welcome - new user", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            FirstEntryDialog1 newFragment = FirstEntryDialog1.newInstance(ft);
            newFragment.show(ft, "dialog");
        }
    }
}
