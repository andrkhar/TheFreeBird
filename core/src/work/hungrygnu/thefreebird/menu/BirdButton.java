package work.hungrygnu.thefreebird.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import work.hungrygnu.thefreebird.beings.Bird;
import work.hungrygnu.thefreebird.game.Level;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 15.02.16.
 */
public class BirdButton {

    public float alpha;
    public boolean visible;
    public Button button;
    public Bird bird;


    public BirdButton(Vector2 position, Level level) {
        bird = new Bird(position, level);
        alpha = 0f;
        visible = false;
        button = new Button(bird.getRenderer(), position);
        button.radius = BIRD_BODY_RADIUS;
    }

    public void update(float delta){
        bird.update(delta);
        bird.energy = BIRD_ENERGY_MAX;
        if (visible && alpha < 1){
            alpha += delta;
            button.colorBody.a -= delta;
            button.colorText.a -= delta;
        }
        if (alpha > 1f) alpha = 1f;
        if (button.colorBody.a < 0f) button.colorBody.a = 0f;
        if (button.colorText.a < 0f) button.colorText.a = 0f;

        button.position.set(bird.position);
        bird.setAlpha(alpha);
    }

    public void render() {
        bird.render();
        button.render();
    }
}
