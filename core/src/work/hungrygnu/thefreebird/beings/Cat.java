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
    // TODO: Draw the cat
    // TODO: Make the cat jump when it is near the bird
    // TODO: Optimise code using more CAT-constants instead of calculation.

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
        float tailWidth = CAT_HEAD_RADIUS_ONE_THIRD;
        float tailLength = CAT_HEAD_RADIUS + tailWidth/2;
        float tailX = facingRight? x : x + CAT_BODY_LENGTH - tailWidth;
        float tailY = y + CAT_HEAD_RADIUS - tailWidth/2;
        float tailOriginX = tailWidth/4f;
        float tailOriginY = 0;
        float tailAngle = angle/4f;


        // legs
        float legLength = 0.8f*tailLength;
        float legWidth = tailWidth;

        float legY = y + legWidth/4f;
        // handR
        float handRX = facingRight?x + CAT_BODY_LENGTH - 2*legWidth:x;
        float handRY = legY;
        float handROriginX= legWidth/2f;
        float handROriginY= 0;
        float handRAngle = angle + 180;
        // handL
        float handLX = facingRight?x + CAT_BODY_LENGTH - legWidth:handRX + legWidth;
        float handLY = legY;
        float handLOriginX= legWidth/2f;
        float handLOriginY= 0;
        float handLAngle = -angle + 180;
        // legR
        float legRX = facingRight?x:x + CAT_BODY_LENGTH - legWidth*2f;
        float legRY = legY;
        float legROriginX= legWidth/2f;
        float legROriginY= 0;
        float legRAngle = -angle + 180;
        // legL
        float legLX = facingRight?x + legWidth:legRX + legWidth;
        float legLY = legY;
        float legLOriginX= legWidth/2f;
        float legLOriginY= 0;
        float legLAngle = angle + 180;

        // TAIL
        renderer.setColor(Color.DARK_GRAY);
        renderer.rect(tailX, tailY, tailOriginX, tailOriginY, tailWidth, tailLength, 1, 1, tailAngle);

        if (facingRight) {
            // LEFT LEGS FIRST
            renderer.setColor(Color.DARK_GRAY);
            renderer.rect(handLX, handLY, handLOriginX, handLOriginY, legWidth, legLength, 1, 1, handLAngle);
            renderer.rect(legLX, legLY, legLOriginX, legLOriginY, legWidth, legLength, 1, 1, legLAngle);
            // BODY
            renderer.setColor(CAT_COLOR_BODY);
            renderer.rect(x, y, CAT_BODY_LENGTH, CAT_BODY_WIDTH);
            // RIGHT LEGS
            renderer.rect(handRX, handRY, handROriginX, handROriginY, legWidth, legLength, 1, 1, handRAngle);
            renderer.rect(legRX, legRY, legROriginX, legROriginY, legWidth, legLength, 1, 1, legRAngle);
        }
        else{ // if facing left
            // RIGHT LEGS FIRST
            renderer.setColor(Color.DARK_GRAY);
            renderer.rect(handRX, handRY, handROriginX, handROriginY, legWidth, legLength, 1, 1, handRAngle);
            renderer.rect(legRX, legRY, legROriginX, legROriginY, legWidth, legLength, 1, 1, legRAngle);
            // BODY
            renderer.setColor(CAT_COLOR_BODY);
            renderer.rect(x, y, CAT_BODY_LENGTH, CAT_BODY_WIDTH);
            // LEFT LEGS
            renderer.rect(handLX, handLY, handLOriginX, handLOriginY, legWidth, legLength, 1, 1, handLAngle);
            renderer.rect(legLX, legLY, legLOriginX, legLOriginY, legWidth, legLength, 1, 1, legLAngle);
           }


    }

    public void updateHead(float x, float y){
        x += facingRight? CAT_BODY_LENGTH + CAT_HEAD_RADIUS_ONE_THIRD: -CAT_HEAD_RADIUS / 3f;
        y += CAT_BODY_WIDTH + CAT_HEAD_RADIUS_ONE_THIRD;

        x += CAT_HEAD_RADIUS_ONE_THIRD/10f * getAnimationCoefficient();

        circle.setPosition(x,y);
    }

    public void renderHead(){

        // CALCULATION OF COORDINATES
        // CENTER
        float x = circle.x;
        float y = circle.y;

        // NOSE
        float noseSide = 0.7f*CAT_HEAD_RADIUS_ONE_THIRD;
        float noseHalfSide = noseSide/2f;
        float noseX = x-noseHalfSide;
        float noseY = y - noseHalfSide;

        // MOUTH
        float mouthWidth = CAT_HEAD_RADIUS_ONE_THIRD/2f;
        float mouthHeight = mouthWidth/5f;
        float mouthX = x - mouthWidth/2f;
        float mouthY = y - CAT_HEAD_RADIUS/2f;

        // EYES
        float eyeRadius = mouthHeight;
        float eyeY = y + CAT_HEAD_RADIUS_ONE_THIRD;
        // LEFT EYE
        float eyeLX = x - CAT_HEAD_RADIUS_ONE_THIRD;
        // RIGHT EYE
        float eyeRX = x + CAT_HEAD_RADIUS_ONE_THIRD;

        // CHEEKS
        float cheekRadius = 1.3f*CAT_HEAD_RADIUS_ONE_THIRD/2f;
        float cheekY = y - cheekRadius;
        float cheekLeftX = x - cheekRadius;
        float cheekRightX = x + cheekRadius;

        // EARS
        float earOffset = eyeRadius*2f;
        float earX = x - CAT_HEAD_RADIUS + earOffset;
        float earY = y;
        float earW = CAT_HEAD_RADIUS*2f - 2f*earOffset;
        float earH = CAT_HEAD_RADIUS - 2f*earOffset;

        // MUSTACHE
        float mustacheLength = 1.2f *CAT_HEAD_RADIUS_ONE_THIRD;
        float mustacheWidth = mustacheLength/20f;
        float mustacheYMiddle = cheekY;
        float mustacheDelta = 3f*mustacheWidth;
        float mustacheYTop = mustacheYMiddle + mustacheDelta;
        float mustacheYBottom = mustacheYMiddle - mustacheDelta;
        float mustacheXLeft = cheekLeftX;
        float mustacheXRight = cheekRightX;
        float mustacheXLeftEnd = cheekLeftX - mustacheLength;
        float mustacheXRightEnd = cheekRightX + mustacheLength;



        // RENDERING
        renderer.setColor(CAT_COLOR_BODY);
        renderer.rect(earX, earY, earW, earH);
        // HEAD
        renderer.setColor(Color.DARK_GRAY);
        renderer.circle(x, y, CAT_HEAD_RADIUS, CAT_HEAD_SEGMENTS);
        // MOUTH
        renderer.setColor(Color.SALMON);
        renderer.rect(mouthX, mouthY, mouthWidth, mouthHeight);
        // EYES
        renderer.setColor(Color.FOREST);
        renderer.circle(eyeLX, eyeY, eyeRadius);
        renderer.circle(eyeRX, eyeY, eyeRadius);
        // CHEEKS
        renderer.setColor(Color.GRAY);
        renderer.circle(cheekLeftX, cheekY, cheekRadius);
        renderer.circle(cheekRightX, cheekY, cheekRadius);
        // NOSE
        renderer.setColor(Color.PINK);
        renderer.rect(noseX, noseY, noseHalfSide, noseHalfSide, noseSide, noseSide, 1, 1, 45);
        // MUSTACHE
        renderer.setColor(Color.WHITE);
        renderer.rectLine(mustacheXLeft-mustacheDelta, mustacheYMiddle, mustacheXLeftEnd, mustacheYMiddle, mustacheWidth);
        renderer.rectLine(mustacheXRight+mustacheDelta, mustacheYMiddle, mustacheXRightEnd, mustacheYMiddle, mustacheWidth);

        renderer.rectLine(mustacheXLeft, mustacheYTop, mustacheXLeftEnd, mustacheYTop + mustacheDelta, mustacheWidth);
        renderer.rectLine(mustacheXRight, mustacheYTop, mustacheXRightEnd, mustacheYTop + mustacheDelta, mustacheWidth);

        renderer.rectLine(mustacheXLeft, mustacheYBottom, mustacheXLeftEnd, mustacheYBottom - mustacheDelta, mustacheWidth);
        renderer.rectLine(mustacheXRight, mustacheYBottom, mustacheXRightEnd, mustacheYBottom - mustacheDelta, mustacheWidth);



    }


}
