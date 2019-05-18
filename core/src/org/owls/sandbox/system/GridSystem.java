package org.owls.sandbox.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.owls.sandbox.components.GridComponent;

public class GridSystem extends EntityProcessingSystem {


    @Wire
    private OrthographicCamera orthographicCamera;

    int gridWidth = 2048 * 8;
    int gridHeight = 2048 * 8;
    int x_spacing = 10;
    int y_spacing = 10;

    private ShapeRenderer gridRenderer;

    public GridSystem() {
        super(Aspect.one(GridComponent.class));

        gridRenderer = new ShapeRenderer();
    }

    @Override
    protected void process(Entity e) {
        gridRenderer.setProjectionMatrix(orthographicCamera.combined);

        gridRenderer.setColor(Color.LIGHT_GRAY);
        gridRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (int y = 0; y < 1+gridHeight / y_spacing; y++) {
            gridRenderer.line(0, y*y_spacing, gridWidth , y*y_spacing);
        }

        for (int x = 0; x < 1+gridWidth/ x_spacing; x++) {
            gridRenderer.line(x*x_spacing, 0, x*x_spacing, gridHeight);
        }

        gridRenderer.end();

    }

}
