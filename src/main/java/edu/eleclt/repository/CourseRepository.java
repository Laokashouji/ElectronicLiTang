package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.datastructure.MyDate;
import edu.datastructure.MyList;
import edu.datastructure.StringUtil;
import edu.eleclt.entity.Course;
import edu.eleclt.loader.Loader;

import java.util.*;

public class CourseRepository {

    //private static final long serialVersionUID = 123456546511L;

    private static MyArrayList<Course> courses = Loader.loadCourse("src/main/resources/static/data/Course.dat",
            "src/main/resources/static/data/CourseNum.txt");
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

    public static MyArrayList<Course> searchByCourseName(String courseName) {
        MyArrayList<Course> arr = new MyArrayList<Course>();
        for (int i = 0; i < courses.size(); i++) {
            if (StringUtil.KMP(courses.get(i).getName(), courseName, 0) != -1) {
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
            if (StringUtil.KMP(courses.get(i).getTeacher(), teacherName, 0) != -1) {
                arr.add(courses.get(i));
            }
        }
        return arr;
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static void addCourse(Map<String,Object> map) {
        String name = (String) map.get("name");
        String teacherName = (String) map.get("teacherName");
        List<Map<String,Object>> times= (List<Map<String, Object>>) map.get("times");
        String place = (String) map.get("place");
        String groupNum = (String) map.get("groupNum");
        MyDate[] dates = new MyDate[times.size()];
        int i = 0;
        for (Map<String, Object> maps : times) {
            String weekday = (String) maps.get("key");
            List<String> time = (List<String>) maps.get("value");
            dates[i++] = new MyDate(weekday, time);
        }
        courses.add(new Course(name, teacherName, dates, place, groupNum));
    }

    public static void editCourse(int id, Map<String,Object> map) {

        List<Map<String,Object>> times= (List<Map<String, Object>>) map.get("time");
        Map<String,Object> examTime = (Map<String,Object>) map.get("examTime");
        Course course = CourseRepository.searchById(id);
        LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>) map.get("homeWorksToDo");
        ArrayList<String> homeWorksToDo = (ArrayList<String>) lm.get("all");
        course.setExamPlace((String) map.get("examPlace"));
        course.setGroup((String) map.get("group"));
        course.setName((String) map.get("name"));
        course.setTeacher((String) map.get("teacher"));
        course.setProgress(Integer.parseInt(map.get("progress").toString()));
        course.setPlace((String) map.get("place"));

       course.setHomeWorksToDo(new MyArrayList<String>(homeWorksToDo));

        MyDate[] dates = new MyDate[times.size()];
        int i = 0;
        for (Map<String, Object> maps : times) {
            String weekday = (String) maps.get("weekday");
            List<String> time = (List<String>) maps.get("rowTime");
            dates[i++] = new MyDate(weekday, time);
        }
        course.setTime(dates);
        String fomatTime = (String) examTime.get("formatTime");
        if(fomatTime != "") {
            String weekday = (String) examTime.get("weekday");
            List<String> time = (List<String>) examTime.get("rowTime");
            course.setExamTime(new MyDate(weekday, time, (String) examTime.get("formatTime")));
        }
    }


    public static int size() {
        return courses.size();
    }

    public static void save() {
        Loader.download("src/main/resources/static/data/CourseNum.txt", courses.size());
        Loader.download("src/main/resources/static/data/Course.dat", courses);
    }

    public boolean editFile(String name, int id, int type) {
        Course course = searchById(id);
        if(type == 1){
            course.addMeterial(name);
            return true;
        }
        else if (type == 2) {
            MyArrayList<String> list = course.getHomeWorksToDo();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(name)) {
                    course.addHomework(name);
                    return true;
                }
            }
        }
        return false;
    }
}
