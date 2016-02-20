package work.hungrygnu.thefreebird.beings;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import work.hungrygnu.thefreebird.Renderable;

/**
 * Created by hungry on 13.02.16.
 */
public class StaticGameObject implements Renderable {
    // Static Object parameters
    protected ShapeRenderer renderer;
    public Vector2 position;

    public StaticGameObject(ShapeRenderer renderer, Vector2 position){
        this.renderer = renderer;
        this.position = new Vector2(position);
    }

    @Override
    public void render() {

    }
}
