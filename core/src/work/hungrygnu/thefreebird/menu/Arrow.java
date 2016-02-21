package work.hungrygnu.thefreebird.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 18.02.16.
 */
public class Arrow extends work.hungrygnu.thefreebird.beings.StaticGameObject {
    public int direction;
    public Color color;

    public Arrow(ShapeRenderer renderer, int direction) { // 1 is left, 2 is right, 3 is up
        super(renderer, new Vector2());
        this.direction = direction;
        color = new Color(ARROW_COLOR);
    }

    public void render(Vector2 cameraPosition) {

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        float deltaX = CAM_CLOSEUP_WIDTH/3f;
        float halfWidth = ARROW_LENGTH /2f;
        float rectX = cameraPosition.x;
        float rectY = cameraPosition.y;
        switch (direction){
            case 1:// left
                rectX += - deltaX - halfWidth;
                rectY -= CAM_CLOSEUP_HALFHEIGHT/2f;
                renderer.rect(rectX, rectY, ARROW_LENGTH, ARROW_WIDTH);
                renderer.triangle(rectX, rectY-ARROW_HEAD_HALFWIDTH,
                        rectX,rectY+ARROW_WIDTH+ARROW_HEAD_HALFWIDTH,
                        rectX - ARROW_HEAD_LENGTH, rectY + ARROW_WIDTH/2f);
                break;
            case 2:// right
                rectX = cameraPosition.x + deltaX - halfWidth;
                rectY -= CAM_CLOSEUP_HALFHEIGHT/2f;
                renderer.rect(rectX, rectY, ARROW_LENGTH, ARROW_WIDTH);
                rectX += + ARROW_LENGTH;
                renderer.triangle(rectX, rectY - ARROW_HEAD_HALFWIDTH,
                        rectX, rectY + ARROW_WIDTH + ARROW_HEAD_HALFWIDTH,
                        rectX + ARROW_HEAD_LENGTH, rectY + ARROW_WIDTH / 2f);
                break;
            case 3:// up
                rectX -= ARROW_WIDTH /2f;
                rectY += CAM_CLOSEUP_HEIGHT/6f;
                renderer.rect(rectX, rectY, ARROW_WIDTH, ARROW_HALFLENGTH);
                renderer.triangle(rectX - ARROW_HEAD_HALFWIDTH, rectY + ARROW_HALFLENGTH,
                        rectX + ARROW_WIDTH + ARROW_HEAD_HALFWIDTH, rectY + ARROW_HALFLENGTH,
                        rectX + ARROW_WIDTH/2f, rectY + ARROW_HALFLENGTH + ARROW_HEAD_LENGTH);

        }

    }
}
