package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 18.02.16.
 */
public class DestructibleDynamicObject extends  DynamicGameObject{

    protected boolean active;

    public DestructibleDynamicObject(ShapeRenderer renderer, Vector2 position) {
        super(renderer, position);
        active = true;

    }

    public Boolean hasCollisionWith(Poop poop){
        return false;
    }

    public Boolean hasCollisionWith(Bird bird){
        return false;
    }
}
