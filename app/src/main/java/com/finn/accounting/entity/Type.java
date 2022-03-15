package com.finn.accounting.entity;

/*
 * @description:
 * @author: Finn
 * @create: 2022-03-13-20-45
 */

public class Type {
    private int id;
    private String typeName;
    private int imageId; // choose
    private int sImageId; // no choose
    private int kind;

    public Type(int id, String typeName, int imageId, int sImageId, int kind) {
        this.id = id;
        this.typeName = typeName;
        this.imageId = imageId;
        this.sImageId = sImageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getsImageId() {
        return sImageId;
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
