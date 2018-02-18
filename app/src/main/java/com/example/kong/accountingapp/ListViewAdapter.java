package com.example.kong.accountingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Kong on 2018/2/18.
 */

public class ListViewAdapter extends BaseAdapter {

    private LinkedList<RecordBean> recordBeans = new LinkedList<>();

    private LayoutInflater mInflater;

    private Context mContext;

    public ListViewAdapter(Context context) {
        mContext = context;
//        mContext = MyApplication.getContext();
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(LinkedList<RecordBean> recordBeans) {
        this.recordBeans = recordBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recordBeans.size();
    }

    @Override
    public RecordBean getItem(int position) {
        return recordBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cell_list_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        RecordBean bean = getItem(position);

//        holder.categoryIcone.setImageResource();
//        holder.remarkTV.setText(bean.getRemark());
//        holder.amountTV.setText(bean.getAmount()+"");
//        holder.timeTV.setText(bean.getDate());

        return convertView;
    }

    class ViewHolder {
        private ImageView categoryIcone;
        private TextView remarkTV, amountTV, timeTV;

        public ViewHolder(View view) {
            categoryIcone = view.findViewById(R.id.imageView_category);
            remarkTV = view.findViewById(R.id.textView_remark);
            amountTV = view.findViewById(R.id.textView_amount);
            timeTV = view.findViewById(R.id.textView_time);

        }
    }

}
