package work.hungrygnu.thefreebird.beings;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import static work.hungrygnu.thefreebird.Constants.*;
import work.hungrygnu.thefreebird.game.Level;

import static work.hungrygnu.thefreebird.Assets.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Bird extends DestructibleDynamicObject {
    // TODO: Make walking up/down move effect
    // TODO: Make losing little energy if flying and not gliding and if walking
    // TODO: Animate the bird death.

    Level level;

    final long timeStart; // in Millis
    public long timeSinceStart; // in Millis
    public int poopedCatsCounter;

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
    public int food; // Eaten food trancsfers to poop and energy with time
    public int energy; // Energy using when flying
    public int poop; // Poop using when bombing
    // -----------------------------------------

    // Other Bird specific parameters ----
    public boolean flying;
    protected boolean glyding;
    public boolean inNest;
    public Circle bodyCircle;
    // -----------------------------------

    // Bird moving parameters ----
    protected boolean movingX;
    protected boolean direction;
    // -----------------------------------



    // Bird Timers -----------------------
    long timeCounterFoodTransform;
    long timeCounterEnergyLose;
    // -----------------------------------

    // Bird Land Colider
    private Vector2 landCollider;


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

        flying = false;
        glyding = false;
        inNest = true;
        recalculatePoints();
        bodyCircle = new Circle(position, bodyRadius);

        food = BIRD_FOOD_MAX;
        energy = BIRD_ENERGY_MAX;
        poop = 0;

        timeCounterFoodTransform = TimeUtils.millis(); // count in Millis
        timeCounterEnergyLose = timeCounterFoodTransform;

        timeStart = TimeUtils.millis();
        timeSinceStart = 0;
        poopedCatsCounter = 0;

        landCollider = (new Vector2(position)).sub(0, BIRD_COLLIDER_OFFSET_Y);

        movingX = false;
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
        if (flying) recalculateDinamicFlyingPoints();
        else recalculateDinamicSittingPoints();
    }

    public void recalculateDinamicFlyingPoints(){
        float wingY;
        if (glyding)
            wingY = wingLT.y;
        else {
            float dinamicValue = (wingLT.y - wingLB.y) * (1.3f*MathUtils.cos(MathUtils.PI2 * TimeUtils.timeSinceNanos(nanotimeAnimationStart) / BIRD_NANOTIME_FRAME));
            wingY = wingLB.y - dinamicValue;
        }
        wingLB.set(wingLT).add(-0.5f * BIRD_SCALE, -2.2f * BIRD_SCALE);
        wingRB.set(wingRT).add(0.5f * BIRD_SCALE, -2.2f * BIRD_SCALE);

        wingLL.set(wingLT.x - 6f * BIRD_SCALE, wingY);
        wingRR.set(wingRT.x + 6f * BIRD_SCALE, wingY);

        tailL.set(position.x - 3f * BIRD_SCALE, position.y + 5.5f * BIRD_SCALE);
        tailR.set(position.x + 3f * BIRD_SCALE, position.y + 5.5f * BIRD_SCALE);
    }
    public void recalculateDinamicSittingPoints(){
        wingLB.set(wingLT).add(-0.5f * BIRD_SCALE, -3f * BIRD_SCALE);
        wingRB.set(wingRT).add(0.5f * BIRD_SCALE, -3f * BIRD_SCALE);

        wingLL.set(wingLT.x - 2f * BIRD_SCALE, wingLT.y - (wingLT.y - wingLB.y) / 2f);
        wingRR.set(wingRT.x + 2f * BIRD_SCALE, wingRT.y - (wingRT.y - wingRB.y) / 2f);

        tailL.set(beakB);
        tailR.set(beakB);
    }

    @Override
    public void update(float delta) {

        move(delta);

        super.update(delta);

        landCollider.set((new Vector2(position)).sub(0, BIRD_COLLIDER_OFFSET_Y));

        checkCollisions();

        transformFoodToEnergyAndPoop();

        loseEnergyOrDie();

        timeSinceStart = TimeUtils.timeSinceMillis(timeStart);
    }

    private void loseEnergyOrDie() {
        if(TimeUtils.timeSinceMillis(timeCounterEnergyLose) > BIRD_ENERGY_LOSE_TIME){
            if(energy>0) {
                energy--;
                timeCounterEnergyLose = TimeUtils.millis();
            }
            else
                death();
        }
    }

    private void transformFoodToEnergyAndPoop() {
        if ((TimeUtils.timeSinceMillis(timeCounterFoodTransform) > BIRD_FOOD_DIGEST_TIME) && (food > 0))
        {

            if (energy < BIRD_ENERGY_MAX){
                energy += 5;
                if (energy > BIRD_ENERGY_MAX)
                    energy = BIRD_ENERGY_MAX;
            }

            if (poop < BIRD_POOP_MAX)
                poop++;
            else
                dropPoop();
            timeCounterFoodTransform = TimeUtils.millis();
            food--;
        }

    }

    private void dropPoop(){
        if (poop > 0) {
            level.poops.add(new Poop(level));
            poop--;
            soundBomb.play(1f);
        }
    }

    private void applyGravity(float delta){
        velocity = velocity.add(0, GRAVITY*delta);

    }

    private void applyWindage(){
        if (velocity.y < 0 && flying)
            velocity.set(velocity.x, MathUtils.clamp(velocity.y, BIRD_WINDAGE, 0));

    }

    private void applyLandCollider(){
        applyCollisionNest();
    }

    private void move(float delta){
        applyGravity(delta);
        applyWindage();
        applyLandCollider();
        position = position.mulAdd(velocity, delta);
//        if (flying){
//
//            if (position.y > SKY_Y) {
//
//                float speedYChange = delta * GRAVITY;
//                if (velocity.y > 0){
//                    velocity.add(0f, speedYChange);
//
//                }
//                else {
//                    velocity.add(0f, speedYChange / BIRD_WINDAGE);
//                }
//                if (!Gdx.input.isTouched() || velocity.x == 0f){
//                    velocity.x = 0f;
//                    glyding = false;
//                }
//
//                position.mulAdd(velocity, delta);
//
//                respectBorders();
//
//
//            }
//            else{
//                flying = false;
//                position.y = BIRD_Y_WALK;
//                //velocity.x = 0;
//            }
//
//     }
//        else if(!inNest){
//            if (Gdx.input.isTouched()) {
//                position.add(velocity.x *delta, 0);
//                respectBorders();
//            }
//
//
//
//        }
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
        if (energy > 1) { // 1 is for checking should the bird continue living
            energy--;
            flying = true;
            velocity.add(0f, 2*BIRD_FLYUP_SPEED * (SKY_H - (position.y - SKY_Y)) / SKY_H);
            position.add(0, 2*BIRD_Y_WALK_DELTA+1f);// jump to fly more than BIRD_Y_WALK_DELTA

            nanotimeAnimationStart = TimeUtils.nanoTime();
            inNest = false;
        }
    }

    private void setProperVelocityX(){
        if (glyding)
            velocity.x = (direction?-1:1) * BIRD_FLY_X_SPEED;
        else 
            velocity.x = (direction?-1:1) * BIRD_WALK_X_SPEED;

    }


    private void startMoveX(boolean direction){
        if(!inNest){
            this.direction = direction;
            movingX = true;
            setProperGlyding();
            setProperVelocityX();
        }
    }

    public void askToStartMoveX(boolean direction){
        if (isMovingX() && this.direction != direction )
            stopMoveX();
        else
            startMoveX(direction);
    }

    private void stopMoveX(){
        movingX = false;
    }

    public void askToStopMoveX(){
        stopMoveX();
    }

    private void setProperGlyding(){
        if (position.y > SKY_Y)
            glyding = true;
    }

    public void askToStartFlyUp(){
        flyUP();
    }

    public void askToStartDropPoop(){
        dropPoop();
    }

    public void respectBorders(){
        position.x = MathUtils.clamp(position.x,BIRD_BORDER_LEFT, BIRD_BORDER_RIGHT );
    }

    private void checkCollisions() {

        bodyCircle.setPosition(position);

        if(!inNest){

            checkCollisionCats();

            checkCollisionCaterpillars();

            if (flying)
                applyCollisionNest();
      }
    }

    private void applyCollisionNest() {

        if(level.nest.isPointInNest(landCollider)) {

            flying = false;
            inNest = true;
            velocity.set(0,0);

        }
    }

    private void checkCollisionCaterpillars() {
        if (food < BIRD_FOOD_MAX)
            for (work.hungrygnu.thefreebird.beings.Caterpillar caterpillar : level.caterpillars) {
                if (caterpillar.hasCollisionWith(this)) {
                    caterpillar.active = false; // The caterpillar is eaten;
                    food++;
                    break;
                }
            }
    }

    private void checkCollisionCats() {
        for (work.hungrygnu.thefreebird.beings.Cat cat : level.cats)
            if (cat.hasCollisionWith(this)) {
                death(); // The bird is dead
                return;
            }
    }

    private void death(){
        active = false;
        soundDeath.play(0.5f);
    }

    public boolean isMovingX(){
        return movingX;
    }
}
