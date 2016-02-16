package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 11.02.16.
 */
public class Constants {

    // World parameters
    public static final float WORLD_WIDTH = 3f * 1920f;
    public static final float WORLD_HEIGHT = 3f * 1080f;
    public static final float CLOSEUP_WIDTH = 1920f;
    public static final float CLOSEUP_HEIGHT = 1080f;
    public static final Color BACKCOLOR = Color.BLACK;
    public static final float SCALE = WORLD_HEIGHT/48f;
    public static final float GRAVITY = -500f;
    // Underground parameters
    public static final float UNDERGROUND_HEIGHT = WORLD_HEIGHT/16f;
    public static final Color UNDERGROUND_FRONTCOLOR = Color.BLACK;
    public static final Color UNDERGROUND_BACKCOLOR = Color.DARK_GRAY;
    // Land parameters
    public static final float LAND_HEIGHT = WORLD_HEIGHT/8f;
    public static final Color LAND_FRONTCOLOR = Color.OLIVE;
    public static final Color LAND_BACKCOLOR = Color.FOREST;

    // Sky parameters
    public static final Color SKY_COLOR = Color.SKY;

    // Tree parameters
    public static final float TREE_X = WORLD_WIDTH/4f;
    public static final float TREE_Y = WORLD_HEIGHT/8f;
    public static final Vector2 TREE_POSITION = new Vector2(TREE_X, TREE_Y);
    public static final int TREE_SEGMENTS = 64;
    public static final Color TREE_COLOR_GREEN1 = Color.FOREST;
    public static final Color TREE_COLOR_GREEN2 = Color.FOREST;
    public static final Color TREE_COLOR_BROWN = Color.BROWN;

    // Nest parameters
    public static final float NEST_SCALE = 0.6f * SCALE;
    public static final Color NEST_COLOR_BOTTOM = Color.TAN;
    public static final Color NEST_COLOR_TOP = Color.FIREBRICK;
    public static final int NEST_SEGMENTS = TREE_SEGMENTS;

    // Bird parameters
    public static final float BIRD_SCALE = 0.15f * SCALE;
    public static final Color BIRD_COLOR_BODY = Color.RED;
    public static final Color BIRD_COLOR_BEAK = Color.CORAL;
    public static final Color BIRD_COLOR_EYE = Color.BLACK;
    public static final Color BIRD_COLOR_WINGS = Color.SCARLET;
    public static final Color BIRD_COLOR_TAIL = Color.SCARLET;
    public static final int BIRD_SEGMENTS = TREE_SEGMENTS;
    public static final float BIRD_WINDAGE = 100f;
    public static final float BIRD_FLYUP_SPEED = 300f;
    public static final float BIRD_FLY_X_SPEED = 200f;
    public static final float BIRD_FLY_X_DEADZONE = BIRD_FLY_X_SPEED / 100f;
    public static final long BIRD_NANOTIME_FRAME = (long)(Math.pow(10, 9));



    // Button parameters
    public static final float BUTTON_RADIUS = 100f;
    public static final int BUTTON_SEGMENTS = 128;
    public static final Color BUTTON_COLOR = Color.CORAL;
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;


}
