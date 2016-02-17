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
        renderer.rect(0,SKY_Y, WORLD_WIDTH, SKY_H);
        renderer.rect(0,SPACE_Y, WORLD_WIDTH, SPACE_H, SKY_COLOR, SKY_COLOR, UNDERGROUND_FRONTCOLOR, UNDERGROUND_FRONTCOLOR);
    }
}
