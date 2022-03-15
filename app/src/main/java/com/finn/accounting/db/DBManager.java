package com.finn.accounting.db;

/*
 * @description: manage db
 * @author: Finn
 * @create: 2022-03-13-20-50
 */

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.finn.accounting.entity.Account;
import com.finn.accounting.entity.Type;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static SQLiteDatabase db;
    /* 初始化数据库对象*/
    public static void initDB(Context context) {
        MyDBOpenHelper helper = new MyDBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    /*
    * @Description: 获取类型内容
    * @Param: [kind]
    * @return:
    * @Author: Finn
    * @Date: 2022/3/13
    */
    public static List<Type> getTypeList(int kind) {
        List<Type> typeList = new ArrayList<>();
        String sql = "select * from type_tb where kind = " + kind;
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            @SuppressLint("Range") String typename = cursor.getString(cursor.getColumnIndex("typeName"));
            @SuppressLint("Range") int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            @SuppressLint("Range") int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            @SuppressLint("Range") int kind1 = cursor.getInt(cursor.getColumnIndex("kind"));
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            Type type = new Type(id, typename, imageId, sImageId, kind1);
            typeList.add(type);
        }
        return typeList;
    }

    /*
    * @Description: 数据插入到记账表
    * @Param: [bean]
    * @return:
    * @Author: Finn
    * @Date: 2022/3/13
    */
    public static void insertItemToAccountTb(Account bean){
        ContentValues values = new ContentValues();
        values.put("typeName", bean.getTypeName());
        values.put("sImageId", bean.getsImageId());
        values.put("description", bean.getDescription());
        values.put("money", bean.getMoney());
        values.put("time", bean.getTime());
        values.put("year", bean.getYear());
        values.put("month", bean.getMonth());
        values.put("day", bean.getDay());
        values.put("kind", bean.getKind());
        db.insert("account_tb", null, values);
    }

    /* 
    * @Description: 获取某一天记账数据
    * @Param:  
    * @return:  
    * @Author: Finn
    * @Date: 2022/3/15 
    */
    @SuppressLint("Range")
    public static List<Account> getAccountListAtOneDay(int year, int month, int day) {
        List<Account>list = new ArrayList<>();
        String sql = "select * from account_tb where year=? and month=? and day=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{
                year + "", month + "", day + ""
        });

        while (cursor.moveToNext()) {
      /*    int id;
            String typeName;
            int sImageId;
            String description;
            float money;
            String time;
            int year;
            int month;
            int day;
            int kind;
       */
            System.out.println(cursor.getInt(cursor.getColumnIndex("id")));
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String typename = cursor.getString(cursor.getColumnIndex("typeName"));
            @SuppressLint("Range") int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") float money = cursor.getFloat(cursor.getColumnIndex("money"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            @SuppressLint("Range") int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            Account bean = new Account(id, typename, sImageId, description, money, time, year, month, day, kind);
            list.add(bean);
        }

        return list;
    }

    /*
    * @Description: 获取当年支持
    * @Param: [year, kind]
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    @SuppressLint("Range")
    public static float getSumMoneyOneYear(int year, int kind){
        float money = 0.0f;
        String sql = "select sum(money) from account_tb where year=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", kind + ""});
        // 遍历
        if (cursor.moveToFirst()) {
            money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
        }
        return money;
    }

    /*
    * @Description: 获取某月的支出
    * @Param: [year, month, kind]
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    @SuppressLint("Range")
    public static float getSumMoneyOneMonth(int year, int month, int kind){
        float money = 0.0f;
        String sql = "select sum(money) from account_tb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        // 遍历
        if (cursor.moveToFirst()) {
            money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
        }
        return money;
    }

    /*
    * @Description: 获取当天的支出
    * @Param: [year, month, day, kind]
    * @return:
    * @Author: Finn
    * @Date: 2022/3/15
    */
    @SuppressLint("Range")
    public static float getSumMoneyOneDay(int year, int month, int day, int kind){
        float money = 0.0f;
        String sql = "select sum(money) from account_tb where year=? and month=? and day=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + "", kind + ""});
        // 遍历
        if (cursor.moveToFirst()) {
            money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
        }
        return money;
    }
}
