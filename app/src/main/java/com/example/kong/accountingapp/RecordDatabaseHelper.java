package com.example.kong.accountingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

/**
 * Created by Kong on 2018/2/18.
 */

public class RecordDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "RecordDatabaseHelper";

    public static final String DB_NAME = "Record";

    private static final String CRETE_RECORD_DB = "create table Record (" +
            "id integer primary key autoincrement," +
            "uuid text," + // 唯一标识
            "type integer," + // 类型
            "category text," + // 消费类别
            "remark text," + // 备注
            "amount double," + // 金额
            "time integer," + // 时间戳
            "date date )"; // 日期

    private Context context;

    public RecordDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRETE_RECORD_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 增
    public void addRecord(RecordBean bean) {
        LogUtil.d(TAG, bean.toString());
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uuid", bean.getUuid());
        values.put("type", bean.getType());
        values.put("category", bean.getCategory());
        values.put("remark", bean.getRemark());
        values.put("amount", bean.getAmount());
        values.put("date", bean.getDate());
        values.put("time", bean.getTimeStamp());
        db.insert(DB_NAME, null, values);
        values.clear();
        LogUtil.d(TAG, bean.getUuid() + " adder");
    }

    // 删
    public void removeRecord(String uuid) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DB_NAME, "uuid = ?", new String[]{uuid});
    }

    // 改
    public void editRecord(String uuid, RecordBean record) {
        removeRecord(uuid);
        record.setUuid(uuid);
        addRecord(record);
    }

    // 查
    public LinkedList<RecordBean> readRecords(String dateStr) {
        LinkedList<RecordBean> recordBeans = new LinkedList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(context.getString(R.string.queryDate), new String[]{dateStr});
        if (cursor.moveToFirst()) {
            do {
                String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));
                double amount = cursor.getDouble(cursor.getColumnIndex("amount"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                long timtStamp = cursor.getLong(cursor.getColumnIndex("time"));
                RecordBean record = new RecordBean();
                record.setUuid(uuid);
                record.setType(type);
                record.setCategory(category);
                record.setRemark(remark);
                record.setAmount(amount);
                record.setDate(date);
                record.setTimeStamp(timtStamp);
                recordBeans.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return recordBeans;
    }

    public LinkedList<String> getAvaliableDate() {
        LinkedList<String> dates = new LinkedList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT * from Record order by date asc", new String[]{});
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                if (!dates.contains(date)) {
                    dates.add(date);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dates;
    }

}
