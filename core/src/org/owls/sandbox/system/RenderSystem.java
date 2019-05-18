package org.owls.sandbox.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.owls.sandbox.components.CameraComponent;

public class RenderSystem extends EntityProcessingSystem implements InputProcessor {

    @Wire
    private OrthographicCamera orthographicCamera;


    public RenderSystem() {
        super(Aspect.one(CameraComponent.class));

        Gdx.app.log(getClass().getSimpleName(), "... rendersystem initialized");
    }

    @Override
    protected void process(Entity e) {

        // handle camera movement, setup projection matrices, etc.
        int moveX = 5, moveY = 5;
        float zoomAmount = 0.01f;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            orthographicCamera.translate(-moveX, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            orthographicCamera.translate(moveX, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            orthographicCamera.translate(0, -moveY, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            orthographicCamera.translate(0, moveY, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            orthographicCamera.zoom -= zoomAmount;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            orthographicCamera.zoom += zoomAmount;
        }

        orthographicCamera.update();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
		/*
		float x = cam.position.x;
		float y = cam.position.y;
		cam.position.set(x + screenX, y + screenY, 0);
		*/
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        /*
        if (amount == 1) {
            cam.zoom += 1f;
        } else if (amount == -1) {
            cam.zoom -= 1f;
        }
        */
        return false;
    }

}
