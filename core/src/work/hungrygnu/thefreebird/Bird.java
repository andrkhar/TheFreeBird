package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Bird extends DynamicDrawable{

    private Vector2 beakL;
    private Vector2 beakR;
    private Vector2 beakB;

    private Vector2 eyeL;
    private Vector2 eyeR;

    private Vector2 tailL;
    private Vector2 tailR;

    private Vector2 wingLL;
    private Vector2 wingLB;
    private Vector2 wingLT;

    private Vector2 wingRR;
    private Vector2 wingRB;
    private Vector2 wingRT;

    private final float bodyRadius;
    private final float eyeRadius;

    private boolean isFlying;

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

        isFlying = true;
        recalculateVectors();
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
