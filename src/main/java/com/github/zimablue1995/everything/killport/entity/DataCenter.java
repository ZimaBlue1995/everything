package com.github.zimablue1995.everything.killport.entity;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DataCenter {
    public static String KILL_PID;
    public static String KILL_IMAGE;
    public static List<PortEntity> NOTE_LIST = new ArrayList();
    public static String[] HEADER = new String[]{"PID", "image", "协议", "状态"};
    public static DefaultTableModel TABLE_MODEL;

    public DataCenter() {
    }

    public static void reset() {
        TABLE_MODEL.setDataVector((Object[][])null, HEADER);
    }

    static {
        TABLE_MODEL = new DefaultTableModel((Object[][])null, HEADER);
    }
}