package org.owls.sandbox.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import org.owls.sandbox.components.AutomataComponent;
import org.owls.sandbox.components.PositionComponent;
import org.owls.sandbox.model.CellStates;
import org.owls.sandbox.model.GameOfLifeMatrix;

import java.util.Timer;
import java.util.TimerTask;

public class AutomataSystem extends EntityProcessingSystem {

    private ShapeRenderer cellRenderer;
    @Wire
    private OrthographicCamera orthographicCamera;


    private int scaling = 10;

    public boolean iterationExecuted = true;


    public AutomataSystem() {
        super(Aspect.one(PositionComponent.class, AutomataComponent.class));

        cellRenderer = new ShapeRenderer();

        Gdx.app.log(getClass().getSimpleName(), "... automatasystem initialized");
        startTime = TimeUtils.nanoTime();
    }
    long startTime = 0;
    @Override
    protected void process(Entity e) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            iterationExecuted = !iterationExecuted;
        }

        PositionComponent positionComponent = e.getComponent(PositionComponent.class);
        AutomataComponent automataComponent = e.getComponent(AutomataComponent.class);

        cellRenderer.setProjectionMatrix(orthographicCamera.combined);
        cellRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // draw the actual matrix
        GameOfLifeMatrix golMatrix = automataComponent.game;
        Vector2 pos = positionComponent.pos;

        for (int y = (int) pos.y; y < golMatrix.getMap().length; y++) {
            for (int x = (int) pos.x; x < golMatrix.getMap()[y].length; x++) {
                int current = golMatrix.getMap()[y][x];

                if (current == CellStates.ELECTRON_HEAD) {
                    cellRenderer.setColor(Color.BLUE);
                    cellRenderer.rect(x * scaling, y * scaling, scaling, scaling);
                }

                if (current == CellStates.ELECTRON_TAIL) {
                    cellRenderer.setColor(Color.RED);
                    cellRenderer.rect(x * scaling, y * scaling, scaling, scaling);
                }

                if (current == CellStates.CONDUCTOR) {
                    cellRenderer.setColor(Color.YELLOW);
                    cellRenderer.rect(x * scaling, y * scaling, scaling, scaling);
                }
            }
        }

        // ...
        if(!iterationExecuted) {

            if (TimeUtils.timeSinceNanos(startTime) > 1000000000 * 0.05) {
            // if time passed since the time you set startTime at is more than 1 second

            //your code here
            golMatrix.setMatrixData(golMatrix.executeIteration(golMatrix.getMap()));
            Gdx.graphics.setTitle("Cellular Automata - Iteration: " + golMatrix.getIteration());
            iterationExecuted = false;

            //also you can set the new startTime
            //so this block will execute every one second
                startTime = TimeUtils.nanoTime();
            }


        }

        cellRenderer.end();
    }



}
