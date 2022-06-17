package edu.eleclt.controller;

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
        return activityRepository.findAll().getAll();
    }

    @GetMapping("/searchByName/{Name}")
    public Object[] searchByName(@PathVariable("Name") String Name) {
        return activityRepository.searchByName(Name).getAll();
    }
    @GetMapping("/searchByTag/{Tag}")
    public Object[] searchByTag(@PathVariable("Tag") String Tag) {
        return activityRepository.searchByTag(Tag).getAll();
    }
    @GetMapping("/searchByType/{Type}")
    public Object[] searchByType(@PathVariable("Type") String Type) {
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

        return  "success";
    }
}
