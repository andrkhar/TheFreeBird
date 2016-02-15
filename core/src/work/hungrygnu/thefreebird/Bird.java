package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Bird extends DynamicDrawable{

    protected Vector2 velocity;

    protected Vector2 beakB;

    protected Vector2 eyeL;
    protected Vector2 eyeR;

    protected Vector2 tailL;
    protected Vector2 tailR;

    protected Vector2 wingLL;
    protected Vector2 wingLB;
    protected Vector2 wingLT;

    protected Vector2 wingRR;
    protected Vector2 wingRB;
    protected Vector2 wingRT;

    protected final float bodyRadius;
    protected final float eyeRadius;

    protected boolean isFlying;

    public Bird(ShapeRenderer renderer, Vector2 position) {
        super(renderer, position);

        beakB = new Vector2();
        eyeL = new Vector2();
        eyeR = new Vector2();
        tailL = new Vector2();
        tailR = new Vector2();
        wingLL = new Vector2();
        wingLB = new Vector2();
        wingLT = new Vector2();
        wingRR = new Vector2();
        wingRB = new Vector2();
        wingRT = new Vector2();

        bodyRadius = 5f * BIRD_SCALE;
        eyeRadius = 0.7f *BIRD_SCALE;

        isFlying = false;
        recalculateVectors();
        velocity = new Vector2();
    }

    @Override
    public void recalculateVectors() {

        beakB.set(position).add(0f, -2f * BIRD_SCALE);

        eyeL.set(position).add(-1.8f * BIRD_SCALE, 1.5f * BIRD_SCALE);
        eyeR.set(position).add(1.8f * BIRD_SCALE, 1.5f * BIRD_SCALE);

        wingLT.set(position).add(-4.5f * BIRD_SCALE, 2f * BIRD_SCALE);
        wingRT.set(position).add(4.5f * BIRD_SCALE, 2f * BIRD_SCALE);

        if (isFlying) {
            wingLB.set(wingLT).add(-0.5f *BIRD_SCALE, -2f * BIRD_SCALE);
            wingRB.set(wingRT).add(0.5f *BIRD_SCALE, -2f * BIRD_SCALE);

            wingLL.set(wingLT.x - 6f * BIRD_SCALE, wingLB.y);
            wingRR.set(wingRT.x + 6f * BIRD_SCALE, wingRB.y);

            tailL.set(position.x - 3f * BIRD_SCALE, position.y + 5.5f * BIRD_SCALE);
            tailR.set(position.x + 3f * BIRD_SCALE, position.y + 5.5f * BIRD_SCALE);

        }
        else {
            wingLB.set(wingLT).add(-0.5f *BIRD_SCALE, -3f * BIRD_SCALE);
            wingRB.set(wingRT).add(0.5f *BIRD_SCALE, -3f * BIRD_SCALE);

            wingLL.set(wingLT.x - 2f * BIRD_SCALE, wingLT.y - (wingLT.y - wingLB.y) / 2f);
            wingRR.set(wingRT.x + 2f * BIRD_SCALE, wingRT.y - (wingRT.y - wingRB.y) / 2f);

            tailL.set(beakB);
            tailR.set(beakB);
        }

    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (isFlying){
            if (position.y > LAND_HEIGHT) {
                velocity.add(0f, delta * 100 * GRAVITY / BIRD_WINDAGE);
                position.add(velocity.scl(delta*10));// // TODO: 15.02.16  
            }
            else{
                isFlying = false;
                position.y = LAND_HEIGHT;
            }

        }

    }

    @Override
    public void render() {
        super.render();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        // TAIL
        renderer.setColor(BIRD_COLOR_TAIL);
        renderer.triangle(tailL.x, tailL.y, tailR.x, tailR.y, beakB.x, beakB.y);
        // BODY
        renderer.setColor(BIRD_COLOR_BODY);
        renderer.circle(position.x, position.y, bodyRadius, BIRD_SEGMENTS);
        // BEAK
        renderer.triangle(eyeL.x, eyeL.y, eyeR.x, eyeR.y, beakB.x, beakB.y, BIRD_COLOR_BODY, BIRD_COLOR_BODY, BIRD_COLOR_BEAK);
        // EYES
        renderer.setColor(BIRD_COLOR_EYE);
        renderer.circle(eyeL.x, eyeL.y, eyeRadius, BIRD_SEGMENTS);
        renderer.circle(eyeR.x, eyeR.y, eyeRadius, BIRD_SEGMENTS);
        // WINGS
        renderer.setColor(BIRD_COLOR_WINGS);
        renderer.triangle(wingLT.x, wingLT.y, wingLB.x, wingLB.y, wingLL.x, wingLL.y);
        renderer.triangle(wingRT.x, wingRT.y, wingRB.x, wingRB.y, wingRR.x, wingRR.y);

    }
}
