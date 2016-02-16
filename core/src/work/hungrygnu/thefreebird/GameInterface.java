package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 16.02.16.
 */
public class GameInterface extends InputAdapter {

    GameScreen screen;

    public GameInterface(GameScreen screen){

        Gdx.input.setInputProcessor(this);
        this.screen = screen;
    }

    public boolean keyUp(int keycode){

        if (keycode == Input.Keys.SPACE) screen.closeUpView = !screen.closeUpView;

        return true;
    }
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        switch (getScreenZone(screenX, screenY, screen.viewportClose.getScreenWidth(), screen.viewportClose.getScreenHeight())){
            case LEFT:
                birdGlideLeft();
                break;
            case RIGHT:
                birdGlideRight();
                break;
            case TOP:
                screen.bird.visible = true;
                screen.bird.flyUP();
                break;
            case BOTTOM:
                ; // Make poop;
        }
        return  true;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        switch (getScreenZone(screenX, screenY, screen.viewportClose.getScreenWidth(), screen.viewportClose.getScreenHeight())){
            case LEFT:
                birdGlideLeft();
                break;
            case RIGHT:
                birdGlideRight();
                break;
            case TOP:
                ; // DO NOTHING ON DRAG;
                break;
            case BOTTOM:
                ; // DO NOTHING ON DRAG;
        }
        return  true;
    }

    public ScreenZone getScreenZone(int x, int y, int screenWidth, int screenHeight)
    {
        Gdx.app.log("SCREEN ZONE", "x = "+x+" y = "+y+" w = " + screenWidth+" h = " + screenHeight);

        if (x < screenWidth / 3f)
            return ScreenZone.LEFT;
        else if (x > screenWidth * 2f / 3f)
            return ScreenZone.RIGHT;
        else if (y < Gdx.graphics.getHeight() / 2f)
            return  ScreenZone.TOP;
        else
            return ScreenZone.BOTTOM;
    }

    public void birdGlideRight(){
        screen.bird.velocity.x = BIRD_FLY_X_SPEED;
        screen.bird.isGlyding = true;
    }
    public void birdGlideLeft(){
        screen.bird.velocity.x = -BIRD_FLY_X_SPEED;
        screen.bird.isGlyding = true;
    }



    public enum ScreenZone{

        LEFT,
        RIGHT,
        TOP,
        BOTTOM

    }


}
