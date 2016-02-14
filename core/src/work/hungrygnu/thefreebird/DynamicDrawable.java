package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 14.02.16.
 */
public class DynamicDrawable implements Renderable, Updatable{

    protected ShapeRenderer renderer;
    public Vector2 position;



    public DynamicDrawable(ShapeRenderer renderer, Vector2 position){
        this.renderer = renderer;
        this.position = position;
    }

    public void recalculateVectors(){

    }

    @Override
    public void update(float delta) {
        recalculateVectors();
    }

    @Override
    public void render() {

    }


}
