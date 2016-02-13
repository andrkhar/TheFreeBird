package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;


/**
 * Created by hungry on 12.02.16.
 */
public class Tree extends StaticDrawable {

    // The position of the nest
    public final Vector2 nestPosition;

    // The centers of the leaves circles
    private final Vector2 leavesLeftCenter;
    private final Vector2 leavesRightCenter;
    // The radiuses of the circles
    private final float leavesLeftR;
    private final float leavesRightR;

    // The points of the trunk the tree
    private final Vector2 trunk1;
    private final Vector2 trunk2;
    private final Vector2 trunk3;

    // The points of the tree base;
    private final Vector2 root1;
    private final Vector2 root2;
    private final Vector2 root3;

    // The points to draw Left Branch as a triangle
    private final Vector2 branchL1;
    private final Vector2 branchL2;
    private final Vector2 branchL3;

    // The points to draw Right Branch as a triangle
    private final Vector2 branchR1;
    private final Vector2 branchR2;
    private final Vector2 branchR3;

    public Tree(ShapeRenderer renderer) {
        super(renderer, TREE_POSITION);

        leavesLeftCenter = (new Vector2(TREE_POSITION)).add(-SCALE, 9f * SCALE);
        leavesRightCenter = (new Vector2(TREE_POSITION)).add(4f * SCALE, 7f * SCALE);
        leavesLeftR = 5f * SCALE;
        leavesRightR = 3.5f * SCALE;

        root1 = (new Vector2(TREE_POSITION)).add(-1.5f * SCALE, 0f);
        root2 = (new Vector2(TREE_POSITION)).add(0f, 5f * SCALE);
        root3 = (new Vector2(TREE_POSITION)).add(2f * SCALE, 0f);

        trunk1 = (new Vector2(TREE_POSITION)).add(-SCALE, SCALE);
        trunk2 = (new Vector2(TREE_POSITION)).add(0f, 11f * SCALE);
        trunk3 = (new Vector2(TREE_POSITION)).add(SCALE, 2f * SCALE);

        branchL1 = (new Vector2(TREE_POSITION)).add(-2f * SCALE, 9f * SCALE);
        branchL2 = (new Vector2(TREE_POSITION)).add(0f, 8.5f * SCALE);
        branchL3 = (new Vector2(TREE_POSITION)).add(0f, 8f * SCALE);

        branchR1 = (new Vector2(TREE_POSITION)).add(0f, 7f * SCALE);
        branchR2 = (new Vector2(TREE_POSITION)).add(6f * SCALE, 7f * SCALE);
        branchR3 = (new Vector2(TREE_POSITION)).add(0f, 6f * SCALE);

        nestPosition = (new Vector2(TREE_POSITION)).add(3f * SCALE, 7f * SCALE);
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(TREE_COLOR_GREEN1);
        renderer.circle(leavesLeftCenter.x, leavesLeftCenter.y, leavesLeftR, TREE_SEGMENTS);
        renderer.setColor(TREE_COLOR_GREEN2);
        renderer.circle(leavesRightCenter.x, leavesRightCenter.y, leavesRightR, TREE_SEGMENTS);

        renderer.setColor(TREE_COLOR_BROWN);
        renderer.triangle(trunk1.x, trunk1.y, trunk2.x, trunk2.y, trunk3.x, trunk3.y);
        renderer.triangle(root1.x, root1.y, root2.x, root2.y, root3.x, root3.y);
        renderer.triangle(branchL1.x, branchL1.y, branchL2.x, branchL2.y, branchL3.x, branchL3.y);
        renderer.triangle(branchR1.x, branchR1.y, branchR2.x, branchR2.y, branchR3.x, branchR3.y);

    }

}
