package edu.eleclt.loader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static final File file = new File("src/main/resources/static/data/Log.txt");

    public static void write(String message) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
            Date date=new Date();
            String str=sdf.format(date);
            out.write(str + "\r\n\t");
            out.write(message + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
