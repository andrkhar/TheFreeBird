package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 18.02.16.
 */
public class Arrow extends StaticGameObject {
    public int direction;

    public Arrow(ShapeRenderer renderer, Vector2 position, int direction) { // 1 is left, 2 is right, 3 is up
        super(renderer, position);
        this.direction = direction;
    }

    public void render(Vector2 cameraPosition) {

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(ARROW_COLOR);
        float deltaX = CAM_CLOSEUP_WIDTH/3f;
        float halfWidth = ARROW_LENGTH /2f;
        float rectX = cameraPosition.x;
        float rectY = cameraPosition.y;
        switch (direction){
            case 1:
                rectX += - deltaX - halfWidth;
                renderer.rect(rectX, rectY, ARROW_LENGTH, ARROW_WIDTH);
                renderer.triangle(rectX, rectY-ARROW_HEAD_HALFWIDTH,
                        rectX,rectY+ARROW_WIDTH+ARROW_HEAD_HALFWIDTH,
                        rectX - ARROW_HEAD_LENGTH, rectY + ARROW_WIDTH/2f);
                break;
            case 2:
                rectX = cameraPosition.x + deltaX - halfWidth;
                renderer.rect(rectX, rectY, ARROW_LENGTH, ARROW_WIDTH);
                rectX += + ARROW_LENGTH;
                renderer.triangle(rectX, rectY - ARROW_HEAD_HALFWIDTH,
                        rectX, rectY + ARROW_WIDTH + ARROW_HEAD_HALFWIDTH,
                        rectX + ARROW_HEAD_LENGTH, rectY + ARROW_WIDTH / 2f);
                break;
            case 3:
                rectX -= ARROW_WIDTH /2f;
                rectY += CAM_CLOSEUP_HEIGHT/6f;
                renderer.rect(rectX, rectY, ARROW_WIDTH, ARROW_HALFLENGTH);
                renderer.triangle(rectX - ARROW_HEAD_HALFWIDTH, rectY + ARROW_HALFLENGTH,
                        rectX + ARROW_WIDTH + ARROW_HEAD_HALFWIDTH, rectY + ARROW_HALFLENGTH,
                        rectX + ARROW_WIDTH/2f, rectY + ARROW_HALFLENGTH + ARROW_HEAD_LENGTH);

        }

    }
}
