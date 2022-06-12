package edu.eleclt.loader;

import edu.datastructure.MyArrayList;
import edu.eleclt.entity.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader {

    public static int readNum(String fileName){
        try {
            Scanner scanner = new Scanner(new File(fileName));
            return scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> MyArrayList<T> load(String fileName, String fileName2) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));

            Course.setTotId(readNum(fileName2));
            MyArrayList<T> list = (MyArrayList<T>) is.readObject();

            //is.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void download(String fileName,MyArrayList<T> list) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            os.writeObject(list);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void download(String fileName,Integer n) {
        try {
            FileOutputStream os = new FileOutputStream(fileName);
            os.write(n.toString().getBytes());
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
