package com.example.kong.accountingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,GlobalUtil.UpdateHandler {

    private static final String TAG = "MainActivity";

    private TickerView tickerView;
    private ViewPager viewPager;
    private MainViewPagerAdapter pagerAdapter;
    private TextView dateText;

    private int currentPagerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        GlobalUtil.getInstance().setContext(this);
        GlobalUtil.getInstance().mainActivity = this;

        tickerView = findViewById(R.id.amount_text);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());

        dateText = findViewById(R.id.date_text);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(pagerAdapter.getLastIndex());

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddRecordActivity.class), 1);
            }
        });

        GlobalUtil.getInstance().setUpdateHandler(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pagerAdapter.reload();
        updateHandler();
//        tickerView.setText((int) pagerAdapter.getTotalCost(position) + "");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPagerPosition = position;
        updateHandler();
    }

    private void updateHandler() {
        tickerView.setText((int) pagerAdapter.getTotalCost(currentPagerPosition) + " ");
        String date = pagerAdapter.getDateStr(currentPagerPosition);
        dateText.setText(DateUtil.getWeekDay(date));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void updateAmount() {
        updateHandler();
    }

}
