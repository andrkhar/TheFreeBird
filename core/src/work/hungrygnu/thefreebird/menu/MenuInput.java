package work.hungrygnu.thefreebird.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import work.hungrygnu.thefreebird.Assets;

/**
 * Created by hungry on 17.02.16.
 */
public class MenuInput extends InputAdapter {
    BirdButton birdButton;
    FitViewport viewport;
    public MenuInput(BirdButton birdButton, FitViewport viewport)
    {
        Gdx.input.setInputProcessor(this);
        this.birdButton = birdButton;
        this.viewport = viewport;
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        Vector2 unprojected = viewport.unproject(new Vector2(screenX, screenY));
        if (birdButton.bird.bodyCircle.contains(unprojected)) {
            startGame();
        }
        return true;
    }

    public boolean keyDown (int keycode){
        if(keycode == Input.Keys.UP)
            startGame();
        return true;
    }

    private void startGame(){
        birdButton.bird.askToStartFlyUp();
        birdButton.visible = true;
        Assets.soundKarr.play(0.4f);
        Assets.music.play();
    }
}
