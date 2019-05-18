package org.owls.sandbox.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import org.owls.sandbox.components.AutomataComponent;
import org.owls.sandbox.components.GridComponent;
import org.owls.sandbox.model.CellStates;

public class EditingSystem extends EntityProcessingSystem {

    int gridWidth = 1280;
    int gridHeight = 720;
    int x_spacing = 10;
    int y_spacing = 10;
    int cellSize = 10;

    int posOffset = 25;

    private ShapeRenderer gridRenderer;

    @Wire
    private OrthographicCamera orthographicCamera;

    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private AutomataComponent currentAutomataComponent;

    public EditingSystem() {
        super(Aspect.one(GridComponent.class, AutomataComponent.class));

        spriteBatch = new SpriteBatch();
        gridRenderer = new ShapeRenderer();


        font = new BitmapFont();
        font.setColor(Color.GREEN);
    }

    @Override
    protected void process(Entity e) {

        //
        Vector3 mousePos = new Vector3(Gdx.input.getX(), (Gdx.input.getY()), 0); //Get the mouse-x and y like in your code
        orthographicCamera.unproject(mousePos); //Unproject it to get the correct camera position
        gridRenderer.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        int cellX = Math.round(mousePos.x) / cellSize * cellSize;
        int cellY = Math.round(mousePos.y) / cellSize * cellSize;

        // draw the edit-cell at the correct mouse-cursor position
        gridRenderer.setColor(Color.WHITE);
        gridRenderer.begin(ShapeRenderer.ShapeType.Filled);
        gridRenderer.rect(
                cellX,
                cellY,
                cellSize,
                cellSize
        );
        gridRenderer.end();

        AutomataComponent automataComponent = e.getComponent(AutomataComponent.class);
        if (automataComponent != null) {
            this.currentAutomataComponent = automataComponent;
        }

        // draw the current position of the mouse-cursor
        spriteBatch.begin();
        font.draw(spriteBatch,
                Math.round(mousePos.x) / cellSize + "," + Math.round(mousePos.y) / cellSize,
                mousePos.x + 20,
                mousePos.y
        );
        spriteBatch.end();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && currentAutomataComponent != null) {
            int xxx = Math.round(mousePos.x) / cellSize;
            int yyy = Math.round(mousePos.y) / cellSize;
            currentAutomataComponent.game.setCellAt(CellStates.ELECTRON_HEAD, xxx, yyy);
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && currentAutomataComponent != null) {
            int xxx = Math.round(mousePos.x) / cellSize;
            int yyy = Math.round(mousePos.y) / cellSize;
            currentAutomataComponent.game.setCellAt(CellStates.BLANK, xxx, yyy);
        }

    }

}
