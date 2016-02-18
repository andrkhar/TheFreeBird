package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 14.02.16.
 */
public class DynamicGameObject extends StaticGameObject implements Updatable{

    protected Vector2 velocity;
    protected Vector2 lastFramePosition;



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
