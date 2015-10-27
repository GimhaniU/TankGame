package Parser;

import sun.util.resources.cldr.ms.CalendarData_ms_MY;

/**
 * Created by Gimhani on 10/27/2015.
 */
public class MapUpdater extends Thread {

    private Map map;

    public MapUpdater(Map map){

        this.map=map;

    }
    public void setMap(Map map){
        this.map=map;

    }

    @Override
    public void run() {
        while (true) {
            map.Update();
        }
    }
}
