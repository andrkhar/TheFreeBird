package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;


/**
 * Created by hungry on 12.02.16.
 */
public class Tree extends Drawable{



    // The centers of the leaves circles
    private final Vector2 leavesLeftCenter;
    private final Vector2 leavesRightCenter;
    // The radiuses of the circles
    private float leavesLeftR;
    private float leavesRightR;

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



        leavesLeftCenter = (new Vector2(TREE_POSITION)).add(-TREE_C, 9f *TREE_C);
        leavesRightCenter = (new Vector2(TREE_POSITION)).add(4f * TREE_C, 7f * TREE_C);
        leavesLeftR = 5f * TREE_C;
        leavesRightR = 3.5f * TREE_C;

        root1 = (new Vector2(TREE_POSITION)).add(-1.5f * TREE_C, 0f);
        root2 = (new Vector2(TREE_POSITION)).add(0f, 5f * TREE_C);
        root3 = (new Vector2(TREE_POSITION)).add(2f * TREE_C, 0f);

        trunk1 = (new Vector2(TREE_POSITION)).add(-TREE_C, TREE_C);
        trunk2 = (new Vector2(TREE_POSITION)).add(0f, 11f * TREE_C);
        trunk3 = (new Vector2(TREE_POSITION)).add(TREE_C, 2f * TREE_C);

        branchL1 = (new Vector2(TREE_POSITION)).add(-2f * TREE_C, 9f *TREE_C);
        branchL2 = (new Vector2(TREE_POSITION)).add(0f, 8.5f *TREE_C);
        branchL3 = (new Vector2(TREE_POSITION)).add(0f, 8f *TREE_C);

        branchR1 = (new Vector2(TREE_POSITION)).add(0f, 7f *TREE_C);
        branchR2 = (new Vector2(TREE_POSITION)).add(6f * TREE_C, 7f *TREE_C);
        branchR3 = (new Vector2(TREE_POSITION)).add(0f, 6f *TREE_C);
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(TREE_COLOR_GREEN1);
        renderer.circle(leavesLeftCenter.x, leavesLeftCenter.y, leavesLeftR, TREE_S);
        renderer.setColor(TREE_COLOR_GREEN2);
        renderer.circle(leavesRightCenter.x, leavesRightCenter.y, leavesRightR, TREE_S);

        renderer.setColor(TREE_COLOR_BROWN);
        renderer.triangle(trunk1.x, trunk1.y, trunk2.x, trunk2.y, trunk3.x, trunk3.y);
        renderer.triangle(root1.x, root1.y, root2.x, root2.y, root3.x, root3.y);
        renderer.triangle(branchL1.x, branchL1.y, branchL2.x, branchL2.y, branchL3.x, branchL3.y);
        renderer.triangle(branchR1.x, branchR1.y, branchR2.x, branchR2.y, branchR3.x, branchR3.y);

    }

}
