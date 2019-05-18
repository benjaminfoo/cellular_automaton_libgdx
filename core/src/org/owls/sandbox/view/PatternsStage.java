package org.owls.sandbox.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.adapter.SimpleListAdapter;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisWindow;

import org.owls.sandbox.data.PredefinedMaps;
import org.owls.sandbox.model.GameOfLifeMatrix;

public class PatternsStage {

    private Stage stage;
    private VisWindow window;
    private ListView<GameOfLifeMatrix> view;
    private Array<GameOfLifeMatrix> patternsArray;

    public void create() {
        stage = new Stage();

        window = new VisWindow("Patterns");
        window.setResizable(true);
        window.setSize(200, 200);
        window.addCloseButton();

        patternsArray = new Array<GameOfLifeMatrix>();
        patternsArray.add(new GameOfLifeMatrix("Acorn", PredefinedMaps.acorn));
        patternsArray.add(new GameOfLifeMatrix("Gun", PredefinedMaps.gun));
        patternsArray.add(new GameOfLifeMatrix("Half Eight", PredefinedMaps.halfEight));
        patternsArray.add(new GameOfLifeMatrix("Heavy Weight Spaceship", PredefinedMaps.heavyWeightSpaceship));
        patternsArray.add(new GameOfLifeMatrix("Hello World", PredefinedMaps.helloWorld));
        patternsArray.add(new GameOfLifeMatrix("R-Pentomino", PredefinedMaps.rPentomino));
        patternsArray.add(new GameOfLifeMatrix("Simple Gliders", PredefinedMaps.simpleGliders));
        patternsArray.add(new GameOfLifeMatrix("Water Test", PredefinedMaps.waterTest));

        SimpleListAdapter<GameOfLifeMatrix> adapter = new SimpleListAdapter<GameOfLifeMatrix>(patternsArray);
        view = new ListView<GameOfLifeMatrix>(adapter);
        view.setItemClickListener(new ListView.ItemClickListener<GameOfLifeMatrix>() {
            @Override
            public void clicked(GameOfLifeMatrix item) {
                System.out.println("Clicked: " + item);

                if (currentMatrix != null) {
                    currentMatrix.reset(256, 256);
                    currentMatrix.setMatrixData(item.getMap());
                }
            }
        });
        window.add(view.getMainTable()).grow();

        stage.addActor(window);

        // window.centerWindow();
        window.setPosition(
                Gdx.graphics.getWidth() - 225,
                Gdx.graphics.getHeight() - 700
        );

    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    GameOfLifeMatrix currentMatrix;

    public void updateInfos(final GameOfLifeMatrix game) {
        currentMatrix = game;

    }


    public Stage getStage() {
        return stage;
    }
}
