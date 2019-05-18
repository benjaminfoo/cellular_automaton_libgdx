package org.owls.sandbox.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.adapter.SimpleListAdapter;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisWindow;

import org.owls.sandbox.model.GameOfLifeMatrix;
import org.owls.sandbox.model.RuleSet;

public class RuleSetStage {

    private Stage stage;
    private VisWindow window;
    private ListView<RuleSet> ruleSetView;
    private Array<RuleSet> rulesetArray;

    public void create() {
        stage = new Stage();

        window = new VisWindow("Rulesets");
        window.setResizable(true);
        window.setSize(200, 100);
        window.addCloseButton();

        rulesetArray = new Array<RuleSet>();
        rulesetArray.add(RuleSet.CONWAY);
        rulesetArray.add(RuleSet.WIREWORLD);

        SimpleListAdapter<RuleSet> adapter = new SimpleListAdapter<RuleSet>(rulesetArray);
        ruleSetView = new ListView<RuleSet>(adapter);
        ruleSetView.setItemClickListener(new ListView.ItemClickListener<RuleSet>() {
            @Override
            public void clicked(RuleSet item) {
                if (currentMatrix != null) {
                    System.out.println("Changing ruleset from " + currentMatrix.currentRuleset + " to " + item);
                    currentMatrix.currentRuleset = item;
                }
            }
        });
        window.add(ruleSetView.getMainTable()).grow();

        stage.addActor(window);

        // window.centerWindow();
        window.setPosition(
                Gdx.graphics.getWidth() - 225,
                Gdx.graphics.getHeight() - 475
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
