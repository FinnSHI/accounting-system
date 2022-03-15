package com.finn;

import android.app.Application;

import com.finn.accounting.db.DBManager;

/*
 * @description: 全局应用类
 * @author: Finn
 * @create: 2022-03-13-21-11
 */
public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(getApplicationContext());
    }
}
