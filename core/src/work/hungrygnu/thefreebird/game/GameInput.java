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
    private ScreenZone[] screenZones;



    public GameInput(GameScreen screen, Bird bird){

        Gdx.input.setInputProcessor(this);
        this.bird = bird;
        this.screen = screen;

        screenZones = new ScreenZone[MAX_NUMBER_OF_FINGERS_PLAYER_HAS_TO_PLAY];

    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        ScreenZone touchedZone = getScreenZone(screenX, screenY);
        screenZones[pointer] = touchedZone;
        if(touchedZone == ScreenZone.BOTTOM)
            bird.askToStartDropPoop();
        else updateOrders();
        return  true;
    }

    private boolean notTheSameZone(ScreenZone zone, int pointer){
        return screenZones[pointer] != zone;
    }

    // TODO: 9/14/2016 if bird moving left but you drag to not left zone and up
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        ScreenZone touchedZone = getScreenZone(screenX, screenY);
        if(notTheSameZone(touchedZone, pointer)) {
            screenZones[pointer] = touchedZone;
            updateOrders();

        }
        return  true;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        //ScreenZone touchedZone = getScreenZone(screenX, screenY);
        screenZones[pointer] = null;
        updateOrders();
        return  true;
    }

    private void updateOrders(){
        boolean left = false;
        boolean right = false;
        boolean up = false;
        for (ScreenZone zone : screenZones){
            if (zone != null)
                switch (zone){
                    case LEFT:
                        left = true;
                        break;
                    case RIGHT:
                        right = true;
                        break;
                    case TOP:
                        up = true;
                        break;
                }
        }
        bird.askToStopFlyUp();
        bird.askToStopMoveX();
        if(left && !right)
            bird.askToStartMoveX(DIRECTION_LEFT);
        else if(right && !left)
            bird.askToStartMoveX(DIRECTION_RIGHT);
        if(up)
            bird.askToStartFlyUp();
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
}
