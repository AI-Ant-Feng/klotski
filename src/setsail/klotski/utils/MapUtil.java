package setsail.klotski.utils;

import setsail.klotski.entites.MapFactory;
import setsail.klotski.entites.Node;

public class MapUtil {

    public static Node[] getMap(int level){
        return MapFactory.getMap(level);
    }

    public static int getRecord(int level){
        return MapFactory.getRecord(level);
    }

    public static void updateRecord(int level, int record){
        MapFactory.writeRecord(level, record);
    }
}
