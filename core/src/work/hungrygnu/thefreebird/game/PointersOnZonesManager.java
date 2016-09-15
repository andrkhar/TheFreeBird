package work.hungrygnu.thefreebird.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by a.harchenko on 9/15/2016.
 */
public final class PointersOnZonesManager {
    private static final int[] zones = new int[3];
    private PointersOnZonesManager(){
    }

    public static void add(GameInput.ScreenZone zone){
        switch (zone){
            case LEFT:
                zones[0]++;
                Gdx.app.log(zone.name(), ""+zones[0]);
            case RIGHT:
                zones[1]++;
                Gdx.app.log(zone.name(), ""+zones[1]);
            case TOP:
                zones[2]++;
                Gdx.app.log(zone.name(), ""+zones[2]);
            default:
                return;
        }
    }

    public static void sub(GameInput.ScreenZone zone){
        switch (zone){
            case LEFT:
                zones[0]--;
                Gdx.app.log(zone.name(), ""+zones[0]);
            case RIGHT:
                zones[1]--;
                Gdx.app.log(zone.name(), ""+zones[1]);
            case TOP:
                zones[2]--;
                Gdx.app.log(zone.name(), ""+zones[2]);
            default:
                return;
        }
    }

    public static boolean zoneHasPointers(GameInput.ScreenZone zone){
        switch (zone){
            case LEFT:
                return zones[0]>0;
            case RIGHT:
                return zones[1]>0;
            case TOP:
                return zones[2]>0;
            default:
                return false;
        }
    }
}
