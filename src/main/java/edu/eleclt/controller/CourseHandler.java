package edu.eleclt.controller;

import edu.datastructure.MyDate;
import edu.eleclt.entity.Course;
import edu.eleclt.repository.CourseRepository;

import org.junit.Test;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/CourseManagement")
public class CourseHandler {

    public CourseRepository courseRepository = new CourseRepository();

    @GetMapping("/findAll")
    public Object[] findAll() {
        return courseRepository.findAll().getAll();
    }
    @GetMapping("/findAll/{page}/{size}")
    public Object[] findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return courseRepository.findAll(page, size).getAll();
    }

    @GetMapping("/searchById/{id}")
    public Object searchById(@PathVariable("id") Integer Id){
        return courseRepository.searchById(Id);
    }

    @GetMapping("/searchByCourseName/{CourseName}")
    public Object[] searchByCourseName(@PathVariable("CourseName") String CourseName) {
        return courseRepository.searchByCourseName(CourseName).getAll();
    }
    @GetMapping("/searchByTeacherName/{TeacherName}")
    public Object[] searchByTeacherName(@PathVariable("TeacherName") String TeacherName) {
        return courseRepository.searchByTeacherName(TeacherName).getAll();
    }

    @PostMapping("/addCourse")
    public String addCourse(@RequestBody Map<String,Object> map){

        System.out.println(map);

        String name = (String) map.get("name");
        String teacherName = (String) map.get("teacherName");
        List<Map<String,Object>> times= (List<Map<String, Object>>) map.get("times");
        String place = (String) map.get("place");
        String groupNum = (String) map.get("groupNum");

        courseRepository.addCourse(name, teacherName, times, place, groupNum);

        courseRepository.save();
        System.out.println(courseRepository.size());

        return  "success";
    }

    @Test
    public void Mytest() {
        String teacher = "丁真珍珠";
        MyDate[] time = {new MyDate(), new MyDate()};
        String[] place = {"N105 ", "S105 "};
        String[] meterials = {"顶真修炼手册从.txt", "a友的自我修养"};
        int progress = 50;
        String[] homeWorksFinished = {"高数第一周", "高数第二周"};
        String[] homeWorksToDo = {"高数第三周", "高数第四周"};
        String group = "114514";
        MyDate examTime = new MyDate();
        String examPlace = "N305";
        /*courseRepository.addCourse(new Course("高等数学", teacher, time, place, meterials, progress,
                homeWorksFinished, homeWorksToDo, group, examTime, examPlace));
        courseRepository.addCourse(new Course("线性代数", teacher, time, place, meterials, progress,
                homeWorksFinished, homeWorksToDo, group, examTime, examPlace));
        courseRepository.addCourse(new Course("数据结构", teacher, time, place, meterials, progress,
                homeWorksFinished, homeWorksToDo, group, examTime, examPlace));*/
        courseRepository.save();
        System.out.println(courseRepository.size());
    }
}