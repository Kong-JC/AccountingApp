package com.example.kong.accountingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener, CategoryRecyclerAdapter.OnCategoryClickListener {

    private static final String TAG = "AddRecordActivity";

    private EditText editText;
    private TextView amountText;
    private String userInput = "";

    private RecyclerView recyclerView;
    private CategoryRecyclerAdapter adapter;

    private String category = "General";
    private RecordBean.RecordType type = RecordBean.RecordType.RECORD_TYPE_EXPENSE;
    private String remark = category;

    private RecordBean recordBean = new RecordBean();

    private boolean inEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        initKeyboardView();
    }

    private void initKeyboardView() {
        findViewById(R.id.keyboard_one).setOnClickListener(this);
        findViewById(R.id.keyboard_two).setOnClickListener(this);
        findViewById(R.id.keyboard_three).setOnClickListener(this);
        findViewById(R.id.keyboard_four).setOnClickListener(this);
        findViewById(R.id.keyboard_five).setOnClickListener(this);
        findViewById(R.id.keyboard_six).setOnClickListener(this);
        findViewById(R.id.keyboard_seven).setOnClickListener(this);
        findViewById(R.id.keyboard_eight).setOnClickListener(this);
        findViewById(R.id.keyboard_nine).setOnClickListener(this);
        findViewById(R.id.keyboard_zero).setOnClickListener(this);

        amountText = findViewById(R.id.textVIew_amount);
        editText = findViewById(R.id.editText);
        editText.setText(remark);

        handlerDot();
        handlerTypeChange();
        handlerBackspace();
        handlerDone();

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CategoryRecyclerAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnCategoryClickListener(this);

        RecordBean record = (RecordBean) getIntent().getSerializableExtra("record");
        if (record != null) {
//            LogUtil.d(TAG,"getIntent "+record.getRemark());
            inEdit = true;
            this.recordBean = record;
        }

    }

    private void handlerDone() {
        findViewById(R.id.keyboard_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(userInput)) {
                    double amount = Double.valueOf(userInput);

                    recordBean.setAmount(amount);
                    recordBean.setType(type);
                    recordBean.setCategory(adapter.getSelected());
                    recordBean.setRemark(editText.getText().toString());

                    if (inEdit) {
                        GlobalUtil.getInstance().databaseHelper.editRecord(recordBean.getUuid(),recordBean);
                    } else {
                        GlobalUtil.getInstance().databaseHelper.addRecord(recordBean);
                    }

                    finish();
                } else {
                    Toast.makeText(AddRecordActivity.this, "Amount is 0 !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handlerBackspace() {
        findViewById(R.id.keyboard_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInput.length() > 0) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
                if (userInput.length() > 0 && userInput.charAt(userInput.length() - 1) == '.') {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
                updateAmountText();
            }
        });
    }

    private void handlerTypeChange() {
        findViewById(R.id.keyboard_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == RecordBean.RecordType.RECORD_TYPE_EXPENSE) {
                    type = RecordBean.RecordType.RECORD_TYPE_INCOME;
                } else {
                    type = RecordBean.RecordType.RECORD_TYPE_EXPENSE;
                }
                adapter.changeType(type);
                category = adapter.getSelected();
            }
        });
    }

    private void handlerDot() {
        findViewById(R.id.keyboard_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG, "onClick: dot");
                if (!userInput.contains(".")) {
                    userInput += ".";
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        String input = ((Button) v).getText().toString();
        if (userInput.contains(".")) {
            String[] split = userInput.split("\\.");
            if (split.length == 1 || split[1].length() < 2) {
                userInput += input;
            }
        } else {
            userInput += input;
        }
        updateAmountText();
    }

    private void updateAmountText() {
        LogUtil.d(TAG, "onClick: userInput:" + userInput);
        String[] split = userInput.split("\\.");
        if (userInput.contains(".")) {
            if (split.length == 1) {
                amountText.setText(userInput + "00");
            } else if (split[1].length() == 1) {
                amountText.setText(userInput + "0");
            } else if (split[1].length() == 2) {
                amountText.setText(userInput);
            }
        } else {
            if (TextUtils.isEmpty(userInput)) {
                amountText.setText("0.00");
            } else {
                amountText.setText(userInput + ".00");
            }
        }
    }

    @Override
    public void onClick(String category) {
        this.category = category;
        editText.setText(category);
        LogUtil.d(TAG, category);
    }

}
