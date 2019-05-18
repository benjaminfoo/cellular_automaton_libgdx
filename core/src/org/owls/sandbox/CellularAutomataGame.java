package org.owls.sandbox;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kotcrab.vis.ui.VisUI;
import org.owls.sandbox.components.*;
import org.owls.sandbox.system.*;

public class CellularAutomataGame extends ApplicationAdapter {

    private World world;

    @Override
    public void create() {
        Gdx.graphics.setTitle("Cellular Automata");
        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
        .with(
                new AutomataSystem(),
                new EditingSystem(),
                new GridSystem(),
                new GUISystem(),
                new RenderSystem()
        )
        .build().register(new OrthographicCamera(1280,720));

        world = new World(worldConfiguration);

        // renders a 2d-grid with definable scaling
        Entity gridEntity = world.createEntity();
        gridEntity.edit().add(new GridComponent());

        // renders a 2d-grid with definable scaling
        Entity toolboxEntity = world.createEntity();
        toolboxEntity.edit().add(new ToolboxComponent());

        // create the camera
        Entity cameraEntity = world.createEntity();
        cameraEntity.edit().add(new CameraComponent());

        // create an automata-entity and apply gol-rules to it
        Entity automataEntity = world.createEntity();
        automataEntity.edit().add(new PositionComponent(0,0)).add(new AutomataComponent());

        // Gdx.input.setInputProcessor(this);
        Gdx.app.log(getClass().getSimpleName(), "... application initialized");

    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // let the world do its work, rendering happens in the specific systems
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }

    @Override
    public void resize(int width, int height) {
        // uiStage.resize(width, height);
        // cam.update();
        // TODO: resize systems (like ui)
    }

    @Override
    public void dispose() {
        // batch.dispose();
        // uiStage.dispose();
        // TODO: dispose all systems!
        VisUI.dispose();
        Gdx.app.log(getClass().getSimpleName(), "... application closed");
    }

}
