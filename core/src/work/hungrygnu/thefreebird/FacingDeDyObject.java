package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.WORLD_WIDTH;

/**
 * Created by hungry on 18.02.16.
 */
public class FacingDeDyObject extends DestructibleDynamicObject {
    protected boolean facingRight;
    public FacingDeDyObject(ShapeRenderer renderer, Vector2 position, Boolean facingRight) {
        super(renderer, position);
        this.facingRight = facingRight;
    }

    public Boolean hasCollisionWith(Poop poop){
        return false;
    }

    public Boolean hasCollisionWith(Bird bird){
        return false;
    }

    void respectBorders(float offBorderDistance){
        if (position.x < -offBorderDistance) {
            facingRight = false;
            if (velocity.x < 0)
                velocity.x *= -1;
        }
        else if(position.x > WORLD_WIDTH+offBorderDistance) {
            facingRight = true;
            if (velocity.x > 0)
                velocity.x *= -1;
        }
    }
}
