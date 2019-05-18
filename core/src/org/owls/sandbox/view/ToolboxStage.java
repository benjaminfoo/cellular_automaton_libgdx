package org.owls.sandbox.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import org.owls.sandbox.model.GameOfLifeMatrix;
import org.owls.sandbox.util.PersistenceUtils;

public class ToolboxStage {

    private Stage stage;
    private VisWindow window;

    private VisTextButton iterateButton;
    private VisTextButton resetButton;
    private VisTextButton loadButton;
    private VisTextButton saveButton;
    private GameOfLifeMatrix currentGameOfLifeMatrx;

    public Stage getStage() {
        return stage;
    }

    public void create() {

        stage = new Stage();

        window = new VisWindow("Toolbox");
        window.setResizable(true);
        window.setSize(200, 200);
        window.addCloseButton();

        saveButton = new VisTextButton("Save current ...");
        loadButton = new VisTextButton("Load current ...");
        iterateButton = new VisTextButton("Iterate");
        resetButton = new VisTextButton("Reset");
        int default_padding = 5;
        window.add(saveButton).growX().pad(default_padding).row();
        window.add(loadButton).growX().pad(default_padding).row();
        window.add(iterateButton).growX().pad(default_padding).row();
        window.add(resetButton).growX().pad(default_padding).row();

        final FileChooser saveFileChooser = new FileChooser(FileChooser.Mode.SAVE);
        saveFileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        saveFileChooser.setDefaultFileName("current_data.autom");
        saveFileChooser.setListener(new FileChooserAdapter() {
            @Override
            public void selected(Array<FileHandle> file) {
                PersistenceUtils persistenceUtils = new PersistenceUtils();
                persistenceUtils.save(file.get(0), currentGameOfLifeMatrx);
            }
        });

        final FileChooser loadFileChooser = new FileChooser(FileChooser.Mode.OPEN);
        loadFileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        loadFileChooser.setListener(new FileChooserAdapter() {
            @Override
            public void selected(Array<FileHandle> file) {
                PersistenceUtils persistenceUtils = new PersistenceUtils();
                GameOfLifeMatrix load = persistenceUtils.load(file.get(0));

                currentGameOfLifeMatrx.setMatrixData(load.getMap());

            }
        });

        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //displaying chooser with fade in animation
                getStage().addActor(loadFileChooser.fadeIn());
            }
        });

        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //displaying chooser with fade in animation
                getStage().addActor(saveFileChooser.fadeIn());
            }
        });

        stage.addActor(window);

        // window.centerWindow();
        window.setPosition(
                Gdx.graphics.getWidth() - 225,
                Gdx.graphics.getHeight() - 225
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

    public void updateInfos(final GameOfLifeMatrix game) {
        iterateButton.clearListeners();

        iterateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setMatrixData(game.executeIteration(game.getMap()));
            }
        });
        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.reset(256, 256);
                // Gdx.graphics.setTitle("");
            }
        });

        this.currentGameOfLifeMatrx = game;
    }
}
