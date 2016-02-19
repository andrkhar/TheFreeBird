package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 11.02.16.
 */
public class Constants {

    // World parameters
    public static final float WORLD_WIDTH = 2f * 1920f;
    public static final float WORLD_HEIGHT = 2f * 1080f;
    public static final Color BACKCOLOR = Color.BLACK;
    public static final float SCALE = WORLD_HEIGHT/48f;
    public static final float GRAVITY = -500f;

    // Underground parameters
    public static final float UNDERGROUND_HEIGHT = WORLD_HEIGHT/16f;
    public static final Color UNDERGROUND_FRONTCOLOR = Color.BLACK;
    public static final Color UNDERGROUND_BACKCOLOR = Color.DARK_GRAY;
    // Land parameters
    public static final float LAND_Y = UNDERGROUND_HEIGHT;
    public static final float LAND_HEIGHT = WORLD_HEIGHT/16f;
    public static final Color LAND_FRONTCOLOR = Color.OLIVE;
    public static final Color LAND_BACKCOLOR = Color.FOREST;

    // Sky parameters
    public static final Color SKY_COLOR = Color.SKY;
    public static final float SKY_Y = LAND_Y + LAND_HEIGHT;
    public static final float SKY_H = WORLD_HEIGHT *6f/8f;

    //Space parameters
    public static final float SPACE_Y= SKY_Y + SKY_H;
    public static final float SPACE_H = WORLD_HEIGHT / 8f;

    // Tree parameters
    public static final float TREE_X = WORLD_WIDTH/2f;
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
    public static final float BIRD_BODY_RADIUS = 5f * BIRD_SCALE;
    public static final Color BIRD_COLOR_BODY = Color.RED;
    public static final Color BIRD_COLOR_BEAK = Color.CORAL;
    public static final Color BIRD_COLOR_EYE = Color.BLACK;
    public static final Color BIRD_COLOR_WINGS = Color.SCARLET;
    public static final Color BIRD_COLOR_TAIL = Color.SCARLET;
    public static final int BIRD_SEGMENTS = TREE_SEGMENTS;
    public static final float BIRD_WINDAGE = 50f; // 100 is ok
    public static final float BIRD_FLYUP_SPEED = 300f;
    public static final float BIRD_FLY_X_SPEED = 200f;
    public static final long BIRD_NANOTIME_FRAME = (long)(0.5*Math.pow(10, 9));
    public static final float BIRD_BORDER_RIGHT = WORLD_WIDTH - BIRD_BODY_RADIUS;
    public static final float BIRD_BORDER_LEFT = BIRD_BODY_RADIUS;

    // Camera parameters
    public static final float CAM_CLOSEUP_COEFFICIENT = 0.4f;
    public static final float CAM_CLOSEUP_WIDTH = CAM_CLOSEUP_COEFFICIENT * WORLD_WIDTH;
    public static final float CAM_CLOSEUP_HEIGHT = CAM_CLOSEUP_COEFFICIENT * WORLD_HEIGHT;
    public static final float CAM_BORDER_RIGHT = WORLD_WIDTH - CAM_CLOSEUP_WIDTH /2f;
    public static final float CAM_BORDER_LEFT = CAM_CLOSEUP_WIDTH /2f;

    // Poop parameters
    public static final float POOP_RADIUS = 8f;
    public static final float POOP_HEIGHT = POOP_RADIUS * 2f;
    public static final int POOP_SEGMENTS = 16;
    public static final Color POOP_COLOR_CIRCLE = Color.GRAY;
    public static final Color POOP_COLOR_TOP = Color.LIGHT_GRAY;
    public static final float POOP_LOWEST_Y = SKY_Y - 2f*BIRD_SCALE;


    // Button parameters
    public static final float BUTTON_RADIUS = 100f;
    public static final int BUTTON_SEGMENTS = 128;
    public static final Color BUTTON_COLOR = Color.CORAL;
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;

    // Menu parameters
    public static final Color MENU_SIDE_COLOR = new Color(1f, 0f, 0f, 0.4f);
    public static final Color MENU_TOP_COLOR = new Color(0f, 0f, 1f, 0.4f);
    public static final Color MENU_BOTTOM_COLOR = new Color(0f, 1f, 0f, 0.4f);

    // Arrow parameters
    public static final Color ARROW_COLOR = new Color(1f, 1f, 1f, 1f);
    public static final float ARROW_LENGTH = 200f;
    public static final float ARROW_HALFLENGTH = ARROW_LENGTH/2f;

    public static final float ARROW_WIDTH = ARROW_LENGTH /5f;
    public static final float ARROW_HEAD_LENGTH = ARROW_LENGTH / 3f;
    public static final float ARROW_HEAD_HALFWIDTH = ARROW_WIDTH*0.5f;

    // Cat parameters
    public static final Color CAT_COLOR_BODY = Color.DARK_GRAY;
    public static final float CAT_SPEED_X = 400f;
    public static final float CAT_BODY_LENGTH = 50f;
    public static final float CAT_START_X_LEFT = -CAT_BODY_LENGTH;
    public static final float CAT_START_X_RIGHT = WORLD_WIDTH;
    public static final int CAT_RESPAWN_COEFFICIENT = 400; // BIGGER NUMBER - LESS CATS

    // Caterpillar parameters
    public static final Color CATERPILLAR_COLOR_BODY = Color.GOLDENROD;
    public static final float CATERPILLAR_SPEED_X = 50f;
    public static final float CATERPILLAR_BODY_LENGTH = 20f;
    public static final int CATERPILLAR_CIRCLES_NUMBER = 5;
    public static final float CATERPILLAR_RADIUS = CATERPILLAR_BODY_LENGTH/CATERPILLAR_CIRCLES_NUMBER;
    public static final int CATERPILLAR_RESPAWN_COEFFICIENT = 10; // BIGGER NUMBER - LESS CATERPILLARS




}
