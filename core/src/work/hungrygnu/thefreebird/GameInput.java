package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 16.02.16.
 */
public class GameInput extends InputAdapter {
    // TODO: I do not want to overload the interface and think about Desktop Interface after succesful release on Android platform.


    GameScreen screen;
    Bird bird;
    Level level;

    public GameInput(GameScreen screen, Level level){

        Gdx.input.setInputProcessor(this);
        bird = level.bird;
        this.screen = screen;
        this.level = level;
    }

    public boolean keyUp(int keycode){

        if (keycode == Input.Keys.SPACE) screen.closeUpView = !screen.closeUpView;

        return true;
    }
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        switch (getScreenZone(screenX, screenY)){
            case LEFT:
                bird.glideLeft();
                break;
            case RIGHT:
                bird.glideRight();
                break;
            case TOP:
                bird.flyUP();
                break;
            case BOTTOM:
                level.poops.add(new Poop(level)); // Make poop;
        }
        return  true;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        switch (getScreenZone(screenX, screenY)){
            case LEFT:
                bird.glideLeft();
                break;
            case RIGHT:
                bird.glideRight();
                break;
            case TOP:
                ; // DO NOTHING ON DRAG;
                break;
            case BOTTOM:
                ; // DO NOTHING ON DRAG;
        }
        return  true;
    }

    public ScreenZone getScreenZone(int x, int y){
        int screenW = Gdx.graphics.getWidth();
        int screenH = Gdx.graphics.getHeight();
        if (x < screenW / 3f)
            return ScreenZone.LEFT;
        else if (x > screenW * 2f / 3f)
            return ScreenZone.RIGHT;
        else if (y < screenH / 2f)
            return  ScreenZone.TOP;
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
