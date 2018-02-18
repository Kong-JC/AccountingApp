package com.example.kong.accountingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Kong on 2018/2/18.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private LayoutInflater mInflater;
    public Context mContext;

    private LinkedList<CategoryResBean> cellList = GlobalUtil.getInstance().costRes;
    private String selected = "";

    private OnCategoryClickListener onCategoryClickListener;

    public interface OnCategoryClickListener {
        void onClick(String category);
    }

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public CategoryRecyclerAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        selected = cellList.get(0).title;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_category, parent, false);
        CategoryViewHolder myViewHolder = new CategoryViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final CategoryResBean res = cellList.get(position);
        holder.imageView.setImageResource(res.resBlack);
        holder.textView.setText(res.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = res.title;
                notifyDataSetChanged();
                if (onCategoryClickListener != null) {
                    onCategoryClickListener.onClick(selected);
                }
            }
        });

        if (holder.textView.getText().toString().equals(selected)) {
            holder.background.setBackgroundResource(R.drawable.bg_edit_text);
        } else {
            holder.background.setBackgroundResource(R.color.colorPrimary);
        }
    }

    @Override
    public int getItemCount() {
        return cellList.size();
    }

    public String getSelected() {
        return selected;
    }

    public void changeType(RecordBean.RecordType type) {
        if (type == RecordBean.RecordType.RECORD_TYPE_EXPENSE) {
            cellList = GlobalUtil.getInstance().costRes;
        } else {
            cellList = GlobalUtil.getInstance().earnRes;
        }
        selected = cellList.get(0).title;
        notifyDataSetChanged();
    }

}

class CategoryViewHolder extends RecyclerView.ViewHolder {

    RelativeLayout background;
    ImageView imageView;
    TextView textView;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        background = itemView.findViewById(R.id.cell_background);
        imageView = itemView.findViewById(R.id.imageView_category);
        textView = itemView.findViewById(R.id.textView_category);
    }

}