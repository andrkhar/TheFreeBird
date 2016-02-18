package work.hungrygnu.thefreebird;

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
        velocity = level.bird.velocity;
        this.level = level;

    }

    @Override
    public void update(float delta) {
        velocity.add(0f, GRAVITY * delta);
        position.add(velocity.scl(delta));
        checkCollisions();
    }

    @Override
    public void render() {

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

        if (position.y < LAND_Y) active = false;
    }
}