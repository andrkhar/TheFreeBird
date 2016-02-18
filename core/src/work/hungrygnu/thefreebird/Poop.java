package work.hungrygnu.thefreebird;

import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Poop implements Renderable, Updatable {
    private final Bird bird;
    private Vector2 position;
    private Vector2 velocity;
    private boolean flying;

    public Poop (Bird bird){
        this.bird = bird;
        position = bird.position;
        velocity = bird.velocity;
        flying = true;
    }

    @Override
    public void update(float delta) {
        velocity.add(0f,GRAVITY * delta);
        position.add(velocity.scl(delta));

    }

    @Override
    public void render() {

    }


}
