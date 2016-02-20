package work.hungrygnu.thefreebird.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import work.hungrygnu.thefreebird.menu.BirdButton;

/**
 * Created by hungry on 17.02.16.
 */
public class MenuInput extends InputAdapter {
    BirdButton bird;
    FitViewport viewport;
    public MenuInput(BirdButton bird, FitViewport viewport)
    {
        Gdx.input.setInputProcessor(this);
        this.bird = bird;
        this.viewport = viewport;
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        Vector2 unprojected = viewport.unproject(new Vector2(screenX, screenY));
        if (bird.bodyCircle.contains(unprojected)) {
            bird.flyUP();
            bird.visible = true;
        }

        return true;
    }


}
