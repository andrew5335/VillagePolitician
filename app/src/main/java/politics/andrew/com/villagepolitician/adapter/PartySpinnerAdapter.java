package politics.andrew.com.villagepolitician.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import politics.andrew.com.villagepolitician.interfacevo.Party;

/**
 * @File : PartySpinnerAdapter
 * @Date : 2019-04-02 오전 9:50
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 정당 목록 스피너용 아답터
**/
public class PartySpinnerAdapter extends ArrayAdapter<Party> {

    private Context context;
    private ArrayList<Party> partyList;

    public PartySpinnerAdapter(Context context, int textViewResourceId, ArrayList<Party> partyList) {
        super(context, textViewResourceId, partyList);
        this.context = context;
        this.partyList = partyList;
    }

    @Override
    public int getCount() {
        return partyList.size();
    }

    @Override
    public Party getItem(int position) {
        return partyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(partyList.get(position).getPolyNm());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(partyList.get(position).getPolyNm());

        return label;
    }
}
