package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.datastructure.MyDate;
import edu.datastructure.StringUtil;
import edu.eleclt.entity.Activity;
import edu.eleclt.loader.Loader;

import java.util.List;
import java.util.Map;

public class ActivityRepository {

    //private static MyArrayList<Activity> activities = new MyArrayList<>();

    private static MyArrayList<Activity> activities = Loader.loadActivity("src/main/resources/static/data/Activity.dat",
            "src/main/resources/static/data/ActivityNum.txt");

    public static MyArrayList<Activity> findAll() {
        return activities;
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void addActivity(String name, String place, Map<String, Object> times, String type, String tag) {
        String formatTime = (String) times.get("formatTime");
        MyDate date = new MyDate((List<String>) times.get("rowTime"), formatTime);
        activities.add(new Activity(name, place, date, type, tag));
    }

    public static int size() {
        return activities.size();
    }

    public static MyArrayList<Activity> searchByName(String name) {
        MyArrayList<Activity> list = new MyArrayList<>();
        for (int i = 0; i < activities.size(); i++) {
            if (StringUtil.KMP(activities.get(i).getName(), name, 0) != -1) {
                list.add(activities.get(i));
            }
        }
        return list;
    }

    public static MyArrayList<Activity> searchByTag(String tag) {
        MyArrayList<Activity> list = new MyArrayList<>();
        for (int i = 0; i < activities.size(); i++) {
            if (StringUtil.KMP(activities.get(i).getTag(), tag, 0) != -1) {
                list.add(activities.get(i));
            }
        }
        return list;
    }

    public static MyArrayList<Activity> searchByType(String type) {
        MyArrayList<Activity> list = new MyArrayList<>();
        for (int i = 0; i < activities.size(); i++) {
            if (StringUtil.KMP(activities.get(i).getType(), type, 0) != -1) {
                list.add(activities.get(i));
            }
        }
        return list;
    }

    public static void save() {
        Loader.download("src/main/resources/static/data/ActivityNum.txt", activities.size());
        Loader.download("src/main/resources/static/data/Activity.dat", activities);
    }
}
