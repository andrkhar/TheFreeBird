package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 12.02.16.
 */
public class Land {
    private ShapeRenderer renderer;

    public Land(ShapeRenderer renderer){
        this.renderer = renderer;
    }

    public void render(){

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0,LAND_Y, WORLD_WIDTH, LAND_HEIGHT, LAND_FRONTCOLOR, LAND_FRONTCOLOR, LAND_BACKCOLOR, LAND_BACKCOLOR);
    }
}
