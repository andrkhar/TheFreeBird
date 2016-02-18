package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 12.02.16.
 */
public class Cat extends FacingDeDyObject {
    public Cat(ShapeRenderer renderer, Vector2 position, Boolean facingRight) {
        super(renderer, position, facingRight);
    }

    public void render(){

    }

    public Boolean hasCollisionWith(Poop poop){
        return false;
    }

    public Boolean hasCollisionWith(Bird bird){
        return false;
    }
}
