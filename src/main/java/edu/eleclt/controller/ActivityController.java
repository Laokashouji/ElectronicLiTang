package edu.eleclt.controller;

import edu.eleclt.loader.Log;
import edu.eleclt.repository.ActivityRepository;
import edu.eleclt.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ActivityManagement")
public class ActivityController {


    public ActivityRepository activityRepository = new ActivityRepository();

    @GetMapping("/findAll")
    public Object[] findAll() {
        Log.write("查询全部活动");
        return activityRepository.findAll().getAll();
    }

    @GetMapping("/searchByName/{Name}")
    public Object[] searchByName(@PathVariable("Name") String Name) {
        Log.write("查找名称为"+Name+"的活动");
        return activityRepository.searchByName(Name).getAll();
    }
    @GetMapping("/searchByTag/{Tag}")
    public Object[] searchByTag(@PathVariable("Tag") String Tag) {
        Log.write("查找标签为"+Tag+"的活动");
        return activityRepository.searchByTag(Tag).getAll();
    }
    @GetMapping("/searchByType/{Type}")
    public Object[] searchByType(@PathVariable("Type") String Type) {
        Log.write("查找类型为"+Type+"的活动");
        return activityRepository.searchByType(Type).getAll();
    }

    @PostMapping("/addActivity")
    public String addActivity(@RequestBody Map<String,Object> map){

        System.out.println(map);

        String name = (String) map.get("name");
        Map<String,Object> times= (Map<String, Object>) map.get("time");
        String place = (String) map.get("place");
        String type = (String) map.get("type");
        String tag = (String) map.get("tag");

        activityRepository.addActivity(name, place, times, type, tag);

        activityRepository.save();
        System.out.println(activityRepository.size());

        Log.write("添加名称为"+name+"的活动");
        return  "success";
    }
}
