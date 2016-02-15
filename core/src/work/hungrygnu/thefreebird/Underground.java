package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 15.02.16.
 */
public class Underground {
    private ShapeRenderer renderer;

    public Underground(ShapeRenderer renderer){
        this.renderer = renderer;
    }

    public void render(){

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0,0, WORLD_WIDTH, UNDERGROUND_HEIGHT, UNDERGROUND_FRONTCOLOR, UNDERGROUND_FRONTCOLOR, UNDERGROUND_BACKCOLOR, UNDERGROUND_BACKCOLOR);
    }
}
