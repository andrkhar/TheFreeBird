package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 13.02.16.
 */
public class Drawable {
    protected ShapeRenderer renderer;
    public Vector2 position;

    public Drawable(ShapeRenderer renderer, Vector2 position){
        this.renderer = renderer;
        this.position = position;
    }
}
