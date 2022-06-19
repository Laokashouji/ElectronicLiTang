package edu.eleclt.controller;

import edu.datastructure.MyDate;
import edu.eleclt.entity.Course;
import edu.eleclt.loader.Log;
import edu.eleclt.repository.CourseRepository;

import org.junit.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/CourseManagement")
public class CourseHandler {

    public CourseRepository courseRepository = new CourseRepository();

    @GetMapping("/findAll")
    public Object[] findAll() {
        Log.write("查询全部课程");
        return courseRepository.findAll().getAll();
    }

    @GetMapping("/findAll/{page}/{size}")
    public Object[] findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Log.write("查询第" + page + "的" + size + "条课程");
        return courseRepository.findAll(page, size).getAll();
    }

    @GetMapping("/searchById/{id}")
    public Object searchById(@PathVariable("id") Integer Id) {
        Log.write("查找id为" + Id + "的课程");
        return courseRepository.searchById(Id);
    }

    @GetMapping("/searchByCourseName/{CourseName}")
    public Object[] searchByCourseName(@PathVariable("CourseName") String CourseName) {
        Log.write("查找名称为" + CourseName + "的课程");
        return courseRepository.searchByCourseName(CourseName).getAll();
    }

    @GetMapping("/searchByTeacherName/{TeacherName}")
    public Object[] searchByTeacherName(@PathVariable("TeacherName") String TeacherName) {
        Log.write("查找老师为" + TeacherName + "的课程");
        return courseRepository.searchByTeacherName(TeacherName).getAll();
    }

    @GetMapping("/editFile/{name}/{id}/{type}")
    public String editFile(@PathVariable("name") String name, @PathVariable("id") int id, @PathVariable("type") int type) {
        String msg = "";
        if (courseRepository.editFile(name, id, type)) {
            msg = type == 1? "资料" : "作业";
            Course course = (Course)searchById(id);
            Log.write("上传" + course.getName() + "的" + msg + ":" + name);
            courseRepository.save();
        }
        else msg = "error";
        return msg;
    }

    @PostMapping("/addCourse")
    public String addCourse(@RequestBody Map<String, Object> map) {

        courseRepository.addCourse(map);
        courseRepository.save();

        Log.write("添加名称为" + (String) map.get("name") + "的课程");
        return "success";
    }

    @PostMapping("/editCourse")
    public String editCourse(@RequestBody Map<String, Object> map) {

        courseRepository.editCourse((int) map.get("id"), map);
        courseRepository.save();

        Log.write("编辑id为" + (int) map.get("id") + "的课程");
        return "success";
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
        courseRepository.save();
        System.out.println(courseRepository.size());
    }
}