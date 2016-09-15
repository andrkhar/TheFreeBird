package work.hungrygnu.thefreebird.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import work.hungrygnu.thefreebird.beings.Bird;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 16.02.16.
 */
public class GameInput extends InputAdapter {
    // TODO: LOW Improve Desktop Interface.


    private GameScreen screen;
    private Bird bird;
    private ScreenZone[] pointersScreenZones;
    private boolean[] pointers;


    public GameInput(GameScreen screen, Bird bird){

        Gdx.input.setInputProcessor(this);
        this.bird = bird;
        this.screen = screen;

        pointersScreenZones = new ScreenZone[MAX_NUMBER_OF_FINGERS_PLAYER_HAS_TO_PLAY];
        pointers = new boolean[MAX_NUMBER_OF_FINGERS_PLAYER_HAS_TO_PLAY];

    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        ScreenZone touchedZone = getScreenZone(screenX, screenY);
        pointersScreenZones[pointer] = touchedZone;
        PointersOnZonesManager.add(touchedZone);
        switch (touchedZone){
            case LEFT:
                bird.askToStartMoveX(DIRECTION_LEFT);
                break;
            case RIGHT:
                bird.askToStartMoveX(DIRECTION_RIGHT);
                break;
            case TOP:
                bird.askToStartFlyUp();
                break;
            case BOTTOM:
                bird.askToStartDropPoop();
        }
        return  true;
    }

    // TODO: 9/14/2016 if bird moving left but you drag to not left zone and up
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        ScreenZone touchedZone = getScreenZone(screenX, screenY);
        if(pointersScreenZones[pointer] != touchedZone) {
            PointersOnZonesManager.sub(pointersScreenZones[pointer]);
            ifHasNoPointersOnZoneAskToStopMoveX(touchedZone);
            ifHasNoPointersOnZoneAskToStopFlyUp(touchedZone);
            pointersScreenZones[pointer] = touchedZone;
            PointersOnZonesManager.add(touchedZone);
            switch (touchedZone) {
                case LEFT:
                    bird.askToStartMoveX(DIRECTION_LEFT);
                    break;
                case RIGHT:
                    bird.askToStartMoveX(DIRECTION_RIGHT);
                    break;
                case TOP:
                    bird.askToStartFlyUp();
                    break;
            }
        }
        return  true;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        ScreenZone touchedZone = getScreenZone(screenX, screenY);
        PointersOnZonesManager.sub(touchedZone);
        switch (touchedZone){
            case LEFT:
            case RIGHT:
                ifHasNoPointersOnZoneAskToStopMoveX(touchedZone);
                break;
            case TOP:
                ifHasNoPointersOnZoneAskToStopFlyUp(touchedZone);
        }
        return  true;
    }

    public boolean keyDown(int keycode){
        switch (keycode){
            case Input.Keys.LEFT:
                bird.askToStartMoveX(DIRECTION_LEFT);
                break;
            case Input.Keys.RIGHT:
                bird.askToStartMoveX(DIRECTION_RIGHT);
                break;
            case Input.Keys.UP:
                bird.askToStartFlyUp();
                break;
            case Input.Keys.DOWN:
                bird.askToStartDropPoop();
                break;

        }
        return true;
    }

    public boolean keyUp(int keycode){

        switch (keycode){
            case Input.Keys.SPACE:
                screen.closeUpView = !screen.closeUpView;
                break;
            case Input.Keys.LEFT:
                bird.askToStopMoveX();
                break;
            case Input.Keys.RIGHT:
                bird.askToStopMoveX();
                break;
            case Input.Keys.UP:
                bird.askToStopFlyUp();
                break;
        }

        return true;
    }

    public ScreenZone getScreenZone(int x, int y){
        int screenW = Gdx.graphics.getWidth();
        int screenH = Gdx.graphics.getHeight();
        if (y < screenH / 2f)
            return  ScreenZone.TOP;
        else if (x > screenW * 2f / 3f)
            return ScreenZone.RIGHT;
        else if (x < screenW / 3f)
            return ScreenZone.LEFT;
        else
            return ScreenZone.BOTTOM;
    }

    private void ifHasNoPointersOnZoneAskToStopMoveX(ScreenZone touchedZone){
        if (!PointersOnZonesManager.zoneHasPointers(touchedZone))
            bird.askToStopMoveX();
    }

    private void ifHasNoPointersOnZoneAskToStopFlyUp(ScreenZone touchedZone){
        if (!PointersOnZonesManager.zoneHasPointers(touchedZone))
            bird.askToStopFlyUp();
    }

    public enum ScreenZone{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }
}
