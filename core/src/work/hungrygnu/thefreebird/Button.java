package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Button extends StaticGameObject {
    public Color colorBody;
    public Color colorText;
    public float radius;

    public Button(ShapeRenderer renderer, Vector2 position){
        super(renderer, position);
        colorBody = new Color(BUTTON_COLOR);
        colorText = new Color(BUTTON_TEXT_COLOR);
        radius = BUTTON_RADIUS;
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(colorBody);
        renderer.circle(position.x, position.y, radius, BUTTON_SEGMENTS);
        flyWordRender();
    }

    private void flyWordRender(){
        renderer.setColor(colorText);
        float x = position.x;
        float y = position.y;
        float r = radius;
        float c = r/3f; // cell size, for drawing on paper
        float w = c/4f; // width of letter stick
        //F
        renderer.rectLine(x-c*2.5f, y-c, x-c*2.5f, y+c, w);
        renderer.rectLine(x-c*2.5f, y, x-c*1.5f, y, w);
        renderer.rectLine(x-c*2.5f, y+c, x-c*1.5f, y+c, w );
        //L
        renderer.rectLine(x-c/2f, y-c, x-c/2f, y+c, w);
        renderer.rectLine(x-c/2f, y-c, x+c/2f, y-c, w);
        //Y
        renderer.rectLine(x+c*1.5f, y+c, x+c*2f, y, w);
        renderer.rectLine(x+c*2.5f, y+c, x+c*2f, y, w);
        renderer.rectLine(x+c*2f, y, x+c*2f, y-c, w);


    }
}
