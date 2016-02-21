package work.hungrygnu.thefreebird.beings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Cat extends FacingDeDyObject {
    // TODO: Draw the cat
    // TODO: Make the cat jump when it is near the bird

    private long catCreateMillis;

    public Cat(ShapeRenderer renderer, Boolean facingRight) {

        super(renderer, new Vector2(), facingRight);

        if (facingRight) {
            position.set(CAT_START_X_LEFT, CAT_Y);

            velocity.set(CAT_SPEED_X, 0f);
        }
        else{
            position.set(CAT_START_X_RIGHT, CAT_Y);
            velocity.set(-CAT_SPEED_X, 0f);
        }

        catCreateMillis = TimeUtils.millis();
    }


    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(CAT_COLOR_BODY);
        renderer.circle(position.x, position.y, CAT_BODY_RADIUS);
        rendrerFacing();

    }

    public void update(float delta){

        position.mulAdd(velocity, delta);

        respectBorders(2 * CAT_BODY_RADIUS);



        position.y = CAT_Y + CAT_WALK_AMP *
                CAT_BODY_RADIUS * getAnimationCoefficient();
    }

    public Boolean hasCollisionWith(Poop poop){
        Circle catCircle = new Circle(position.x,position.y, CAT_BODY_RADIUS);
        return catCircle.contains(poop.position);
    }

    public Boolean hasCollisionWith(Bird bird){
        Circle catCircle = new Circle(position.x,position.y, CAT_BODY_RADIUS);
        return catCircle.contains(bird.position);
    }

    public void rendrerFacing(){
        float tailW = CAT_BODY_RADIUS /7f;
        float tailX;
        float tailY = position.y + CAT_BODY_RADIUS *1.5f;
        float eyeX;
        float eyeY = position.y + CAT_BODY_RADIUS/3f;
        float noseX;
        float mouthX;
        float mouthY = position.y - CAT_BODY_RADIUS/5f;
        float eyeR = CAT_BODY_RADIUS / 7f;
        float pupilR = eyeR/3f;
        float pupilX;

        if (facingRight){
            tailX = position.x - CAT_BODY_RADIUS + tailW/3f;
            eyeX = position.x + CAT_BODY_RADIUS/3f;
            noseX = position.x + 1.05f*CAT_BODY_RADIUS;
            mouthX = position.x + 0.9f*CAT_BODY_RADIUS;
            pupilX = eyeX + pupilR;

        }
        else {
            tailX = position.x + CAT_BODY_RADIUS - tailW/3f;
            eyeX = position.x - CAT_BODY_RADIUS/3f;
            noseX = position.x - 1.05f*CAT_BODY_RADIUS;
            mouthX = position.x - CAT_BODY_RADIUS;
            pupilX = eyeX - pupilR;

        }

        float animCoefficient = getAnimationCoefficient();

        // TAIL
        renderer.rectLine(tailX, position.y, tailX + 0.3f*tailW *animCoefficient, tailY, tailW);

        // EAR
        float earOffsetX = CAT_BODY_RADIUS /4f;
        float earHeight = CAT_BODY_RADIUS /2f;
        float earBaseY = position.y + 0.9f* CAT_BODY_RADIUS;


        renderer.triangle(position.x - earOffsetX, earBaseY,
                position.x + earOffsetX, earBaseY,
                position.x, earBaseY + earHeight);


        // EYE
        renderer.setColor(Color.WHITE);
        renderer.circle(eyeX, eyeY, eyeR);

        // PUPIL
        renderer.setColor(Color.PURPLE);
        renderer.circle(pupilX + 0.4f*pupilR * animCoefficient , eyeY, pupilR);

        // NOSE
        renderer.setColor(Color.PINK);
        renderer.circle(noseX, position.y, CAT_BODY_RADIUS / 6f);

        // MOUTH
        renderer.setColor(Color.LIGHT_GRAY);
        //renderer.rectLine(position.x, position.y, mouthX, mouthY, tailW / 2);
        renderer.triangle(position.x, position.y, mouthX, mouthY, mouthX, mouthY - tailW/3 - animCoefficient*tailW/3);

    }

    private float getAnimationCoefficient(){
        return MathUtils.sin(MathUtils.PI2 * TimeUtils.timeSinceMillis(catCreateMillis) / CAT_WALK_PERIOD);
    }
}
