package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 15.02.16.
 */
public class BirdButton extends Bird {

    public float alpha;
    public boolean visible;
    public Button button;

    private Color body;
    private Color beak;


    public BirdButton(Vector2 position, Level level) {
        super(position, level);
        alpha = 0f;
        visible = false;
        button = new Button(renderer, position);
        button.radius = this.bodyRadius;
        body = new Color(BIRD_COLOR_BODY);
        beak = new Color(BIRD_COLOR_BEAK);

    }
    public void update(float delta){
        super.update(delta);
        if (visible && alpha < 1){
            alpha += delta;
            button.colorBody.a -= delta;
            button.colorText.a -= delta;
        }
        if (alpha > 1f) alpha = 1f;
        if (button.colorBody.a < 0f) button.colorBody.a = 0f;
        if (button.colorText.a < 0f) button.colorText.a = 0f;

        button.position.set(position);

    }
    @Override
    public void render() {
        renderer.set(ShapeRenderer.ShapeType.Filled);

        // TAIL
        renderer.setColor(BIRD_COLOR_TAIL.r, BIRD_COLOR_TAIL.g, BIRD_COLOR_TAIL.b, alpha);
        renderer.triangle(tailL.x, tailL.y, tailR.x, tailR.y, beakB.x, beakB.y);
        // BODY
        renderer.setColor(BIRD_COLOR_BODY);
        renderer.circle(lastFramePosition.x, lastFramePosition.y, bodyRadius, BIRD_SEGMENTS);
        lastFramePosition.set(position);
        // BEAK
        renderer.triangle(eyeL.x, eyeL.y, eyeR.x, eyeR.y, beakB.x, beakB.y, body, body, beak);
        // EYES
        renderer.setColor(BIRD_COLOR_EYE.r, BIRD_COLOR_EYE.g, BIRD_COLOR_EYE.b, alpha);
        renderer.circle(eyeL.x, eyeL.y, eyeRadius, BIRD_SEGMENTS);
        renderer.circle(eyeR.x, eyeR.y, eyeRadius, BIRD_SEGMENTS);
        // WINGS
        renderer.setColor(BIRD_COLOR_WINGS.r, BIRD_COLOR_WINGS.g, BIRD_COLOR_WINGS.b, alpha);
        renderer.triangle(wingLT.x, wingLT.y, wingLB.x, wingLB.y, wingLL.x, wingLL.y);
        renderer.triangle(wingRT.x, wingRT.y, wingRB.x, wingRB.y, wingRR.x, wingRR.y);

        button.render();


    }
}
