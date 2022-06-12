package edu.eleclt.controller;

import edu.eleclt.entity.Course;
import edu.eleclt.repository.CourseRepository;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/CourseManagement")
public class CourseHandler {

    CourseRepository courseRepository = new CourseRepository();

    @GetMapping("/findAll")
    public Object[] findAll() {
        return courseRepository.findAll().getAll();
    }

    @GetMapping("/findAll/{page}/{size}")
    public Object[] findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return courseRepository.findAll(page, size).getAll();
    }

    @GetMapping("/searchByCourseName/{CourseName}")
    public Object[] searchByCourseName(@PathVariable("CourseName") String CourseName) {
        return courseRepository.searchByCourseName(CourseName).getAll();
    }

    @Test
    public void Mytest() {
        String teacher = "丁真珍珠";
        String[] time = {"Mon,9:50 ~ 11:25 ", "Fri,9:50 ~ 11:25 "};
        String[] place = {"N105 ", "S105 "};
        String[] meterials = {"顶真修炼手册从.txt", "a友的自我修养"};
        int progress = 50;
        String[] homeWorksFinished = {"高数第一周", "高数第二周"};
        String[] homeWorksToDo = {"高数第三周", "高数第四周"};
        String group = "114514";
        String examTime = "2022.6.16  9:50~11:25";
        String examPlace = "N305";
        courseRepository.addCourse(new Course("高等数学", teacher, time, place, meterials, progress,
                homeWorksFinished, homeWorksToDo, group, examTime, examPlace));
        courseRepository.addCourse(new Course("线性代数", teacher, time, place, meterials, progress,
                homeWorksFinished, homeWorksToDo, group, examTime, examPlace));
        courseRepository.addCourse(new Course("数据结构", teacher, time, place, meterials, progress,
                homeWorksFinished, homeWorksToDo, group, examTime, examPlace));
        courseRepository.save();
        System.out.println(courseRepository.size());
    }
}