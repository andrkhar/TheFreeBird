package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Cat extends FacingDeDyObject {

    public Cat(ShapeRenderer renderer, Boolean facingRight) {

        super(renderer, new Vector2(), facingRight);
        if (facingRight) {
            position.set(CAT_START_X_LEFT, SKY_Y);

            velocity.set(CAT_SPEED_X, 0f);
        }
        else{
            position.set(CAT_START_X_RIGHT, SKY_Y);
            velocity.set(-CAT_SPEED_X, 0f);
        }
    }


    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(CAT_COLOR_BODY);
        renderer.circle(position.x, position.y, CAT_BODY_LENGTH);

    }

    public void update(float delta){

        position.mulAdd(velocity, delta);

        if (position.x < 0) {
            facingRight = false;
            if (velocity.x < 0)
                velocity.x *= -1;
        }
        else if(position.x > WORLD_WIDTH) {
            facingRight = true;
            if (velocity.x > 0)
                velocity.x *= -1;
        }

    }

    public Boolean hasCollisionWith(Poop poop){
        Circle catCircle = new Circle(position.x,position.y, CAT_BODY_LENGTH);
        return catCircle.contains(poop.position);
    }

    public Boolean hasCollisionWith(Bird bird){
        return false;
    }
}
