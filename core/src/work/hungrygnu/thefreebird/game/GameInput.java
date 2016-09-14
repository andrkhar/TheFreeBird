package work.hungrygnu.thefreebird.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 16.02.16.
 */
public class GameInput extends InputAdapter {
    // TODO: LOW Improve Desktop Interface.


    GameScreen screen;
    work.hungrygnu.thefreebird.beings.Bird bird;
    Level level;

    public GameInput(GameScreen screen, Level level){

        Gdx.input.setInputProcessor(this);
        bird = level.bird;
        this.screen = screen;
        this.level = level;
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        switch (getScreenZone(screenX, screenY)){
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

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        switch (getScreenZone(screenX, screenY)){
            case LEFT:
                bird.askToStopMoveX();
                break;
            case RIGHT:
                bird.askToStopMoveX();
                break;
            case TOP:
                bird.askToStopFlyUp();
                break;
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


    // TODO: 9/14/2016 if bird moving left but you drag to not left zone and up 
//    public boolean touchDragged (int screenX, int screenY, int pointer) {
//        switch (getScreenZone(screenX, screenY)){
//            case LEFT:
//                bird.moveLeft();
//                break;
//            case RIGHT:
//                bird.moveRight();
//                break;
//            case TOP:
//                ; // DO NOTHING ON DRAG;
//                break;
//            case BOTTOM:
//                ; // DO NOTHING ON DRAG;
//        }
//        return  true;
//    }

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

    public enum ScreenZone{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }
}
