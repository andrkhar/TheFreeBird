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

    private Level level;

    private final long timeStart; // in Millis
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
    //protected boolean glyding;
    public boolean inNest;
    public Circle bodyCircle;
    // -----------------------------------

    // Bird moving parameters ----
    protected boolean movingX;
    protected boolean direction;
    protected boolean flyingUp;
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
        //glyding = false;
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
        flyingUp = false;
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
        if (isGlyding())
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

    // ===================================UPDATE========================================
    public void update(float delta) {
        move(delta);
        super.update(delta);
        updateLandCollider();
        checkCollisions();
        transformFoodToEnergyAndPoop();
        loseEnergyOrDie();
        timeSinceStart = TimeUtils.timeSinceMillis(timeStart);
    }
    // ===================================UPDATE========================================

    public void updateLandCollider(){
        landCollider.set((new Vector2(position)).sub(0, BIRD_COLLIDER_OFFSET_Y));
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
                energy += BIRD_ENERGY_ADD;
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

    private void clampVelocityY(){
        velocity.set(velocity.x,
                MathUtils.clamp(velocity.y, -BIRD_FLYDOWN_MAX_SPEED, BIRD_FLYUP_MAX_SPEED));
    }

    private void applyLandCollider(){
        tryCollideNest();
        tryCollideLand();

    }

    private void applyFlyUp(float delta){
        if (energy > 1){
            velocity.add(0f, BIRD_FLYUP_ACCELERATION*delta);
            energy--;
        }
    }

    private void clampPosition(){
        position.x = MathUtils.clamp(position.x,BIRD_BORDER_LEFT, BIRD_BORDER_RIGHT );
        position.y = MathUtils.clamp(position.y, BIRD_BORDER_BOTTOM, BIRD_BORDER_TOP);
    }

    // ============================serProperVelocity===================================
    private void setProperVelocityX(){
        if(movingX)
            velocity.x = (direction?-1:1)*(isGlyding()?BIRD_GLIDE_SPEED_X: BIRD_WALK_SPEED_X);
        else velocity.x = 0;
    }

    private void setProperVelocityY(float delta){
        applyGravity(delta);
        if(flyingUp) applyFlyUp(delta);
        else applyLandCollider();
        clampVelocityY();
    }
    // ============================serProperVelocity===================================

    // ===================================move=========================================
    private void move(float delta){
        setProperVelocityX();
        setProperVelocityY(delta);
        position = position.mulAdd(velocity, delta);
        clampPosition();
    }
    // ===================================move=========================================

    // ===================================RENDER=========================================
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
    // ===================================RENDER=========================================

    // ============================MoveX===================================
    public boolean askToStartMoveX(boolean direction){
        if (isMovingX() && this.direction != direction ){
            stopMoveX();
            return false;
        }
        else {
            startMoveX(direction);
            return true;
        }
    }

    private void startMoveX(boolean direction){
            this.direction = direction;
            movingX = true;
    }

    public void askToStopMoveX(){
        stopMoveX();
    }

    private void stopMoveX(){
        movingX = false;
    }
    // ============================MoveX===================================

    // ============================FlyUp===================================
    public boolean askToStartFlyUp(){
        if(!flyingUp)
            startFlyUp();
        return true;
    }

    private void startFlyUp(){
        if (energy > 1) { // 1 is for checking should the bird continue living
            flyingUp = true;
            flying = true;
            nanotimeAnimationStart = TimeUtils.nanoTime();
        }
    }

    public void askToStopFlyUp(){
        stopFlyUp();
    }

    private void stopFlyUp(){
        flyingUp = false;
    }
    // ============================FlyUp===================================

    // ==========================DropPoop==================================
    public void askToStartDropPoop(){
        dropPoop();
    }
    // ==========================DropPoop==================================


    private void checkCollisions() {
        bodyCircle.setPosition(position);
        checkCollisionCats();
        checkCollisionCaterpillars();
    }

    private void tryCollideLand(){
        if(landCollider.y <= BIRD_BORDER_BOTTOM){
            flying = false;
            position.y = BIRD_BORDER_BOTTOM;
            velocity.y = 0;
        }
    }

    private void tryCollideNest() {
        if(level.nest.isPointInNest(landCollider)){
            flying = false;
            inNest = true;
            position.y = level.nest.position.y + BIRD_NEST_POSITION_Y_OFFSET;
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

    private boolean isMovingX(){
        return movingX;
    }

    private boolean isGlyding(){
        return (position.y > BIRD_GLIDE_Y) && isMovingX() && !flyingUp;
    }


}
