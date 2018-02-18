package com.example.kong.accountingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void textRecordBean() {
        new RecordBean();
    }

//    RecordBean{amount=11.11, type=RECORD_TYPE_EXPENSE, category='Food', remark='Food', date='2018-02-18', timeStamp=1518959358541, uuid='aff0a145-d14c-4220-8b95-2c2f6cfbdebb'}

    @Test
    public void textDatabase() {

//        GlobalUtil.getInstance().setContext(MyApplication.context);

//        LinkedList<String> avaliableDate = GlobalUtil.getInstance().databaseHelper.getAvaliableDate();
//        for (String s : avaliableDate) {
//            LogUtil.println(s);
//        }
//
//        LinkedList<RecordBean> recordBeans = GlobalUtil.getInstance().databaseHelper.readRecords("2018-02-18");
//        for (RecordBean recordBean : recordBeans) {
//            LogUtil.println(recordBean.toString());
//        }

    }

}