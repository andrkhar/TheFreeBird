package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Poop extends DestructibleDynamicObject {


    Level level;

    public Poop(Level level) {
        super(level.bird.renderer, level.bird.position);
        velocity = new Vector2(level.bird.velocity);
        this.level = level;

    }

    @Override
    public void update(float delta) {
        velocity.add(0f, GRAVITY * delta);
        position.mulAdd(velocity, delta);
        checkCollisions();
    }

    @Override
    public void render() {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(POOP_COLOR_CIRCLE);
        renderer.circle(position.x, position.y, POOP_RADIUS, POOP_SEGMENTS);
        renderer.triangle(position.x - POOP_RADIUS, position.y, position.x + POOP_RADIUS, position.y, position.x, position.y + POOP_HEIGHT,
                POOP_COLOR_CIRCLE,POOP_COLOR_CIRCLE,POOP_COLOR_TOP);

    }

    private void checkCollisions() {

        for (Cat cat : level.cats)
            if (cat.hasCollisionWith(this)) {
                    cat.active = false;
                    active = false;
                    return;
                }
        for (Caterpillar caterpillar : level.caterpillars)
            if (caterpillar.hasCollisionWith(this)) {
                caterpillar.active = false;
                active = false;
                return;
            }

        if (position.y < POOP_LOWEST_Y) active = false;
    }
}