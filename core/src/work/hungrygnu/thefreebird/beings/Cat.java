package work.hungrygnu.thefreebird.beings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Cat extends FacingDeDyObject {

    // TODO: Make the cat jump when it is near the bird


    private long catCreateMillis;
    private Circle circle;
    private Rectangle rectangle;

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
        circle = new Circle(0,0, CAT_HEAD_RADIUS);
        rectangle = new Rectangle(0,0, CAT_BODY_LENGTH, CAT_BODY_WIDTH);
        update(0);
    }


    public void render(){

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderBody();
        renderHead();


    }

    public void update(float delta){

        position.mulAdd(velocity, delta);

        respectBorders(2 * CAT_BODY_LENGTH);

        position.y = CAT_Y + CAT_WALK_AMP *
                CAT_BODY_WIDTH * getAnimationCoefficient();
        rectangle.setPosition(position.x - CAT_BODY_LENGTH / 2f, position.y - CAT_BODY_WIDTH / 2f);
        updateHead(rectangle.x, rectangle.y);

    }

    public Boolean hasCollisionWith(Poop poop){
        return circle.contains(poop.position) || rectangle.contains(poop.position);
    }

    public Boolean hasCollisionWith(Bird bird){
        Rectangle collisionRect = new Rectangle(rectangle.x, rectangle.y - rectangle.height, rectangle.width, rectangle.height*2f );
        return circle.contains(bird.position) || collisionRect.contains(bird.position);
    }

    private float getAnimationCoefficient(){
        return MathUtils.sin(MathUtils.PI2 * TimeUtils.timeSinceMillis(catCreateMillis) / CAT_WALK_PERIOD);
    }

    private void renderBody()

    {
        float x = rectangle.x;
        float y = rectangle.y;

        float angle = 12 * getAnimationCoefficient();

        // tail
        float tailX = facingRight? x : x + CAT_BODY_LENGTH - CAT_TAIL_WIDTH;
        float tailY = y + CAT_HEAD_RADIUS - CAT_TAIL_WIDTH/2;
        float tailOriginX = CAT_TAIL_WIDTH/4f;
        float tailOriginY = 0;
        float tailAngle = angle/4f;

        // legs
        float legY = y + CAT_LEG_WIDTH/4f;
        // handR
        float handRX = facingRight?x + CAT_BODY_LENGTH - 2*CAT_LEG_WIDTH:x;
        float handRY = legY;
        float handROriginX= CAT_LEG_WIDTH/2f;
        float handROriginY= 0;
        float handRAngle = angle + 180;
        // handL
        float handLX = facingRight?x + CAT_BODY_LENGTH - CAT_LEG_WIDTH:handRX + CAT_LEG_WIDTH;
        float handLY = legY;
        float handLOriginX= CAT_LEG_WIDTH/2f;
        float handLOriginY= 0;
        float handLAngle = -angle + 180;
        // legR
        float legRX = facingRight?x:x + CAT_BODY_LENGTH - CAT_LEG_WIDTH*2f;
        float legRY = legY;
        float legROriginX= CAT_LEG_WIDTH/2f;
        float legROriginY= 0;
        float legRAngle = -angle + 180;
        // legL
        float legLX = facingRight?x + CAT_LEG_WIDTH:legRX + CAT_LEG_WIDTH;
        float legLY = legY;
        float legLOriginX= CAT_LEG_WIDTH/2f;
        float legLOriginY= 0;
        float legLAngle = angle + 180;

        // TAIL
        renderer.setColor(Color.DARK_GRAY);
        renderer.rect(tailX, tailY, tailOriginX, tailOriginY, CAT_TAIL_WIDTH, CAT_TAIL_LENGTH, 1, 1, tailAngle);

        if (facingRight) {
            // LEFT LEGS FIRST
            renderer.setColor(Color.DARK_GRAY);
            renderer.rect(handLX, handLY, handLOriginX, handLOriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, handLAngle);
            renderer.rect(legLX, legLY, legLOriginX, legLOriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, legLAngle);
            // BODY
            renderer.setColor(CAT_COLOR_BODY);
            renderer.rect(x, y, CAT_BODY_LENGTH, CAT_BODY_WIDTH);
            // RIGHT LEGS
            renderer.rect(handRX, handRY, handROriginX, handROriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, handRAngle);
            renderer.rect(legRX, legRY, legROriginX, legROriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, legRAngle);
        }
        else{ // if facing left
            // RIGHT LEGS FIRST
            renderer.setColor(Color.DARK_GRAY);
            renderer.rect(handRX, handRY, handROriginX, handROriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, handRAngle);
            renderer.rect(legRX, legRY, legROriginX, legROriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, legRAngle);
            // BODY
            renderer.setColor(CAT_COLOR_BODY);
            renderer.rect(x, y, CAT_BODY_LENGTH, CAT_BODY_WIDTH);
            // LEFT LEGS
            renderer.rect(handLX, handLY, handLOriginX, handLOriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, handLAngle);
            renderer.rect(legLX, legLY, legLOriginX, legLOriginY, CAT_LEG_WIDTH, CAT_LEG_LENGTH, 1, 1, legLAngle);
           }


    }

    public void updateHead(float x, float y){
        x += facingRight? CAT_BODY_LENGTH + CAT_HEAD_RADIUS_ONE_THIRD: -CAT_HEAD_RADIUS / 3f;
        y += CAT_BODY_WIDTH + CAT_HEAD_RADIUS_ONE_THIRD;

        x += CAT_HEAD_RADIUS_ONE_THIRD/10f * getAnimationCoefficient();

        circle.setPosition(x,y);
    }

    public void renderHead(){

        float animCoefficient = getAnimationCoefficient();

        // CALCULATION OF COORDINATES
        // CENTER
        float x = circle.x;
        float y = circle.y;

        // NOSE
        float noseX = x - CAT_NOSE_HALFSIDE;
        float noseY = y - CAT_NOSE_HALFSIDE;

        // MOUTH
        float mouthX = x - CAT_MOUTH_WIDTH/2f;
        float mouthY = y - CAT_HEAD_RADIUS/2f;

        // EYES
        float eyeY = y + CAT_HEAD_RADIUS_ONE_THIRD;
        // LEFT EYE
        float eyeLX = x - CAT_HEAD_RADIUS_ONE_THIRD - CAT_EYE_DELTA * animCoefficient;
        // RIGHT EYE
        float eyeRX = x + CAT_HEAD_RADIUS_ONE_THIRD + CAT_EYE_DELTA * animCoefficient;

        // CHEEKS
        float cheekY = y - CAT_CHEEK_RADIUS;
        float cheekLeftX = x - CAT_CHEEK_RADIUS;
        float cheekRightX = x + CAT_CHEEK_RADIUS;

        // EARS
        float earX = x - CAT_HEAD_RADIUS + CAT_EAR_OFFSET;
        float earY = y;

        // MUSTACHE
        float mustacheYMiddle = cheekY;
        float mustacheYTop = mustacheYMiddle + CAT_MUSTACHE_DELTA;
        float mustacheYBottom = mustacheYMiddle - CAT_MUSTACHE_DELTA;
        float mustacheXLeft = cheekLeftX;
        float mustacheXRight = cheekRightX;
        float mustacheXLeftEnd = cheekLeftX - CAT_MUSTACHE_LENGTH;
        float mustacheXRightEnd = cheekRightX + CAT_MUSTACHE_LENGTH;



        // RENDERING
        renderer.setColor(CAT_COLOR_BODY);
        renderer.rect(earX, earY, CAT_EAR_WIDTH, CAT_EAR_HEIGHT);
        // HEAD
        renderer.setColor(Color.DARK_GRAY);
        renderer.circle(x, y, CAT_HEAD_RADIUS, CAT_HEAD_SEGMENTS);
        // MOUTH
        renderer.setColor(Color.SALMON);
        renderer.rect(mouthX, mouthY, CAT_MOUTH_WIDTH, CAT_MOUTH_HEIGHT + CAT_MOUTH_DELTA *(1+ animCoefficient));
        // EYES
        renderer.setColor(Color.FOREST);
        renderer.circle(eyeLX, eyeY, CAT_EYE_RADIUS);
        renderer.circle(eyeRX, eyeY, CAT_EYE_RADIUS);
        // CHEEKS
        renderer.setColor(Color.GRAY);
        renderer.circle(cheekLeftX, cheekY, CAT_CHEEK_RADIUS);
        renderer.circle(cheekRightX, cheekY, CAT_CHEEK_RADIUS);
        // NOSE
        renderer.setColor(Color.PINK);
        renderer.rect(noseX, noseY, CAT_NOSE_HALFSIDE, CAT_NOSE_HALFSIDE, CAT_NOSE_SIDE, CAT_NOSE_SIDE, 1, 1, 45);
        // MUSTACHE
        renderer.setColor(Color.WHITE);
        renderer.rectLine(mustacheXLeft-CAT_MUSTACHE_DELTA, mustacheYMiddle, mustacheXLeftEnd, mustacheYMiddle, CAT_MUSTACHE_WIDTH);
        renderer.rectLine(mustacheXRight+CAT_MUSTACHE_DELTA, mustacheYMiddle, mustacheXRightEnd, mustacheYMiddle, CAT_MUSTACHE_WIDTH);

        renderer.rectLine(mustacheXLeft, mustacheYTop, mustacheXLeftEnd, mustacheYTop + CAT_MUSTACHE_DELTA, CAT_MUSTACHE_WIDTH);
        renderer.rectLine(mustacheXRight, mustacheYTop, mustacheXRightEnd, mustacheYTop + CAT_MUSTACHE_DELTA, CAT_MUSTACHE_WIDTH);

        renderer.rectLine(mustacheXLeft, mustacheYBottom, mustacheXLeftEnd, mustacheYBottom - CAT_MUSTACHE_DELTA, CAT_MUSTACHE_WIDTH);
        renderer.rectLine(mustacheXRight, mustacheYBottom, mustacheXRightEnd, mustacheYBottom - CAT_MUSTACHE_DELTA, CAT_MUSTACHE_WIDTH);



    }


}
