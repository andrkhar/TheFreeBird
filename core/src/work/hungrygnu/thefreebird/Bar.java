package work.hungrygnu.thefreebird;

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

    public Bar (ShapeRenderer renderer, Vector2 position, int maxValue){
        super(renderer,position);

        this.unitWidth = BAR_WIDTH /maxValue;

        bottomRect = new Rectangle(position.x, position.y, BAR_WIDTH, BAR_HEIGHT);
        topRect = new Rectangle(bottomRect);
        updateTopRect();


    }


    public void update(int value) {
        this.value = value;
        updateTopRect();
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(BAR_BOTOM_COLOR);
        renderer.rect(bottomRect.x, bottomRect.y, bottomRect.width, bottomRect.height);
        renderer.setColor(BAR_TOP_COLOR);
        renderer.rect(topRect.x, topRect.y, topRect.width, topRect.height);

    }

    private void updateTopRect(){
        topRect.setWidth(value*unitWidth);
    }

}
