package com.example.kong.accountingapp;

import java.util.UUID;

/**
 * Created by Kong on 2018/2/17.
 */

public class RecordBean {

    private static final String TAG = "RecordBean";

    // 消费类别
    public enum RecordType {
        RECORD_TYPE_EXPENSE, RECORD_TYPE_INCOME
    }

    // 消费金额
    private double amount;

    // 消费类别
    private RecordType type;

    // 消费类型
    private String category;

    // 备注
    private String remark;

    // 日期  格式:2017-06-15
    private String date;

    // 时间戳
    private long timeStamp;

    // 唯一标识
    private String uuid;

    public RecordBean() {
        uuid = UUID.randomUUID().toString();
        LogUtil.d(TAG, "RecordBean: uuid" + uuid);
        timeStamp = System.currentTimeMillis();
        date = DateUtil.getFormattedDate();

        // JUnit 用
        LogUtil.println("RecordBean: uuid:" + uuid);
        LogUtil.println("RecordBean: timeStamp:" + timeStamp);
        LogUtil.println("RecordBean: getFormattedTime:" + DateUtil.getFormattedTime(timeStamp));
        LogUtil.println("RecordBean: getFormattedDate:" + DateUtil.getFormattedDate());
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        if (this.type == RecordType.RECORD_TYPE_EXPENSE) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setType(int type) {
        if (type == 1){
            this.type = RecordType.RECORD_TYPE_EXPENSE;
        }else{
            this.type = RecordType.RECORD_TYPE_INCOME;
        }
    }
    public void setType(RecordType type) {
            this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
