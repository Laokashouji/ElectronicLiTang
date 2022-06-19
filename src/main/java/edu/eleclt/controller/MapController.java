package edu.eleclt.controller;

import edu.eleclt.loader.Log;
import edu.eleclt.repository.MapRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Map")
public class MapController {

    public MapRepository mapRepository = new MapRepository();

    @GetMapping("/findByLength/{source}/{target}/{school}")
    public Object[] findByLength(@PathVariable("source") String source, @PathVariable("target") String target
            , @PathVariable("school") Integer school) {
        Log.write("查询"+source+"到"+target+"的最短路");
        return mapRepository.findByLength(source, target, school);
    }
    @GetMapping("/findByTime/{source}/{target}/{school}")
    public Object[] findByTime(@PathVariable("source") String source, @PathVariable("target") String target
            , @PathVariable("school") Integer school) {
        Log.write("查询"+source+"到"+target+"的最短时间");
        return mapRepository.findByTime(source, target, school);
    }
    @GetMapping("/findByVehicle/{source}/{target}/{school}")
    public Object[] findByVehicle(@PathVariable("source") String source, @PathVariable("target") String target
            , @PathVariable("school") Integer school) {
        Log.write("查询"+source+"到"+target+"的最短交通工具时间");
        return mapRepository.findByVehicle(source, target, school);
    }

    @GetMapping("/getPlace")
    public Object[] getPlace() {
        return mapRepository.getPlace();
    }
}
