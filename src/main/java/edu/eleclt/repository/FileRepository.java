package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.eleclt.entity.Course;
import edu.eleclt.entity.MyFile;

public class FileRepository {

    private static MyArrayList<MyFile> files = new MyArrayList<>();

    public static MyArrayList<MyFile> findAll() {
        return files;
    }

    public static void addFile(MyFile file){
        files.add(file);
    }

    public static int size(){
        return files.size();
    }

    public static boolean findMd5(String md5) {
        for (int i = 0; i < files.size(); i++)
            if (files.get(i).getMd5().equals(md5)) {
                return true;
            }
        return false;
    }
}
