package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 12.02.16.
 */
public class Caterpillar extends FacingDeDyObject {
    // Caterpillar specific parameters -------
    private Array<Circle> circles; // For collision detection
    //  ---------

    public Caterpillar(ShapeRenderer renderer, Vector2 position,boolean facingRight) {
        super(renderer, position, facingRight);
        float x = position.x - 4 *CATERPILLAR_TIGHT_RADIUS;
        float y = position.y;

        circles = new Array<Circle>();
        for ( int i = 0 ; i< CATERPILLAR_CIRCLES_NUMBER; i++) {
            circles.add(new Circle(x, y, CATERPILLAR_RADIUS));
            x += 2 * CATERPILLAR_TIGHT_RADIUS;
        }

        if (facingRight) {
            velocity.set(CATERPILLAR_SPEED_X, 0f);
        }
        else{
            velocity.set(-CATERPILLAR_SPEED_X, 0f);
        }

        nanotimeAnimationStart = TimeUtils.nanoTime();
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(CATERPILLAR_COLOR_BODY);
        for (Circle circle : circles)
            renderer.circle(circle.x, circle.y, circle.radius, POOP_SEGMENTS);

    }

    public void update(float delta){
        position.mulAdd(velocity, delta);
        respectBorders(2 * CATERPILLAR_BODY_LENGTH);
        updateCircles();

    }

    public void updateCircles(){

        float x = position.x - 4 *CATERPILLAR_TIGHT_RADIUS;
        float ydelta = CATERPILLAR_Y_DELTA *
                MathUtils.cos(MathUtils.PI2 *
                        TimeUtils.timeSinceNanos(nanotimeAnimationStart)/CATERPILLAR_NANOTIME_FRAME);
        float y = position.y + ydelta;


        for ( int i = 0 ; i< CATERPILLAR_CIRCLES_NUMBER; i++) {
            circles.get(i).setPosition(x, y);
            x += 2 * CATERPILLAR_TIGHT_RADIUS;
            ydelta = - ydelta;
            y = position.y + ydelta;
        }
    }



    public Boolean hasCollisionWith(Poop poop){
        for (Circle circle : circles)
            if (circle.contains(poop.position))
                return true;
        return false;
    }

    @Override
    public Boolean hasCollisionWith(Bird bird){
        for (Circle circle : circles)
            if (bird.bodyCircle.overlaps(circle))
                return true;
        return false;
    }
}
