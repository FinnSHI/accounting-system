package com.finn.accounting.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.finn.accounting.R;

/*
 * @description: open db
 * @author: Finn
 * @create: 2022-03-13-20-51
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(@Nullable Context context) {
        super(context,"as.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table type_tb" +
                "(" +
                "id integer primary key autoincrement," +
                "typeName varchar(10)," +
                "imageId integer," +
                "sImageId integer," +
                "kind integer" +
                ")";
        sqLiteDatabase.execSQL(sql);
        insertType(sqLiteDatabase);

        sql = "create table account_tb(" +
                "id integer primary key autoincrement," +
                "typeName varchar(10)," +
                "sImageId integer," +
                "description varchar(80)," +
                "money float," +
                "time varchar(60)," +
                "year integer," +
                "month integer," +
                "day integer," +
                "kind integer" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    private void insertType(SQLiteDatabase sqLiteDatabase) {
        String sql = "insert into type_tb (typeName,imageId,sImageId,kind) values (?,?,?,?)";
        sqLiteDatabase.execSQL(sql,new Object[]{"Others", R.mipmap.ic_qita,R.mipmap.ic_qita_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Food", R.mipmap.ic_canyin,R.mipmap.ic_canyin_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Transport", R.mipmap.ic_jiaotong,R.mipmap.ic_jiaotong_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Shopping", R.mipmap.ic_gouwu,R.mipmap.ic_gouwu_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Fruit", R.mipmap.ic_fushi,R.mipmap.ic_fushi_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Network", R.mipmap.ic_tongxun,R.mipmap.ic_tongxun_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Game", R.mipmap.ic_yule,R.mipmap.ic_yule_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Rent", R.mipmap.ic_zhufang,R.mipmap.ic_zhufang_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Hospital", R.mipmap.ic_yiliao,R.mipmap.ic_yiliao_fs,0});
        sqLiteDatabase.execSQL(sql,new Object[]{"Study", R.mipmap.ic_xuexi,R.mipmap.ic_xuexi_fs,0});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
