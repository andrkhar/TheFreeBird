package work.hungrygnu.thefreebird;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Sky {
    private ShapeRenderer renderer;

    public Sky(ShapeRenderer renderer){
        this.renderer = renderer;
    }

    public void render(){

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(SKY_COLOR);
        renderer.rect(0,LAND_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
    }
}
