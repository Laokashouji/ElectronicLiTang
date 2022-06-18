package edu.eleclt.repository;

import edu.map.SchoolMap;

public class MapRepository {
    private static SchoolMap map1 = new SchoolMap("src/main/resources/static/data/map/spot1.txt",
            "src/main/resources/static/data/map/map1.txt", 1);
    private static SchoolMap map2 = new SchoolMap("src/main/resources/static/data/map/spot2.txt",
            "src/main/resources/static/data/map/map2.txt", 2);


    public Object[] findByLength(String source, String target, Integer school) {
        if(school == 1){
            return map1.dijkstra(source, target);
        }
        else if(school == 2){
            return map2.dijkstra(source, target);
        }
        return null;
    }

    public Object[] findByTime(String source, String target, Integer school) {
        if(school == 1){
            return map1.dijkstra2(source, target);
        }
        else if(school == 2){
            return map2.dijkstra2(source, target);
        }
        return null;
    }

    public Object[] findByVehicle(String source, String target, Integer school) {
        if(school == 1){
            return map1.dijkstra3(source, target);
        }
        else if(school == 2){
            return map2.dijkstra3(source, target);
        }
        return null;
    }
}
