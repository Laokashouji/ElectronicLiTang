package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.datastructure.MyList;
import edu.datastructure.StringUtil;
import edu.eleclt.entity.MyFile;
import edu.eleclt.loader.Loader;

public class FileRepository {

    private static MyArrayList<MyFile> files = Loader.loadFile("src/main/resources/static/data/File.dat",
            "src/main/resources/static/data/FileNum.txt");
    //private MyArrayList<MyFile> files = new MyArrayList<>();

    public MyArrayList<MyFile> findAll() {
        return files;
    }

    public void addFile(MyFile file){
        files.add(file);
        save();
    }

    public int size(){
        return files.size();
    }

    public boolean findMd5(String md5) {
        for (int i = 0; i < files.size(); i++)
            if (files.get(i).getMd5().equals(md5)) {
                return true;
            }
        return false;
    }

    public void save() {
        Loader.download("src/main/resources/static/data/FileNum.txt", files.size());
        Loader.download("src/main/resources/static/data/File.dat", files);
    }

    public MyList<MyFile> searchByName(String name) {
        MyArrayList<MyFile> list = new MyArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            if (StringUtil.KMP(files.get(i).getName(), name, 0) != -1) {
                list.add(files.get(i));
            }
        }
        return list;
    }
}
