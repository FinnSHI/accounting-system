package com.finn.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.finn.accounting.adapter.AccountAdapter;
import com.finn.accounting.db.DBManager;
import com.finn.accounting.dialog.BudgetDialog;
import com.finn.accounting.entity.Account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView todayListView; //今日支出
    ImageButton recordBtn;
    ImageButton configBtn;

    // top view
    View headerView;
    TextView topSpendTextView;
    TextView topRemainTextView;
    TextView topTodaySpendTextView;
    SharedPreferences preferences;

    // database
    List<Account> accountList;
    AccountAdapter adapter;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("budget", Context.MODE_PRIVATE);
        initTime();
        initView();
        initTopView();
        accountList = new ArrayList<>();
        //设置适配器：加载每一行数据到列表当中
        adapter = new AccountAdapter(this, accountList);
        todayListView.setAdapter(adapter);

    }

    /*
    * @Description: init time
    * @Param: []
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /*
    * @Description: initTopView
    * @Param:
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    private void initTopView() {
        float spendOneDay = DBManager.getSumMoneyOneDay(year, month, day, 0);
        String infoOneDay = "Today HK$" + spendOneDay;
        topTodaySpendTextView.setText(infoOneDay);
        float spendOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        topSpendTextView.setText("HK$" + spendOneMonth);

        // budget
        float budget = preferences.getFloat("budgetMoney", 0);
        if (budget == 0) {
            topRemainTextView.setText("HK$ 0");
        }else{
            float remain = budget - spendOneMonth;
            topRemainTextView.setText("HK$" + remain);
        }
    }

    /*
    * @Description: init view
    * @Param: []
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    private void initView() {
        todayListView = findViewById(R.id.main_list);
        recordBtn = findViewById(R.id.main_record_btn);
        configBtn = findViewById(R.id.main_config_btn);

        /*
        * top view
        * TextView topSpendTextView;
        * TextView topRemainTextView;
        * TextView topTodaySpendTextView;
        */
        headerView = getLayoutInflater().inflate(R.layout.item_main_top, null);
        todayListView.addHeaderView(headerView);

        topSpendTextView = headerView.findViewById(R.id.item_main_top_spend);
        topRemainTextView = headerView.findViewById(R.id.item_main_top_remaining_money);
        topTodaySpendTextView = headerView.findViewById(R.id.item_main_top_today_spend);

        headerView.setOnClickListener(this);
        topRemainTextView.setOnClickListener(this);
    }

    /* 
    * @Description: main_activity获取焦点，执行onResume() 
    * @Param: [] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/3/15 
    */
    @Override
    protected void onResume() {
        super.onResume();
        loadTodayAccount();
        showTopView();
    }

    /*
    * @Description: 显示 top view
    * @Param: []
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    private void showTopView() {

    }

    /* 
    * @Description: 点击事件
    * @Param: [view] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/3/15 
    */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_record_btn:
                Intent intent = new Intent(this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.main_config_btn:

                break;
            case R.id.item_main_top_remaining_money:
                showBudgetDialog();
                break;
        }
    }

    /*
    * @Description: 显示Budget Dialog
    * @Param: []
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    private void showBudgetDialog() {
        BudgetDialog dialog = new BudgetDialog(this);
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnEnsureListener(money -> {
            // budget写入到共享参数中
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat("budgetMoney", money);
            editor.commit();
            // remain
            float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
            float remainMoney = money - outcomeOneMonth;
            topRemainTextView.setText("HK$" + remainMoney);
        });
    }

    /* 
    * @Description: 加载today的记账记录 
    * @Param: [] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/3/15 
    */
    private void loadTodayAccount() {
        List<Account> list = DBManager.getAccountListAtOneDay(year, month, day);
        accountList.clear();
        accountList.addAll(list);
        adapter.notifyDataSetChanged();
    }


}