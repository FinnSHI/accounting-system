package com.finn.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.finn.accounting.adapter.AccountAdapter;
import com.finn.accounting.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView todayListView; //今日支出
    List<Account> accountList;
    AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todayListView = findViewById(R.id.main_list);
        accountList = new ArrayList<>();
        //设置适配器：加载每一行数据到列表当中
        adapter = new AccountAdapter(this, accountList);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_record_btn:
                Intent intent = new Intent(this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.main_config_btn:

                break;
        }
    }
}