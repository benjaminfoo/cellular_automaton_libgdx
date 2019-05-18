package org.owls.sandbox.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * https://github.com/libgdx/libgdx/wiki/Scene2d
 */
public class GridView extends Actor {


    public GridView() {
        super();

    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        setDebug(true);

        batch.end();

/*
        {
            renderer.setProjectionMatrix(batch.getProjectionMatrix());
            renderer.setTransformMatrix(batch.getTransformMatrix());
            renderer.translate(getX(), getY(), 0);
            renderer.setColor(Color.WHITE);

            renderer.begin(ShapeRenderer.ShapeType.Line);
            for (int y = 0; y < gridHeight / y_spacing; y++) {
                renderer.line(0, y*y_spacing, gridWidth , y*y_spacing);
            }

            for (int x = 0; x < gridWidth/ x_spacing; x++) {
                renderer.line(x*x_spacing, 0, x*x_spacing, gridHeight);
            }

            renderer.end();
        }
*/
        batch.begin();

    }
}
