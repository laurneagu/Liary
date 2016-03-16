package com.Liary.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.Liary.Database.BlackListEntry;
import com.Liary.R;
import com.Liary.Utils.GeneralData;

import java.util.List;
import java.util.Map;

/**
 * Created by Laur on 1/25/2015.
 */
public class BlackListAdapter  extends BaseAdapter implements View.OnClickListener {

    private Context m_context;
    private List<Map.Entry<String, Integer>> m_blackList;
    private AssetManager m_assetMan;

    public BlackListAdapter(Context i_context, List<Map.Entry<String, Integer>> i_blackList, AssetManager i_assetMan) {
        m_context = i_context;
        m_blackList = i_blackList;
        m_assetMan= i_assetMan;
    }

    @Override
    public int getCount() {
        return m_blackList.size();
    }

    @Override
    public Object getItem(int position) {
        String lied_to = m_blackList.get(position).getKey();
        Integer nr_lies = m_blackList.get(position).getValue();

        BlackListEntry entry = new BlackListEntry(lied_to, nr_lies);
        return entry;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        final BlackListEntry entry = (BlackListEntry)this.getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) m_context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elem_listview_blacklist, null);
        }

        int curr_color = 0xFF767676 + (position * 100);

        TextView liedTo = (TextView) convertView.findViewById(R.id.list_content);
        liedTo.setTypeface(Typeface.createFromAsset(m_assetMan, GeneralData.customFont));
        liedTo.setText((position + 1)  + " . " +  entry.getPersonLied());
        liedTo.getLayoutParams().height = GeneralData.buttonSizeHeight/ 2;
        liedTo.setBackgroundColor(curr_color);

        TextView numberOfLies = (TextView) convertView.findViewById(R.id.list_content2);
        numberOfLies.setTypeface( Typeface.createFromAsset(m_assetMan, GeneralData.customFont));
        String liesString = "lie";
        if(entry.getNumLies() > 1)
            liesString += "s";

        numberOfLies.setText("" + entry.getNumLies() + " " + liesString);
        numberOfLies.setWidth( 8 * GeneralData.displaySizeWidth / 9 );
        numberOfLies.getLayoutParams().height = GeneralData.buttonSizeHeight/ 3;
        numberOfLies.setBackgroundColor(curr_color);

        RelativeLayout curr_lie_lay = (RelativeLayout)convertView.findViewById(R.id.black_lie_list_layout);
        curr_lie_lay.setBackgroundColor(curr_color);

        return convertView;
    }

    @Override
    public void onClick(View view) {

    }

}
