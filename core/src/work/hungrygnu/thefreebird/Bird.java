package work.hungrygnu.thefreebird;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Bird extends DestructibleDynamicObject {

    Level level;

    // Draw Bird specific parameters -------
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
    // -----------------------------------

    // Main Bird specific parameters--------------------------
    protected int food; // Eaten food trancsfers to poop and energy with time
    protected int energy; // Energy using when flying
    protected int poop; // Poop using when bombing
    // -----------------------------------------

    // Other Bird specific parameters ----
    protected boolean isFlying;
    protected boolean isGlyding;
    protected Circle bodyCircle;
    // -----------------------------------


    public Bird(Vector2 position, Level level) {
        super(level.renderer, position);
        this.level = level;

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

        bodyRadius = BIRD_BODY_RADIUS;
        eyeRadius = 0.7f *BIRD_SCALE;

        isFlying = false;
        isGlyding = false;
        recalculatePoints();
        bodyCircle = new Circle(position, bodyRadius);

    }

    @Override
    public void recalculatePoints() {
        recalculateStaticPoints();
        recalculateDinamicPoints();
    }

    public void recalculateStaticPoints(){

        beakB.set(position).add(0f, -2f * BIRD_SCALE);

        eyeL.set(position).add(-1.8f * BIRD_SCALE, 1.5f * BIRD_SCALE);
        eyeR.set(position).add(1.8f * BIRD_SCALE, 1.5f * BIRD_SCALE);

        wingLT.set(position).add(-4.2f * BIRD_SCALE, 2f * BIRD_SCALE);
        wingRT.set(position).add(4.2f * BIRD_SCALE, 2f * BIRD_SCALE);
    }

    public  void recalculateDinamicPoints(){
        if (isFlying) recalculateDinamicFlyingPoints();
        else recalculateDinamicSittingPoints();
    }

    public void recalculateDinamicFlyingPoints(){
        float wingY;
        if (isGlyding)
            wingY = wingLT.y;//wingLB.y +(wingLT.y - wingLB.y)/2f;
        else {
            float dinamicValue = (float)
                    ((wingLT.y - wingLB.y) * (1.3f*MathUtils.cos(MathUtils.PI2 * TimeUtils.timeSinceNanos(nanotimeAnimationStart) / BIRD_NANOTIME_FRAME)));
            wingY = wingLB.y - dinamicValue;
        }
        wingLB.set(wingLT).add(-0.5f *BIRD_SCALE, -2.2f * BIRD_SCALE);
        wingRB.set(wingRT).add(0.5f *BIRD_SCALE, -2.2f * BIRD_SCALE);

        wingLL.set(wingLT.x - 6f * BIRD_SCALE, wingY);
        wingRR.set(wingRT.x + 6f * BIRD_SCALE, wingY);

        tailL.set(position.x - 3f * BIRD_SCALE, position.y + 5.5f * BIRD_SCALE);
        tailR.set(position.x + 3f * BIRD_SCALE, position.y + 5.5f * BIRD_SCALE);
    }
    public void recalculateDinamicSittingPoints(){
        wingLB.set(wingLT).add(-0.5f *BIRD_SCALE, -3f * BIRD_SCALE);
        wingRB.set(wingRT).add(0.5f *BIRD_SCALE, -3f * BIRD_SCALE);

        wingLL.set(wingLT.x - 2f * BIRD_SCALE, wingLT.y - (wingLT.y - wingLB.y) / 2f);
        wingRR.set(wingRT.x + 2f * BIRD_SCALE, wingRT.y - (wingRT.y - wingRB.y) / 2f);

        tailL.set(beakB);
        tailR.set(beakB);
    }

    @Override
    public void update(float delta) {
        bodyCircle.setPosition(position); // Circle is for collisions detection


        if (isFlying){

            if (position.y > SKY_Y) {

                float speedYChange = delta * GRAVITY;
                if (velocity.y > 0){
                    velocity.add(0f, speedYChange);

                }
                else {
                    velocity.add(0f, speedYChange / BIRD_WINDAGE);
                }
                if (!Gdx.input.isTouched() || velocity.x == 0f){
                    velocity.x = 0f;
                    isGlyding = false;
                }

                position.mulAdd(velocity, delta);

                respectBorders();


            }
            else{
                isFlying = false;
                position.y = SKY_Y;
            }



        }
        super.update(delta);



    }

    @Override
    public void render() {
        super.render();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        // TAIL
        renderer.setColor(BIRD_COLOR_TAIL);
        renderer.triangle(tailL.x, tailL.y, tailR.x, tailR.y, beakB.x, beakB.y);
        // BODY uses lastFramePosition for a cool dynamic effect
        renderer.setColor(BIRD_COLOR_BODY);
        renderer.circle(lastFramePosition.x, lastFramePosition.y, bodyRadius, BIRD_SEGMENTS);
        lastFramePosition.set(position);
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

    public void flyUP(){
        isFlying = true;
        velocity.add(0f, BIRD_FLYUP_SPEED*(SKY_H-(position.y - SKY_Y))/SKY_H);
        position.add(0,1f);// jump to fly

        nanotimeAnimationStart = TimeUtils.nanoTime();
    }

    public void glideRight(){
        if (position.x < BIRD_BORDER_RIGHT) {
            velocity.x = BIRD_FLY_X_SPEED;
            isGlyding = true;
        }
    }
    public void glideLeft(){
        if (position.x > BIRD_BORDER_LEFT) {
            velocity.x = -BIRD_FLY_X_SPEED;
            isGlyding = true;
        }
    }

    public void respectBorders(){
        if (position.x < BIRD_BORDER_LEFT) {
            position.x = BIRD_BORDER_LEFT;
            isGlyding = false;
        }
        else if (position.x > BIRD_BORDER_RIGHT){
            position.x = BIRD_BORDER_RIGHT;
            isGlyding = false;
        }
    }

    private void checkCollisions() {

        for (Cat cat : level.cats)
            if (cat.hasCollisionWith(this)) {
                active = false; // The bird is dead
                return;
            }
        for (Caterpillar caterpillar : level.caterpillars) {
            Gdx.app.log("CHECK", "cater x " +caterpillar.position.x );
            if (caterpillar.hasCollisionWith(this)) {
                caterpillar.active = false; // The caterpillar is eaten;
            }
        }
    }
}
