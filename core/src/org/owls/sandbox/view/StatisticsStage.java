package org.owls.sandbox.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisWindow;

import org.owls.sandbox.model.GameOfLifeMatrix;

public class StatisticsStage {

    private Stage stage;
    private VisWindow window;

    private VisLabel iterationLabel;
    private VisLabel sizeLabel;


    public void create () {

        stage = new Stage();

        window = new VisWindow("Statistics");
        window.setResizable(true);
        window.setSize(200,100);
        window.addCloseButton();

        iterationLabel = new VisLabel("Iteration: n/a");
        sizeLabel = new VisLabel("Width: 20\nHeight:20");

        window.add(iterationLabel).growX().pad(10);
        window.add(sizeLabel).growX().pad(10);

        stage.addActor(window);

        // window.centerWindow();
        window.setPosition(
                Gdx.graphics.getWidth() - 225,
                Gdx.graphics.getHeight() - 350
        );


    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render () {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public void updateInfos(GameOfLifeMatrix game) {
        iterationLabel.setText("Iteration: " + game.getIteration());
        sizeLabel.setText("Width: " + game.getMap().length + "\n" + "Height: " + game.getMap()[0].length);
    }

    public Stage getStage() {
        return stage;
    }
}
