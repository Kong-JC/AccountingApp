package com.example.kong.accountingapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Kong on 2018/2/18.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private View rootView;
    private TextView textView;
    private ListView listView;
    private ListViewAdapter listViewAdapter;

    private LinkedList<RecordBean> records = new LinkedList<>();

    private String date = "";

    @SuppressLint("ValidFragment")
    public MainFragment(String date) {
        this.date = date;
        records = GlobalUtil.getInstance().databaseHelper.readRecords(date);
//        records.add(new RecordBean());
//        records.add(new RecordBean());
//        records.add(new RecordBean());
//        records.add(new RecordBean());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        textView = rootView.findViewById(R.id.day_text);
        listView = rootView.findViewById(R.id.list_view);
//        textView.setText(date);
        textView.setText(DateUtil.getDateTitle(date));
        listViewAdapter = new ListViewAdapter(getContext());
        listView.setAdapter(listViewAdapter);
        reload();

        listView.setOnItemLongClickListener(this);

    }

    public void reload() {
        records = GlobalUtil.getInstance().databaseHelper.readRecords(date);
        if (listViewAdapter==null) {
            listViewAdapter = new ListViewAdapter(getContext());
        }
        listViewAdapter.setData(records);
        listView.setAdapter(listViewAdapter);
        if (listViewAdapter.getCount() > 0) {
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.GONE);
        }
    }

    public double getTotalCose() {
        double totalCost = 0;
        for (RecordBean record : records) {
            if (record.getType() == 1) {
                totalCost += record.getAmount();
            }
        }
        return totalCost;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        showDialog(position);
        return false;
    }

    private void showDialog(final int index) {
        final String[] options = {"Remove", "Edit"};

        final RecordBean selectedRecord = records.get(index);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.create();
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 0 -> Remove  1 -> Edit
                if (which == 0) {
                    String uuid = selectedRecord.getUuid();
                    GlobalUtil.getInstance().databaseHelper.removeRecord(uuid);
                    reload();
                    GlobalUtil.getInstance().updateHandler.updateAmount();
                } else if (which == 1) {
//                    RecordBean record =
                    Intent intent = new Intent(getActivity(),AddRecordActivity.class);
                    Bundle extra = new Bundle();
                    extra.putSerializable("record",selectedRecord);
                    intent.putExtras(extra);
                    startActivityForResult(intent,1);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

}
