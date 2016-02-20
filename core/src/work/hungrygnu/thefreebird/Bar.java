package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 12.02.16.
 */
public class Bar extends StaticGameObject {
    private Rectangle bottomRect;
    private Rectangle topRect;
    private int value;
    private final float unitWidth;
    private Color color;

    public Bar (ShapeRenderer renderer, Vector2 position, int maxValue, Color color){
        // TODO Change size of top rectangle to get borders of the bar
        super(renderer,position);

        this.unitWidth = (BAR_WIDTH - 2f*BAR_PADDING) /maxValue;

        bottomRect = new Rectangle(position.x, position.y, BAR_WIDTH, BAR_HEIGHT);
        topRect = new Rectangle(position.x,position.y,BAR_HEIGHT- 2f* BAR_PADDING,BAR_HEIGHT-2f*BAR_PADDING);

        updateTopRect();

        this.color = new Color(color);


    }


    public void update(Vector2 newPosition, int value) {
        position.set(newPosition);
        this.value = value;
        bottomRect.setPosition(newPosition);

        updateTopRect();
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(BAR_BOTOM_COLOR);
        renderer.rect(bottomRect.x, bottomRect.y, bottomRect.width, bottomRect.height);
        renderer.setColor(color);
        renderer.rect(topRect.x, topRect.y, topRect.width, topRect.height);

    }

    private void updateTopRect(){
        topRect.setPosition(position.x + BAR_PADDING, position.y + BAR_PADDING);
        topRect.setWidth(value*unitWidth);
    }

}
