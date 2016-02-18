package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 18.02.16.
 */
public class FacingDeDyObject extends DestructibleDynamicObject {
    protected boolean facingRight;
    public FacingDeDyObject(ShapeRenderer renderer, Vector2 position, Boolean facingRight) {
        super(renderer, position);
        this.facingRight = facingRight;
    }

}
