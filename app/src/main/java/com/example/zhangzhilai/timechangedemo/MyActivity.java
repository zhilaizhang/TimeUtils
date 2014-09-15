package com.example.zhangzhilai.timechangedemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MyActivity extends ActionBarActivity {

    private TextView mOutputTimeTextView; //显示具体时间转换多少天以前
    private TextView mTimeStampTextView; //显示时间戳转换后天数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        findViews();
        mOutputTimeTextView.setText(TimeUtils.progressDate("2014-09-11T10:00:10+08:00"));
        mTimeStampTextView.setText(TimeUtils.secondToTime("1409900705"));
    }

    private void findViews(){
        mOutputTimeTextView = (TextView)findViewById(R.id.output_days);
        mTimeStampTextView = (TextView)findViewById(R.id.timestamp_days_textview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
