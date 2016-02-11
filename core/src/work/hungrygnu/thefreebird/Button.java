package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Button {

    private ShapeRenderer renderer;
    public final Vector2 position;

    public Button(ShapeRenderer renderer, Vector2 position){
        this.renderer = renderer;
        this.position = position;

    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(BUTTON_COLOR);
        renderer.circle(position.x, position.y, BUTTON_RADIUS);
    }
}
