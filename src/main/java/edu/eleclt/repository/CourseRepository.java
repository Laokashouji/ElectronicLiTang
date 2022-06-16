package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.datastructure.MyDate;
import edu.datastructure.MyList;
import edu.eleclt.entity.Course;
import edu.eleclt.loader.Loader;

import java.util.*;

public class CourseRepository {

    //private static final long serialVersionUID = 123456546511L;

    private static MyArrayList<Course> courses = Loader.load("src/main/resources/static/data/Course.dat", "src/main/resources/static/data/CourseNum.txt");
    //private static MyArrayList<Course> courses = new MyArrayList<>();

    public static Boolean isEmpty() {
        return courses.isEmpty();
    }

    public static MyArrayList<Course> findAll() {
        return courses;
    }

    public static MyArrayList<Course> findAll(int page, int size) {
        return courses.slices((page - 1) * size, page * size > courses.size() ? courses.size() : page * size);
    }

    public static MyArrayList<Course> searchByCourseName(String CourseName) {
        MyArrayList<Course> arr = new MyArrayList<Course>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getName().equals(CourseName)) {
                arr.add(courses.get(i));
            }
        }
        return arr;
    }

    public static Course searchById(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                return courses.get(i);
            }
        }
        return null;
    }

    public static MyList<Course> searchByTeacherName(String teacherName) {
        MyArrayList<Course> arr = new MyArrayList<Course>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getTeacher().equals(teacherName)) {
                arr.add(courses.get(i));
            }
        }
        return arr;
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static void addCourse(String name, String teacherName, List<Map<String, Object>> times, String place, String group) {
        MyDate[] dates = new MyDate[times.size()];
        int i = 0;
        for (Map<String, Object> map : times) {
            String weekday = (String) map.get("key");
            List<String> time = (List<String>) map.get("value");
            dates[i++] = new MyDate(weekday, time);
        }
        courses.add(new Course(name, teacherName, dates, place, group));
    }

    public static int size() {
        return courses.size();
    }

    public static void save() {
        Loader.download("src/main/resources/static/data/CourseNum.txt", Course.getTotId());
        Loader.download("src/main/resources/static/data/Course.dat", courses);
    }

}
