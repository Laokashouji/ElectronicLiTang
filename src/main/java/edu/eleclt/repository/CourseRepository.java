package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.eleclt.entity.Course;
import edu.eleclt.loader.Loader;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository{

    //private static final long serialVersionUID = 123456546511L;

    private static MyArrayList<Course> courses = Loader.load("src/data/Course.dat", "src/data/CourseNum.txt");
    //private static MyArrayList<Course> courses = new MyArrayList<>();

    public static Boolean isEmpty(){
        return courses.isEmpty();
    }

    public static MyArrayList<Course> findAll(){
        return courses;
    }

    public static MyArrayList<Course> findAll(int page, int size){
        return courses.slices((page - 1) * size, page * size > courses.size()? courses.size() : page * size);
    }

    public static MyArrayList<Course> searchByCourseName(String CourseName){
        MyArrayList<Course> arr = new MyArrayList<Course>();
        for (int i = 0; i <courses.size(); i++) {
            if (courses.get(i).getName().equals(CourseName)) {
                arr.add(courses.get(i));
            }
        }
        return arr;
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static int size() {
        return courses.size();
    }

    public static void save(){
        Loader.download("src/data/CourseNum.txt", Course.getTotId());
        Loader.download("src/data/Course.dat", courses);
    }
}
