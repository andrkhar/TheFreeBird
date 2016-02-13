package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 13.02.16.
 */
public class StaticDrawable implements Renderable{
    protected ShapeRenderer renderer;
    public final Vector2 position;

    public StaticDrawable(ShapeRenderer renderer, Vector2 position){
        this.renderer = renderer;
        this.position = position;
    }

    @Override
    public void render() {

    }
}
