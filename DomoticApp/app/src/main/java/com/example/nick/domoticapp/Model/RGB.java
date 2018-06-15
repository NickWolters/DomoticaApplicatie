package com.example.nick.domoticapp.Model;

public class RGB {
    public static final String TABLE_NAME = "rgbs";

    private String name;
    private int id;
    private int red;
    private int green;
    private int blue;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RGB = "rgb";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public RGB(int red, int green, int blue, String name){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public RGB(){

    }

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RGB + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getColumnRgb() {
        return COLUMN_RGB;
    }

    public static String getColumnTimestamp() {
        return COLUMN_TIMESTAMP;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }


}
