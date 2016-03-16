package com.Liary.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.Liary.R;
import com.Liary.Utils.CreditsPerson;
import com.Liary.Utils.GeneralData;

import java.util.List;

/**
 * Created by Laur on 2/16/2015.
 */
public class CreditsAdapter  extends BaseAdapter implements View.OnClickListener {

    private Context m_context;
    private List<CreditsPerson> m_listPerson;
    private AssetManager m_assetMan;

    public CreditsAdapter(Context i_context, AssetManager i_assetMan) {
        m_context = i_context;
        m_assetMan= i_assetMan;

        m_listPerson = CreditsPerson.Init();
    }

    public int getCount() {
        return m_listPerson.size();
    }

    public Object getItem(int position) {
        return m_listPerson.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final CreditsPerson entry = m_listPerson.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) m_context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.elem_listview_credits, null);
        }

        int textSize = GeneralData.s_inches;

        RelativeLayout details = (RelativeLayout)convertView.findViewById(R.id.detailsLay);
        details.getLayoutParams().height =  3* GeneralData.buttonSizeHeight /2 ;
        details.getLayoutParams().width = GeneralData.displaySizeWidth/2;

        TextView lie_text = (TextView) convertView.findViewById(R.id.list_content);
        lie_text.setTypeface( Typeface.createFromAsset(m_assetMan, GeneralData.customFont));
        lie_text.setText(entry.getName());
        lie_text.setMinimumHeight(GeneralData.buttonSizeHeight / 4);

        TextView lie_details = (TextView) convertView.findViewById(R.id.list_content2);
        lie_details.setTypeface( Typeface.createFromAsset(m_assetMan, GeneralData.customFont));
        lie_details.setText(entry.getRole());
        lie_details.setTextSize(textSize);
        lie_details.setMinimumHeight(GeneralData.buttonSizeHeight/4);
        lie_details.setWidth( 8 * GeneralData.displaySizeWidth / 9 );

        // button background category
        ImageView personPic = (ImageView)convertView.findViewById(R.id.personPic);
        personPic.setImageResource(entry.getPicSource());
        personPic.setScaleType(ImageView.ScaleType.FIT_XY);
        personPic.getLayoutParams().height =  3* GeneralData.buttonSizeHeight /2 ;
        personPic.getLayoutParams().width = GeneralData.displaySizeWidth/2;
        return convertView;
    }

    @Override
    public void onClick(View view) {

    }
}
