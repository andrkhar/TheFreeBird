package work.hungrygnu.thefreebird.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Nest extends work.hungrygnu.thefreebird.beings.StaticGameObject {

    private final Rectangle rectBase;
    private final Rectangle rectTop;

    public Nest(ShapeRenderer renderer, Vector2 position) {

        super(renderer, position);


        rectBase = new Rectangle(position.x -1.5f * NEST_SCALE, position.y -0.5f * NEST_SCALE,
                3f * NEST_SCALE,NEST_SCALE);
        rectTop = new Rectangle(rectBase.getX()+ rectBase.getWidth()/10f,
                rectBase.getY() + 2f/3f * rectBase.getHeight(),
                0.8f * rectBase.getWidth(), rectBase.getHeight()/3f);

    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(NEST_COLOR_BOTTOM);
        renderer.ellipse(rectBase.getX(), rectBase.getY(), rectBase.getWidth(), rectBase.getHeight(),
                NEST_SEGMENTS);
        renderer.setColor(NEST_COLOR_TOP);
        renderer.ellipse(rectTop.getX(), rectTop.getY(), rectTop.getWidth(), rectTop.getHeight(),
                NEST_SEGMENTS);
    }

    public boolean isPointInNest(Vector2 point){
        return rectTop.contains(point.x, point.y);
    }
}
