package work.hungrygnu.thefreebird.beings;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import work.hungrygnu.thefreebird.Updatable;

/**
 * Created by hungry on 14.02.16.
 */
public class DynamicGameObject extends StaticGameObject implements Updatable {

    public Vector2 velocity;
    public Vector2 lastFramePosition;
    public long nanotimeAnimationStart;



    public DynamicGameObject(ShapeRenderer renderer, Vector2 position){
        // static object constructor call
        super(renderer, position);
        // dinamic object specific parameters initialisation
        this.lastFramePosition = new Vector2(position);
        this.velocity = new Vector2();

    }

    // Assign correct values for drawing shapes of the complex object
    public void recalculatePoints(){
    }

    @Override
    public void update(float delta) {
        recalculatePoints();
    }

}
