package edu.eleclt.repository;

import edu.datastructure.MyArrayList;
import edu.eleclt.entity.Clock;

public class ClockRepository {

    private static MyArrayList<Clock> clocks = new MyArrayList<>();

    public static MyArrayList<Clock> findAll() {
        return clocks;
    }

    public static MyArrayList<Clock> addClock(){

        return null;
    }
}
