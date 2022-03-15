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

}
