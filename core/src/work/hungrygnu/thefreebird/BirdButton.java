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


    public BirdButton(ShapeRenderer renderer, Vector2 position) {
        super(renderer, position);
        alpha = 0f;
        visible = false;
        button = new Button(renderer, position);
        button.radius = this.bodyRadius;
        body = BIRD_COLOR_BODY;
        beak = BIRD_COLOR_BEAK;
    }
    public void update(float delta){
        super.update(delta);
        if (visible && alpha < 1){
            alpha += delta/4f;
            button.colorBody.a -= delta/4f;
            button.colorText.a -= delta/4f;
        }
        if (alpha > 1f) alpha = 1f;
        if (button.colorBody.a < 0f) button.colorBody.a = 0f;
        if (button.colorText.a < 0f) button.colorText.a = 0f;

    }
    @Override
    public void render() {
        renderer.set(ShapeRenderer.ShapeType.Filled);


        // TAIL
        renderer.setColor(BIRD_COLOR_TAIL.r, BIRD_COLOR_TAIL.g, BIRD_COLOR_TAIL.b, alpha);
        renderer.triangle(tailL.x, tailL.y, tailR.x, tailR.y, beakB.x, beakB.y);
        // BODY
        renderer.setColor(BIRD_COLOR_BODY.r, BIRD_COLOR_BODY.g, BIRD_COLOR_BODY.b, alpha);
        renderer.circle(position.x, position.y, bodyRadius, BIRD_SEGMENTS);
        // BEAK
        beak = new Color(beak.r, beak.g, beak.b, alpha);
        body = new Color(body.r, body.g, body.b, alpha);
        renderer.triangle(eyeL.x, eyeL.y, eyeR.x, eyeR.y, beakB.x, beakB.y, body, body, beak);
        //renderer.triangle(eyeL.x, eyeL.y, eyeR.x, eyeR.y, beakB.x, beakB.y, BIRD_COLOR_BODY, BIRD_COLOR_BODY, BIRD_COLOR_BEAK);
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
