package com.finn.accounting.entity;

/*
 * @description: 支出数据
 * @author: Finn
 * @create: 2022-03-13-22-09
 */
public class Account {
    int id;
    String typeName;
    int sImageId;
    String description;
    float money;
    String time;
    int year;
    int month;
    int day;
    int kind;

    public Account() {
        super();
    }

    public Account(int id, String typeName, int sImageId, String description, float money, String time, int year, int month, int day, int kind) {
        this.id = id;
        this.typeName = typeName;
        this.sImageId = sImageId;
        this.description = description;
        this.money = money;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getsImageId() {
        return sImageId;
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
