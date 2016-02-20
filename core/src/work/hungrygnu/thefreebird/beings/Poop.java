package work.hungrygnu.thefreebird.beings;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


import static work.hungrygnu.thefreebird.Constants.*;
import static work.hungrygnu.thefreebird.Assets.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Poop extends work.hungrygnu.thefreebird.beings.DestructibleDynamicObject {
    // TODO: Make the poop render splash while hitting

    work.hungrygnu.thefreebird.game.Level level;

    public Poop(work.hungrygnu.thefreebird.game.Level level) {

        super(level.bird.renderer, (new Vector2(level.bird.position)).sub(0f,BIRD_BODY_RADIUS/2f));
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

        for (work.hungrygnu.thefreebird.beings.Cat cat : level.cats)
            if (cat.hasCollisionWith(this)) {
                cat.active = false;
                active = false;
                level.bird.poopedCatsCounter++;
                soundHit.play(1f);
                return;
                }
        for (work.hungrygnu.thefreebird.beings.Caterpillar caterpillar : level.caterpillars)
            if (caterpillar.hasCollisionWith(this)) {
                caterpillar.active = false;
                active = false;
                soundHit.play(1f);
                return;
            }

        if (position.y < POOP_LOWEST_Y) active = false;
    }
}