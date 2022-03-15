package com.finn.accounting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finn.accounting.R;
import com.finn.accounting.entity.Account;

import java.util.Calendar;
import java.util.List;

/*
 * @description: AccountAdapter
 * @author: Finn
 * @create: 2022-03-15-13-50
 */
public class AccountAdapter extends BaseAdapter {
    Context context;
    List<Account> accountList;
    LayoutInflater inflater;
    int year;
    int month;
    int day;
    public AccountAdapter(Context context, List<Account> accountList) {
        this.context = context;
        this.accountList = accountList;
        inflater = LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 加载布局
            convertView = inflater.inflate(R.layout.item_main,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Account bean = accountList.get(position);
        holder.typeIv.setImageResource(bean.getsImageId());
        holder.typeTv.setText(bean.getTypeName());
        holder.descriptionTv.setText(bean.getDescription());
        holder.moneyTv.setText("￥ "+bean.getMoney());
        if (bean.getYear()==year&&bean.getMonth()==month&&bean.getDay()==day) {
            String time = bean.getTime().split(" ")[1];
            holder.timeTv.setText("今天 "+time);
        }else {
            holder.timeTv.setText(bean.getTime());
        }
        return convertView;
    }

    class ViewHolder{
        ImageView typeIv;
        TextView typeTv, descriptionTv, timeTv, moneyTv;
        public ViewHolder(View view){
            typeIv = view.findViewById(R.id.item_img);
            typeTv = view.findViewById(R.id.item_title);
            timeTv = view.findViewById(R.id.item_spend_time);
            descriptionTv = view.findViewById(R.id.item_description);
            moneyTv = view.findViewById(R.id.item_spend);

        }
    }
}
