package com.Liary.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.Liary.Database.DatabaseManipulation;
import com.Liary.Database.Lie;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

import java.util.List;

/**
 * Created by Laur on 1/25/2015.
 */
public class LieAdapter  extends BaseAdapter implements View.OnClickListener {

    private Context m_context;
    private List<Lie> m_listLie;
    private AssetManager m_assetMan;

    private int[] colorsInt = new int[]{0xFF99CC00,0xFF33B5E5, 0xFF767676, 0xFFFF4444, 0xFFFF8800 };

    public LieAdapter(Context i_context, List<Lie> i_listLie, AssetManager i_assetMan, ListView i_listView) {
        m_context = i_context;
        m_listLie = i_listLie;
        m_assetMan= i_assetMan;
   }

    public int getCount() {
        return m_listLie.size();
    }

    public Object getItem(int position) {
        return m_listLie.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final Lie entry = m_listLie.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) m_context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elem_listview_myliary, null);
        }

        int curr_color = colorsInt[position % 5];

        TextView lie_text = (TextView) convertView.findViewById(R.id.list_content);
        lie_text.setTypeface( Typeface.createFromAsset(m_assetMan, GeneralData.customFont));
        lie_text.setText(entry.getPersonLied());
        lie_text.setMinimumHeight(GeneralData.buttonSizeHeight / 4);
        lie_text.setBackgroundColor(curr_color);

        TextView lie_details = (TextView) convertView.findViewById(R.id.list_content2);
        lie_details.setTypeface( Typeface.createFromAsset(m_assetMan, GeneralData.customFont));
        lie_details.setText(entry.getText());
        lie_details.setMinimumHeight(GeneralData.buttonSizeHeight/4);
        lie_details.setWidth( 8 * GeneralData.displaySizeWidth / 9 );
        lie_details.setBackgroundColor(curr_color);

        RelativeLayout curr_lie_lay = (RelativeLayout)convertView.findViewById(R.id.curr_lie_layout);
        curr_lie_lay.setBackgroundColor(curr_color);

        // button discard current lie
        ImageButton i_discardButton = (ImageButton)convertView.findViewById(R.id.discardLie);
        i_discardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                int selectedId = entry.getId();
                DatabaseManipulation.DeleteLie(selectedId);

                m_listLie.remove(position);
                notifyDataSetChanged();

                Toast.makeText(m_context, "Selected lie was deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // button background category
       Button categButton = (Button)convertView.findViewById(R.id.roundButtonCategory);
       if(entry.getCategory() == 0){
           categButton.setBackgroundResource(R.drawable.circlebuttoncateg1);
        }
        else{
            if(entry.getCategory() == 1){
                categButton.setBackgroundResource(R.drawable.circlebuttoncateg2);
            }
            else{
                categButton.setBackgroundResource(R.drawable.circlebuttoncateg3);
            }
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {

    }

}
